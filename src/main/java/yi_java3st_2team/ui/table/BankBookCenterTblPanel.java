package yi_java3st_2team.ui.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

@SuppressWarnings("serial")
public class BankBookCenterTblPanel extends AbsCenterTblPanel<BankBook> {

	public BankBookCenterTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		setColumnWidth(200,100,100,100,200,100);
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3,4);
		setColumnAlign(SwingConstants.RIGHT,5);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"계좌번호","고객이름","상품명","통장구분","계좌개설일","이자율"};
	}

	@Override
	public BankBook getSelectedItem() {
		int selIdx = getSelectedRowIdx();
		String accountNum = (String)model.getValueAt(selIdx, 0);
		Customer custCode = new Customer();
		custCode.setCustName((String)model.getValueAt(selIdx, 1));
		Plan accountPlanCode = new Plan();
		accountPlanCode.setPlanName((String)model.getValueAt(selIdx, 2));
		String dateArr = (String)model.getValueAt(selIdx, 4);
		Date accountOpenDate = null;
		try {
			accountOpenDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateArr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String interestStr = (String)model.getValueAt(selIdx, 5);
		interestStr = interestStr.replaceAll("[\\%]", "");
		float accountInterest = (Float.parseFloat(interestStr) / 100);
		return new BankBook(accountNum, custCode, accountPlanCode, accountOpenDate, accountInterest);
	}

	@Override
	protected Object[] toArray(BankBook item) {
		String bankDiv = null;
		if(item.getAccountNum().substring(8, 9).equals("1")) {
			bankDiv = "예금";
		}
		else if(item.getAccountNum().substring(8, 9).equals("2")) {
			bankDiv = "적금";
		}
		else {
			bankDiv = "마이너스";
		}
		return new Object[] {item.getAccountNum(),item.getCustCode().getCustName(),item.getAccountPlanCode().getPlanName(),bankDiv,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getAccountOpenDate()),String.format("%.2f%%", item.getAccountInterest()*100)};
	}

	@Override
	public void updateRow(BankBook item, int updateIdx) {
		model.setValueAt(item.getAccountNum(), updateIdx, 0);
		model.setValueAt(item.getCustCode().getCustName(), updateIdx, 1);
		model.setValueAt(item.getAccountPlanCode().getPlanName(), updateIdx, 2);
		String bankDiv = null;
		if(item.getAccountNum().substring(8,9).equals("1")) {
			bankDiv = "예금";
		}
		else if(item.getAccountNum().substring(8, 9).equals("2")) {
			bankDiv = "적금";
		}
		else {
			bankDiv = "마이너스";
		}
		model.setValueAt(bankDiv, updateIdx, 3);
		model.setValueAt(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(item.getAccountOpenDate()), updateIdx, 4);
		model.setValueAt(String.format("%.2f%%", item.getAccountInterest() * 100), updateIdx, 5);
	}

}
