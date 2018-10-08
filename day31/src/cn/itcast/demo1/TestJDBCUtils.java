package cn.itcast.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.itcast.util.JDBCUtils;

public class TestJDBCUtils {
public static void main(String[] args) throws SQLException {
	Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement("select sname from sort");
}
}
