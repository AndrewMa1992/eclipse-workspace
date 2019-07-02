package com.itheima.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class ProductService {

	// Get hot items
	public List<Product> findHotProductList()  {
		
		ProductDao dao = new ProductDao();
		List<Product> hotProductList=null;
		try {
			hotProductList = dao.findHotProductList();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return hotProductList;
		
	}

	public List<Product> findNewProductList() {
		
		ProductDao dao = new ProductDao();
		List<Product> newProductList=null;
		try {
			newProductList = dao.findNewProductList();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return newProductList;
	}

	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return categoryList;
	}

	public PageBean findProductListByCid(String cid, int currentPage, int currentCount) {
		
		ProductDao dao = new ProductDao();
		
		// Wrap a pagebean back to the web layer
		
		PageBean<Product> pageBean = new PageBean<Product>();
		
		
		
		pageBean.setCurrentPage(currentPage);
		
		pageBean.setCurrentCount(currentCount);
		
		int totalCount=0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		pageBean.setTotalCount(totalCount);
		
		int totalPage = (int) Math.ceil(1.0 *totalCount/currentCount);
		
		pageBean.setTotalPage(totalPage);
		
		int index =(currentPage-1)*currentCount;
		
		List<Product> list =null;
		try {
			list = dao.findProductByPage(cid,index, currentCount);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		pageBean.setList(list);
		
		return pageBean;
	}

	public Product findProductByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}

	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();
		
		try {
			DataSourceUtils.startTransaction();
			
			dao.addOrders(order);
			
			dao.addOrderItem(order);
			
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	public void updateOrderAdrr(Order order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderAdrr(order);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void updateOrderState(String r6_Order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public List<Order> findAllOrders(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}

	


}
