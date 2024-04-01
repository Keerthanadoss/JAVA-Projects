package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dto.TotalOrderByCustomer;
import com.exception.DatabaseConnectionException;
import com.model.Customer;
import com.util.DBUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> fetchAllCustomers() throws DatabaseConnectionException,SQLException {
		List<Customer> list = new ArrayList<>();
		Connection conn = DBUtil.getDBConn();

		String sql = "select * from customer";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id = rst.getInt("id");
			
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			String email = rst.getString("email");
			String phone = rst.getString("phone");
			String address = rst.getString("address");
			Customer c = new Customer(id, firstName, lastName, email, phone, address); 
			list.add(c);
		}
		DBUtil.dbClose();
		return (list);
	}

	@Override
	public void createCustomer(String firstName, String lastName, String email, String phoneNumber,String  address) throws DatabaseConnectionException,SQLException {
		Connection conn = DBUtil.getDBConn();

		String sql = "insert into customer(first_name,last_name,email,phone ,address) values(?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName);
		pstmt.setString(3, email);
		pstmt.setString(4, phoneNumber);
		pstmt.setString(5, address);
		

		pstmt.executeUpdate();

		DBUtil.dbClose();
		
	}

	@Override
	public void updateCustomer(int id, String fieldd, String newVal) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "update customer set "+fieldd+"=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,newVal);
		pstmt.setInt(2, id);
		pstmt.executeUpdate();
		DBUtil.dbClose();
	}

	@Override
	public List<com.dto.TotalOrderByCustomer> TotalOrderByCustomer() throws DatabaseConnectionException,SQLException {
		List<TotalOrderByCustomer> list=new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql ="select c.id,c.first_name,count(o.customer_id) as no_of_orders from customer c join orders o on c.id=o.customer_id group by(o.customer_id)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rst= pstmt.executeQuery();
		
		while(rst.next()) {
			int id=rst.getInt("c.id");
			String firstName = rst.getString("c.first_name");
			int TotalOrder = rst.getInt("no_of_orders");
			TotalOrderByCustomer dto = new TotalOrderByCustomer(id,firstName,TotalOrder);
			list.add(dto);
		}
	 DBUtil.dbClose();
	return list;
	}

}
