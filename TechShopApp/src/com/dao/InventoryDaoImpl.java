package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dto.QuantityOfProductInStock;
import com.model.Inventory;
import com.model.Product;
import com.util.DBUtil;

public class InventoryDaoImpl implements InventoryDao {

	@Override
	public List<Inventory> fetchAllProductsDetails() throws SQLException {
		 List<Inventory> list=new ArrayList<>();
		 Connection conn = DBUtil.getDBConn();
			String sql = "select * from inventory";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				int id = rst.getInt("id");
				int quantityInStock = rst.getInt("QuantityInStock");
				LocalDate  lastStockUpdate = rst.getDate("last_stock_update").toLocalDate();
				int productId = rst.getInt("product_id");
				Inventory a = new Inventory(id, quantityInStock, lastStockUpdate, productId); 
				list.add(a);
			}
			DBUtil.dbClose();
			return (list);
	}

	@Override
	public List<QuantityOfProductInStock> getQunatityProductsInStock() throws SQLException {
		List<QuantityOfProductInStock> list=new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql = "select p.id,p.name,i.QuantityInStock from product p join inventory i on p.id=i.product_id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			int id = rst.getInt("p.id");
			String productName=rst.getString("p.name");
			int quantityInStock = rst.getInt("i.QuantityInStock");
			QuantityOfProductInStock p = new QuantityOfProductInStock(id,productName, quantityInStock); 
			list.add(p);
		}
		DBUtil.dbClose();
		return (list);
		
	}

	@Override
	public void addQuantity(int q, int productId) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "update inventory set QuantityInStock=? where product_id=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, q);
		pstmt.setInt(2, productId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	@Override
	public void removeQuantity(int q, int productId) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "update inventory set QuantityInStock=? where product_id=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, q);
		pstmt.setInt(2, productId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	@Override
	public void updateQuantity(int quantity, int productId) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "update inventory set QuantityInStock=? where product_id=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, quantity);
		pstmt.setInt(2, productId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	@Override
	public List<Product> fetchAllProducts() throws SQLException {
		List<Product> list=new ArrayList<>();
		 Connection conn = DBUtil.getDBConn();
			String sql = "select * from product";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				int id = rst.getInt("id");
				String name = rst.getString("name");
				String description = rst.getString("description");
				double price=rst.getDouble("price");
				Product p = new Product(id, name, description, price); 
				list.add(p);
			}
			DBUtil.dbClose();
			return (list);
	}

}
