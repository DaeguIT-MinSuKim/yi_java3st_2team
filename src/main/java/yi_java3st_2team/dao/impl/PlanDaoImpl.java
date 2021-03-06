package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.ds.LocalDataSource;
import yi_java3st_2team.ds.MySqlDataSource;
import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Loan;
import yi_java3st_2team.dto.Plan;

public class PlanDaoImpl implements PlanDao {
	private static final PlanDaoImpl instance = new PlanDaoImpl();
	
	public static PlanDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Plan> selectPlanAll() throws SQLException {
		List<Plan> list = null;
		String sql = "select planCode, planDetail, planName, planDesc, planDiv from plan";
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(getPlan(rs));
				}while(rs.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private Plan getPlan(ResultSet rs) throws SQLException {
		String planCode = rs.getString("planCode");
		String planDetail = rs.getString("planDetail");
		String planName = rs.getString("planName");
		String planDesc = rs.getString("planDesc");
		String planDiv = rs.getString("planDiv");
		return new Plan(planCode, planDetail, planName, planDesc, planDiv);
	}

	@Override
	public List<Plan> selectPlanByBankBook() throws SQLException {
		List<Plan> list = new ArrayList<>();
		String sql = "select * from plan where planCode like ?";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "A%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getPlan(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<Plan> selectPlanByCard() throws SQLException {
		List<Plan> list = new ArrayList<>();
		String sql = "select * from plan where planCode like ?";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "B%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getPlan(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<Plan> selectPlanByLoan() throws SQLException {
		List<Plan> list = new ArrayList<>();
		String sql = "select * from plan where planCode like ?";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "C%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getPlan(rs));
				}
			}
		}
		return list;
	}

	
	//!!!!!!!!!! 한글 깨짐 문제로 고객명으로 검색이 안돼서 상 코드 검색으로 임시변경함
	@Override
	public List<Plan> selectPlanByName(String planName) throws SQLException {
		List<Plan> list = null;
		String sql = "select planCode, planDetail, planName, planDesc, planDiv from plan where planName like ?";
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, '%'+planName+'%');
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(getPlan(rs));
				}while(rs.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertPlan(Plan plan) throws SQLException {
		String sql = "insert into plan values(?,?,?,?,?);";
		int res = -1;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, plan.getPlanCode());
			pstmt.setString(2, plan.getPlanDetail());
			pstmt.setString(3, plan.getPlanName());
			pstmt.setString(4, plan.getPlanDesc());
			pstmt.setString(5, plan.getPlanDiv());
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updatePlan(Plan plan) throws SQLException {
		String sql = "update plan set planDetail=?, planName=?, planDesc=?, planDiv=? where planCode=?";
		int res = -1;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
		//	pstmt.setString(1, plan.getPlanCode());
			pstmt.setString(1, plan.getPlanDetail());
			pstmt.setString(2, plan.getPlanName());
			pstmt.setString(3, plan.getPlanDesc());
			pstmt.setString(4, plan.getPlanDiv());
			pstmt.setString(5, plan.getPlanCode());
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deletePlan(Plan plan) throws SQLException {
		String sql = "delete from plan where planCode=?";
		int res = -1;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, plan.getPlanCode());
			
			res = pstmt.executeUpdate();
		}
		
		return res;
	}

	@Override
	public List<String> planExistChk() throws SQLException {
		List<String> list = null;
		String sql = "select planName from plan";
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(rs.getString(1));
				}while(rs.next());
			}
		}
		return list;
	}

	@Override
	public int selectPlanA() throws SQLException {
		String sql = "select count(*) from plan where planCode like 'A%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanB() throws SQLException {
		String sql = "select count(*) from plan where planCode like 'B%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanC() throws SQLException {
		String sql = "select count(*) from plan where planCode like 'C%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanAA() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'AA%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanAB() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'AB%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanAC() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'AC%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanBA() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'BA%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanBB() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'BB%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanCA() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'CA%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanCB() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'CB%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public int selectPlanCC() throws SQLException {
		String sql = "select count(*) from plan where planDetail like 'CC%'";
		int result = 0;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		return result;
	}

	@Override
	public List<Plan> selectPlanByCode(String planCode) throws SQLException {
		String sql = "select planCode, planDetail, planName, planDesc, planDiv from plan where planCode like ?";
		List<Plan> list = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, planCode+'%');
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(getPlan(rs));
				}while(rs.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Plan> selectPlanByDetail(String planDetail) throws SQLException {
		String sql = "select planCode, planDetail, planName, planDesc, planDiv from plan where planDetail like ?";
		List<Plan> list = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, planDetail+'%');
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(getPlan(rs));
				}while(rs.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Plan> selectPlanByBankBookNormal() throws SQLException {
		List<Plan> list = new ArrayList<>();
		String sql = "select * from plan where planCode like ? and plandiv = 'N'";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "A%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getPlan(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<Plan> selectPlanByCardNormal() throws SQLException {
		List<Plan> list = new ArrayList<>();
		String sql = "select * from plan where planCode like ? and plandiv = 'N'";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "B%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getPlan(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<Plan> selectPlanByLoanNormal() throws SQLException {
		List<Plan> list = new ArrayList<>();
		String sql = "select * from plan where planCode like ? and plandiv = 'N'";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "C%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getPlan(rs));
				}
			}
		}
		return list;
	}

}
