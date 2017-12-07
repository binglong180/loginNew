package com.niu.active;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * 
 * 修改一！
 * 
 * @author 牛牛牛！！！牛牛！！牛！
 *
 * @date 2017-12-7
 *
 * @version 
 *
 */
public class LoginJdbc {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			menu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 一级菜单！
	 * 
	 * @author 牛牛
	 *
	 * @date 2017-12-7
	 *
	 * @throws SQLException
	 */
	private static void menu() throws SQLException {
		System.out.println("1、注册");
		System.out.println("2、登录");
		System.out.println("3、退出");
		System.out.println("请选择：");
		String choice = input.next();
		switch (choice) {
		case "1":
			resigter();
			break;
		case "2":
			login();
			break;
		case "3":
			System.out.println("欢迎下次光临！");
			break;
		}
	}
	/**
	 * 
	 * 二级菜单！
	 * 
	 * @author 牛牛
	 *
	 * @date 2017-12-7
	 *
	 * @param loginName
	 * @throws SQLException
	 */
	private static void second(String loginName) throws SQLException {
		System.out.println("1、修改信息");
		System.out.println("2、删除用户");
		System.out.println("3、进入上一级");
		System.out.println("请选择：");
		String choice = input.next();
		switch (choice) {
		case "1":
			change(loginName);
			break;
		case "2":
			delet(loginName);
			break;
		case "3":
			menu();
			break;
		}
	}
	private static void delet(String loginName) throws SQLException {
		String sql="DELETE FROM easybuy_user WHERE loginName=?;";
		BaseDao bd = new BaseDao();
		int num=bd.update(sql,loginName);
		if (num > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
		menu();
	}

	private static void change(String loginName) throws SQLException {
		System.out.println("请输入新的登录名");
		String newloginName = input.next();
		System.out.println("请输入新的姓名");
		String userName = input.next();
		System.out.println("请输入新密码");
		String password = input.next();
		System.out.println("请输入性别1男0女");
		int sex = input.nextInt();
		String sql="UPDATE easybuy_user SET loginName=?,userName=?,password=?,sex=? WHERE loginName=?;";
		BaseDao bd = new BaseDao();
		int num=bd.update(sql, newloginName,userName,password,sex,loginName);
		if (num > 0) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}
		menu();
	}
	private static void login() throws SQLException {
		System.out.println("**********登录界面*********");
		System.out.println("请输入登录名");
		String loginName = input.next();
		System.out.println("请输入密码");
		String password = input.next();
		String sql = "SELECT loginName,PASSWORD,id,sex,userName FROM easybuy_user WHERE loginName=? AND PASSWORD=?;";
		BaseDao bd = new BaseDao();
		ResultSet query = bd.query(sql,loginName,password);
		if (query !=null) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
		while(query.next()){
			System.out.println("id："+query.getInt("id"));
			System.out.println("登录名："+query.getString("loginName"));
			System.out.println("真实名："+query.getString("userName"));
			System.out.println("性别："+query.getInt("sex"));
		}
		//二级菜单
		second(loginName);
	}

	private static void resigter() throws SQLException {
		System.out.println("*********注册界面*********");
		System.out.println("请输入登录名");
		String loginName = input.next();
		System.out.println("请输入真实姓名");
		String userName = input.next();
		System.out.println("请输入密码");
		String password = input.next();
		System.out.println("请输入性别1男0女");
		int sex = input.nextInt();
		String sql = "INSERT INTO easybuy_user(loginName,userName,password,sex)VALUES(?,?,?,?);";
		int num = 0;
		BaseDao bd = new BaseDao();
		try {
			num = bd.update(sql, loginName, userName, password, sex);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (num > 0) {
			System.out.println("注册成功！");
		} else {
			System.out.println("注册失败！");
		}
		menu();
	}
}
