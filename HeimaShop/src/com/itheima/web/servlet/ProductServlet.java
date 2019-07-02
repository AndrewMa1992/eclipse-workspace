package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.itheima.domain.Cart;
import com.itheima.domain.CartItem;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.domain.User;
import com.itheima.service.ProductService;
import com.itheima.utils.CommonsUtils;
import com.itheima.utils.JedisPoolUtils;
import com.itheima.utils.PaymentUtil;


import redis.clients.jedis.Jedis;

public class ProductServlet extends BaseServlet {

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String methodName = request.getParameter("method");
		if ("productList".equals(methodName)) {
			productList(request, response);
		} else if ("categoryList".equals(methodName)) {
			categoryList(request, response);
		} else if ("index".equals(methodName)) {
			index(request, response);
		} else if ("productInfo".equals(methodName)) {
			productInfo(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}*/
	
	
	public void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		
		ProductService service = new ProductService();
		//查询该用户的所有的订单信息(单表查询orders表)
		//集合中的每一个Order对象的数据是不完整的 缺少List<OrderItem> orderItems数据
		List<Order> orderList = service.findAllOrders(user.getUid());
		//循环所有的订单 为每个订单填充订单项集合信息
		if(orderList!=null){
			for(Order order : orderList){
				//获得每一个订单的oid
				String oid = order.getOid();
				//查询该订单的所有的订单项---mapList封装的是多个订单项和该订单项中的商品的信息
				List<Map<String, Object>> mapList = service.findAllOrderItemByOid(oid);
				//将mapList转换成List<OrderItem> orderItems 
				for(Map<String,Object> map : mapList){
					
					try {
						//从map中取出count subtotal 封装到OrderItem中
						OrderItem item = new OrderItem();
						//item.setCount(Integer.parseInt(map.get("count").toString()));
						BeanUtils.populate(item, map);
						//从map中取出pimage pname shop_price 封装到Product中
						Product product = new Product();
						BeanUtils.populate(product, map);
						//将product封装到OrderItem
						item.setProduct(product);
						//将orderitem封装到order中的orderItemList中
						order.getOrderItems().add(item);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					
					
				}

			}
		}
		
		//orderList封装完整了
		request.setAttribute("orderList", orderList);
		
		request.getRequestDispatcher("privilege/order_list.jsp").forward(request, response);

	}

	public void confirmOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String[]> properties = request.getParameterMap();
		Order order = new Order();
		try {
			BeanUtils.populate(order, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		ProductService service = new ProductService();
		
		service.updateOrderAdrr(order);
		
		String orderid = request.getParameter("oid");
		//String money = order.getTotal()+"";//支付金额
		String money = "0.01";//支付金额
		// 银行
		String pd_FrpId = request.getParameter("pd_FrpId");

		// 发给支付公司需要哪些数据
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = orderid;
		String p3_Amt = money;
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// 加密hmac 需要密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);


		String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
				"&p0_Cmd="+p0_Cmd+
				"&p1_MerId="+p1_MerId+
				"&p2_Order="+p2_Order+
				"&p3_Amt="+p3_Amt+
				"&p4_Cur="+p4_Cur+
				"&p5_Pid="+p5_Pid+
				"&p6_Pcat="+p6_Pcat+
				"&p7_Pdesc="+p7_Pdesc+
				"&p8_Url="+p8_Url+
				"&p9_SAF="+p9_SAF+
				"&pa_MP="+pa_MP+
				"&pr_NeedResponse="+pr_NeedResponse+
				"&hmac="+hmac;

		//重定向到第三方支付平台
		response.sendRedirect(url);
	
	}
	
	
	
	public void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if (user== null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		
		Order order = new Order();
		
		String oid = CommonsUtils.getUUID();
		order.setOid(oid);
		
		order.setOrdertime(new Date());
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		double total = cart.getTotal();
		
		order.setTotal(total);
		
		order.setState(0);
		
		order.setAddress(null);
		
		order.setName(null);
		
		order.setTelephone(null);
		
		order.setUser(user);
		
		Map<String, CartItem> cartItems = cart.getCartItems();
		
		for(Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
			CartItem cartItem = entry.getValue();
			
			OrderItem orderItem = new OrderItem();
			
			orderItem.setItemid(CommonsUtils.getUUID());
			
			orderItem.setCount(cartItem.getBuyNum());
			
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			orderItem.setProduct(cartItem.getProduct());
			
			orderItem.setOrder(order);
			
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		
		ProductService service = new ProductService();
		service.submitOrder(order);
		session.setAttribute("order", order);
		response.sendRedirect(request.getContextPath()+"/order_info.jsp");
		
	}
	
	
	public void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	
	
	
	public void delProFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			cart.setTotal(cart.getTotal()-cartItems.get(pid).getSubtotal());
			cartItems.remove(pid);
			cart.setCartItems(cartItems);
		}
		
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
	
	public void addProductToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ProductService service = new ProductService();
		
		String pid = request.getParameter("pid");
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		
		Product product = service.findProductByPid(pid);
		
		double subtotal = product.getShop_price() * buyNum;
		
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setBuyNum(buyNum);
		item.setSubtotal(subtotal);
		
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null) {
			cart = new Cart();
		}
		
		Map<String, CartItem> cartItems = cart.getCartItems();
		double newsubtotal = 0.0;
		if (cartItems.containsKey(pid)) {
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();
			oldBuyNum += buyNum;
			cartItem.setBuyNum(oldBuyNum);
			
			cart.setCartItems(cartItems);
			double oldsubtotal = cartItem.getSubtotal();
			
			newsubtotal = buyNum*product.getShop_price();
			
			cartItem.setSubtotal(oldsubtotal+newsubtotal);
			
			
		}else {
			cart.getCartItems().put(product.getPid(), item);
			newsubtotal = buyNum*product.getShop_price();
		}
		
		double total = cart.getTotal() + subtotal;
		
		cart.setTotal(total);
		
				
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
	
	public void categoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductService service = new ProductService();

		Jedis jedis = JedisPoolUtils.getJedis();
		String categoryListJson = jedis.get("categoryListJson");

		if (categoryListJson == null) {

			System.out.println("缓存没有数据 查询数据库");
			List<Category> categoryList = service.findAllCategory();
			Gson gson = new Gson();

			categoryListJson = gson.toJson(categoryList);
			jedis.set("categoryListJson", categoryListJson);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}

	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Prepare hot items

		ProductService service = new ProductService();

		List<Product> hotProductList = service.findHotProductList();

		List<Product> newProductList = service.findNewProductList();

		List<Category> categoryList = service.findAllCategory();

		request.setAttribute("categoryList", categoryList);
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("newProductList", newProductList);

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public void productInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentPage = request.getParameter("currentPage");

		String cid = request.getParameter("cid");

		String pid = request.getParameter("pid");

		ProductService service = new ProductService();
		Product product = service.findProductByPid(pid);

		request.setAttribute("product", product);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		String pids = pid;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					pids = cookie.getValue();

					String[] split = pids.split("-");
					List<String> asList = Arrays.asList(split);
					LinkedList<String> list = new LinkedList<String>(asList);
					if (list.contains(pid)) {
						list.remove(pid);
						list.addFirst(pid);
					} else {
						list.addFirst(pid);
					}
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < list.size() && i < 7; i++) {
						sb.append(list.get(i));
						sb.append("-");
					}
					pids = sb.substring(0, sb.length() - 1);

				}
			}
		}
		Cookie cookie_pids = new Cookie("pids", pids);

		response.addCookie(cookie_pids);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	public void productList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cid = request.getParameter("cid");

		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";

		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;

		ProductService service = new ProductService();
		PageBean pageBean = service.findProductListByCid(cid, currentPage, currentCount);

		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);

		List<Product> historyProductList = new ArrayList<Product>();

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();
					String[] split = pids.split("-");
					for (String pid : split) {
						Product pro = service.findProductByPid(pid);

						historyProductList.add(pro);
					}
				}
			}
		}

		request.setAttribute("historyProductList", historyProductList);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);

	}

}
