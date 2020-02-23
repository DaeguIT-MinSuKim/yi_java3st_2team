package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import yi_java3st_2team.dao.CustomerDao;
import yi_java3st_2team.ds.LocalDataSource;
import yi_java3st_2team.ds.MySqlDataSource;
import yi_java3st_2team.dto.Customer;

public class CustomerDaoImpl implements CustomerDao {
	private static final CustomerDaoImpl instance = new CustomerDaoImpl();
	
	public static CustomerDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Customer> selectCustomerAll() throws SQLException {
		List<Customer> list = null;
		String sql = "select custCode, custName, custRank, custCredit, custAddr, custTel from customer";
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(getCustomer(rs));
				}while(rs.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Customer getCustomer(ResultSet rs) throws SQLException{
		String custCode = rs.getString("custCode");
		String custName = rs.getString("custName");
		String custRank = rs.getString("custRank");
		int custCredit = rs.getInt("custCredit");
		String custAddr = rs.getString("custAddr");
		String custTel = rs.getString("custTel");
		
		return new Customer(custCode, custName, custRank, custCredit, custAddr, custTel);
	}

	
	//!!!!!!!!!! 한글 깨짐 문제로 고객명으로 검색이 안돼서 고객 코드 검색으로 임시변경함
	@Override
	public Customer selectCustomerByName(String custName) throws SQLException {
		String sql = "select custCode, custName, custRank, custCredit, custAddr, custTel from customer where custCode = ?";
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, custName);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					return getCustomer(rs);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
