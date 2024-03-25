package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Customer;
import com.model.Loan;
import com.util.DbUtil;

public class LoanDaoImpl implements LoanDao {

	@Override
	public List<Loan> fetchAllLoanDetails() throws SQLException {
		List<Loan> list=new ArrayList<>();
		Connection conn=DbUtil.getDBConn();
		String sql="select *from loan";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int id=rst.getInt("id");
			int customerId=rst.getInt("customer_id");
			double principalAmount=rst.getDouble("principal_amount");
			int interestRate=rst.getInt("interest_rate");
			int loanTerm=rst.getInt("loan_term");
			String loanType=rst.getString("loan_type");
			String loanStatus=rst.getString("loan_status");
			Loan l=new Loan(id,customerId,principalAmount,interestRate,loanTerm,loanType,loanStatus);
			list.add(l);
		}
		DbUtil.dbClose();
		return list;
	}

	@Override
	public List<Customer> fetchAllCustomer() throws SQLException {
		List<Customer> list=new ArrayList<>();
		Connection conn=DbUtil.getDBConn();
		String sql="select *from customer";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int id=rst.getInt("id");
			String name=rst.getString("name");
			String emailId=rst.getString("email_id");
			String phoneNumber=rst.getString("phone_num");
			String address=rst.getString("address");
			double creditScore=rst.getDouble("credit_score");
			Customer c=new Customer(id,name,emailId,phoneNumber,address,creditScore);
			list.add(c);
		}
		DbUtil.dbClose();
		return list;
	}

	@Override
	public void insertIntoLoan(int customerId, double principalAmount, int interestRate, int loanTerm, String loanType,
			String loanStatus) throws SQLException {
		Connection conn=DbUtil.getDBConn();
		String sql="insert into loan(customer_id,principal_amount,interest_rate,loan_term,loan_type,loan_status)values(?,?,?,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		pstmt.setDouble(2, principalAmount);
		pstmt.setInt(3,interestRate);
		pstmt.setInt(4, loanTerm);
		pstmt.setString(5,loanType);
		pstmt.setString(6, loanStatus);
		pstmt.executeUpdate();
		DbUtil.dbClose();
		
	}

}
