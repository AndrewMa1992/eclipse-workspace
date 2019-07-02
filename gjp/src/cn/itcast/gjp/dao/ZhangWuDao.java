package cn.itcast.gjp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.gjp.domain.ZhangWu;
import cn.itcast.gjp.tools.JDBCUtils;

public class ZhangWuDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public void addZhangWu(ZhangWu zw) {
		try{
			 //ƴ��������ݵ�sql
			String sql = "INSERT INTO gjp_zhangwu (flname,money,zhanghu,createtime,description) VALUES(?,?,?,?,?)";
			//�����������飬����5��ռλ����ʵ�ʲ���
			//ʵ�ʲ�����Դ�Ǵ��ݹ����Ķ���ZhangWu
			Object[] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDescription()};
			//����qr�����еķ���updateִ�����
			qr.update(sql, params);
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("�������ʧ��");
		}
	}
	
	public List<ZhangWu> select(String startDate, String endDate){
		try {
		String sql = "SELECT * FROM gjp_zhangwu WHERE createtime BETWEEN ? AND ?";
		Object[] params = {startDate, endDate};
		
		 return qr.query(sql, new BeanListHandler<>(ZhangWu.class), params);
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Conditional query failed!");
		}
		
	}
	
	public List<ZhangWu> selectAll(){
		try {
			String sql = "SELECT * FROM gjp_zhangwu";
			
			List<ZhangWu> list = qr.query(sql, new BeanListHandler<>(ZhangWu.class));
			return list;
		}catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Failed to query account information!");
		}
		
	}

}
