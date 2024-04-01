package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.model.Inventory;
import com.model.Product;
import com.util.DBUtil;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> fetchAllProduct() throws SQLException {
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
	public void updateProduct(int productId, String field, String newVal) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update product SET "+field+"=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,newVal);
		pstmt.setInt(2,productId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
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

}
