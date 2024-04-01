package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.model.Customer;
import com.model.Inventory;
import com.model.Orders;
import com.model.Product;
import com.util.DBUtil;

public class OrdersDaoImpl implements OrdersDao {

	@Override
	public List<Customer> fetchAllCustomers() throws SQLException {
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
	public List<Product> fetchAllProducts() throws SQLException {
		List<Product> list=new ArrayList<>();
		Connection conn=DBUtil.getDBConn();
		String sql="select *from product";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int id=rst.getInt("id");
			String productName=rst.getString("name");
			String description=rst.getString("description");
			double price=rst.getDouble("price");
			Product p=new Product(id,productName,description,price);
			list.add(p);
		}
		DBUtil.dbClose();
		return list;
	}

	@Override
	public List<Inventory> fetchAllInventory() throws SQLException {
		List<Inventory> list=new ArrayList<>();
		Connection conn=DBUtil.getDBConn();
		String sql="select *from inventory";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int id=rst.getInt("id");
			int quantityInStock=rst.getInt("QuantityInStock");
			LocalDate lastStockUpdate = rst.getDate("last_stock_update").toLocalDate();
			int productId=rst.getInt("product_id");
			Inventory i=new Inventory(id,quantityInStock,lastStockUpdate,productId);
			list.add(i);
		}
		DBUtil.dbClose();
		return list;
	}

	@Override
	public void insertRecordInReservation(int customerId, int productId, LocalDate now, double totalAmount,
			int quantity, String status) throws SQLException {
		Connection conn = DBUtil.getDBConn();

		String sql = "insert into orders(customer_id,product_id,date,total_amount,quantity,status) values(?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, customerId);
		pstmt.setInt(2, productId);
		pstmt.setString(3, now.toString());
		pstmt.setDouble(4, totalAmount);
		pstmt.setInt(5, quantity);
		pstmt.setString(6, status);
		
		pstmt.executeUpdate();

		DBUtil.dbClose();
	}

	@Override
	public void updateProductInStock(int newStockNum, int productId) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update inventory SET QuantityInStock=? where product_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,newStockNum);
		pstmt.setInt(2,productId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	@Override
	public List<Orders> fetchALLOrders() throws SQLException {
		List<Orders> list=new ArrayList<>();
		Connection conn=DBUtil.getDBConn();
		String sql="select *from orders";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int id=rst.getInt("id");
			int customerId=rst.getInt("customer_id");
			int productId=rst.getInt("product_id");
			LocalDate date=rst.getDate("date").toLocalDate();
			double totalAmount=rst.getDouble("total_amount");
			int quantity=rst.getInt("quantity");
			String status=rst.getString("status");
			Orders o=new Orders(id,customerId,productId,date,totalAmount,quantity,status);
			list.add(o);
		}
		DBUtil.dbClose();
		return list;
	}

	@Override
	public void updateStatus(int id, String status) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update orders SET status=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,status);
		pstmt.setInt(2,id);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
		
	}

	@Override
	public void updateStock(int productId, int inStock) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update inventory SET QuantityInStock=? where product_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,inStock);
		pstmt.setInt(2,productId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	@Override
	public void cancelOrder(int id) throws SQLException {
		 Connection conn=DBUtil.getDBConn();
		 String sql="delete from orders where id=?";
		 PreparedStatement pstmt=conn.prepareStatement(sql);
		 pstmt.setInt(1,id);
		 pstmt.executeUpdate();
		 DBUtil.dbClose();
		
	}

	@Override
	public void updateQuantity(int id, int newQuantity) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update orders SET quantity=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,newQuantity);
		pstmt.setInt(2,id);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}
		
	}


