package cn.itcast.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.domain.Sort;
import cn.itcast.util.JDBCUtils;

public class JDBCDemo {
	public static void main(String[] args) throws SQLException {
		
		Connection con = JDBCUtils.getConnection();
		String sql = "SELECT * FROM sort";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<Sort> list = new ArrayList<Sort>();
		while(rs.next()) {
			Sort s = new Sort(rs.getInt("sid"), rs.getString("sname"), 
					rs.getDouble("sprice"),rs.getString("sdesc"));
			list.add(s);
		}
		//traverse list container
		for(Sort s: list) {
			System.out.println(s);
		}
	}
}
