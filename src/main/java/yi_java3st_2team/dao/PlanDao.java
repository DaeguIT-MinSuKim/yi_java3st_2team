package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Loan;
import yi_java3st_2team.dto.Plan;

public interface PlanDao {
	abstract List<Plan> selectPlanAll() throws SQLException;
	abstract List<Plan> selectPlanByName(String planName) throws SQLException;
	abstract List<Plan> selectPlanByBankBook() throws SQLException;
	abstract List<Plan> selectPlanByCard() throws SQLException;
	abstract List<Plan> selectPlanByLoan() throws SQLException;
}
