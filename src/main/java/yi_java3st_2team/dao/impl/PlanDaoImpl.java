package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.ds.MySqlDataSource;
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
		try(Connection con = MySqlDataSource.getConnection();
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

}
