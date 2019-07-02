package cn.itcast.gjp.service;

import java.util.List;

import cn.itcast.gjp.dao.ZhangWuDao;
import cn.itcast.gjp.domain.ZhangWu;

public class ZhangWuService {
	
	private ZhangWuDao dao = new ZhangWuDao();
	
	public void addZhangWu(ZhangWu zw) {
		dao.addZhangWu(zw);
	}
	
	public List<ZhangWu> select(String startDate, String endDate){
		
		return dao.select(startDate, endDate);
	}
	
	public List<ZhangWu> selectAll() {
		return dao.selectAll();
	}

}
