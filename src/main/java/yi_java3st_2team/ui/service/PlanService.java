package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.dao.impl.PlanDaoImpl;
import yi_java3st_2team.dto.Plan;

public class PlanService {
	private PlanDao dao;
	
	public PlanService() {
		dao = new PlanDaoImpl().getInstance();
	}
	
	public List<Plan> showPlans() throws SQLException{
		return dao.selectPlanAll();
	}
	
	public List<Plan> showPlansByName(String planName) throws SQLException{
		return dao.selectPlanByName(planName);
	}

	
	public int addPlan(Plan plan) throws SQLException{
		return dao.insertPlan(plan);
	}
	
	public int editPlan(Plan plan) throws SQLException{
		return dao.updatePlan(plan);
	}
	
	public int removePlan(Plan plan) throws SQLException{
		return dao.deletePlan(plan);
	}
	
}
