package yi_java3st_2team.ui.panel.cust;

import javax.swing.JPanel;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CustDWCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Customer> {

	public CustDWCenterNorthSearchPanel() {
		
	}

	@Override
	public void tfClear() {
		this.getTfSearch().setText("");
	}

	@Override
	public String[] setSearchList() {
		String[] list = {"검색 구분", "고객명", "계좌번호"}; 
		return list;
	}

}
