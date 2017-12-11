package com.niu.active;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.niu.util.ConfigManger;

public class BaseDao {
	static Connection conn = null;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(ConfigManger.getPorValue("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(
					ConfigManger.getPorValue("jdbc.url"),
					ConfigManger.getPorValue("jdbc.username"),
					ConfigManger.getPorValue("jdbc.password"));
		}
		return conn;
	}

	public static int update(String sql, Object... params) throws SQLException {
		int num = 0;
		conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
		num = ps.executeUpdate();
		closeAll(conn, null, null);
		return num;
	}

	public static ResultSet query(String sql, Object... params)
			throws SQLException {
		ResultSet rs = null;
		conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
		rs = ps.executeQuery();
		return rs;
	}

	public static void closeAll(Connection conn, PreparedStatement ps,
			ResultSet query) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (query != null) {
			try {
				query.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
