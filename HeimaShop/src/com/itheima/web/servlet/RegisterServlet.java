package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.CommonsUtils;
import com.itheima.utils.MailUtils;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegisterServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Get form data
		
		Map<String, String[]> properties = request.getParameterMap();
		User user = new User();
		try {
			// Specify a type converter(Convert string to date)
			ConvertUtils.register(new Converter() {

				@Override
				public Object convert(Class clazz, Object value) {
					// Convert string to date
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date parse = null;
					try {
						parse = format.parse(value.toString());
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					
					return parse;
				}
				
			}, Date.class);
			// Mapping package
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		user.setUid(CommonsUtils.getUUID());
		
		user.setTelephone(null);
		
		user.setState(0);
		
		String activeCode = CommonsUtils.getUUID();
		
		user.setCode(activeCode);
		
		//pass user to the service layer
		
		UserService service = new UserService();
		
		boolean isRegisterSuccess = service.regist(user);
		
		//whether registration is successful
		if(isRegisterSuccess) {
			String emailMsg = "Congratulations on your registration, please click on the link below to activate your account."
					+ "<a href='http://localhost:8080/HeimaShop/active?activeCode="+activeCode+"'>"+"http://localhost:8080/HeimaShop/active?activecode="+
					activeCode+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
			//Jump to the page where registration is successful
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
			
		}else {
			// Jump to the failed prompt page
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
		
	}

}
