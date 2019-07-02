package cn.itcast.demo1;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
 * load properties configuration files
 * read files by using IO stream, add key-value pairs to collection
 * get the information of key-value pairs of connection from collection in database, to finish the connection of database 
 */

public class PropertiesDemo {
	
	public static void main(String[] args) throws Exception {
//		FileInputStream fis = new FileInputStream("src/database.properties");
//		System.out.println(fis);
		
		InputStream in = PropertiesDemo.class.getClassLoader().getResourceAsStream("database.properties");
		System.out.println(in);
		Properties pro = new Properties();
		pro.load(in);
		String driverClass = pro.getProperty("driverClass");
		String url = pro.getProperty("url");
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println(con);
		
	}

}
