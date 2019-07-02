package cn.itcast.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception{
		System.out.println("进入hello action...");
		return SUCCESS;
	}

}
