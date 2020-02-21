package yi_java3st_2team.ui.panel;

import javax.swing.JPanel;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CustInfoCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Customer> {

	public CustInfoCenterNorthSearchPanel() {
		
		initialize();
	}
	private void initialize() {
		getLblSearch().setFont(new Font("맑은 고딕", Font.BOLD, 20));
		getLblSearch().setHorizontalAlignment(SwingConstants.CENTER);
		getLblSearch().setText("고객명 입력");
		getLblSearch().setForeground(Color.BLACK);
	}

	@Override
	public void tfClear() {
		this.getTfSearch().setText("");
	}

}
