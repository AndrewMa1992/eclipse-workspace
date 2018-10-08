package cn.itcast.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo1 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		
		String url ="jdbc:mysql://localhost:3306/mybase";
		String username ="root";
		String password ="123";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stat = con.createStatement();
		int row = stat.executeUpdate
					("insert into sort(sname,sprice,sdesc) values('汽车用品',50000,'优惠的促销')");
		System.out.println(row);
		
		stat.close();
		con.close();
	}
}
