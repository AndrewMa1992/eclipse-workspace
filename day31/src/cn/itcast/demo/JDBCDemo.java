package cn.itcast.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		
		String url ="jdbc:mysql://localhost:3306/mybase";
		String username ="root";
		String password ="123";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stat = con.createStatement();
		String sql = "select * from sort";
		ResultSet rs = stat.executeQuery(sql);
		while(rs.next()) {
			
		}
		rs.close();
		stat.close();
		con.close();
	}
}
