package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Plan;

public interface PlanDao {
	abstract List<Plan> selectPlanAll() throws SQLException;
}
