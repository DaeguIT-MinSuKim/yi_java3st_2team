package yi_java3st_2team.ui.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Loan;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

@SuppressWarnings("serial")
public class LoanCenterTblPanel extends AbsCenterTblPanel<Loan> {

	/**
	 * Create the panel.
	 */
	public LoanCenterTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		setColumnWidth(200,100,100,50,150,100,100);
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3,4,5,6);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"계좌번호","고객이름","상품명","대출구분","대출날짜","대출이자","대출금액"};
	}

	@Override
	protected Object[] toArray(Loan item) {
		String loanDiv = null;
		switch(item.getLoanAccountNum().substring(8, 9)) {
		case "1":
			loanDiv = "일반대출";
			break;
		case "2":
			loanDiv = "신용대출";
			break;
		case "3":
			loanDiv = "카드론";
			break;
		}
		return new Object[] {item.getLoanAccountNum(),item.getCustCode().getCustName(),item.getPlanCode().getPlanName(),loanDiv,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getLoanDate()),item.getLoanInterest(),item.getLoanBalance()};
	}

	@Override
	public void updateRow(Loan item, int updateIdx) {
		String loanDiv = null;
		switch(item.getLoanAccountNum().substring(8, 9)) {
		case "1":
			loanDiv = "일반대출";
			break;
		case "2":
			loanDiv = "신용대출";
			break;
		case "3":
			loanDiv = "카드론";
			break;
		}
		model.setValueAt(item.getLoanAccountNum(), updateIdx, 0);
		model.setValueAt(item.getCustCode().getCustName(), updateIdx, 1);
		model.setValueAt(item.getPlanCode().getPlanName(), updateIdx, 2);
		model.setValueAt(loanDiv, updateIdx, 3);
		model.setValueAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getLoanDate()), updateIdx, 4);
		model.setValueAt(item.getLoanInterest(), updateIdx, 5);
		model.setValueAt(item.getLoanBalance(), updateIdx, 6);
	}

	@Override
	public Loan getSelectedItem() {
		int idx = getSelectedRowIdx();
		String loanAccountNum = (String)model.getValueAt(idx, 0);
		Customer custCode = new Customer();
		custCode.setCustName((String)model.getValueAt(idx, 1));
		Plan planCode = new Plan();
		planCode.setPlanName((String)model.getValueAt(idx, 2));
		Date loanDate = null;
		try {
			loanDate = new SimpleDateFormat().parse((String)model.getValueAt(idx, 4));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float loanInterest = (float)model.getValueAt(idx, 5);
		long loanBalance = (long)model.getValueAt(idx, 6);
		return new Loan(loanAccountNum, custCode, planCode, loanDate, loanInterest, loanBalance);
	}

}
