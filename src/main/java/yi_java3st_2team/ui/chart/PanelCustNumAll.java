package yi_java3st_2team.ui.chart;

import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import yi_java3st_2team.ui.service.CustomerService;

public class PanelCustNumAll extends JPanel {
	private final JLabel lblTitle = new JLabel("총 고객숫자");
	private CustomerService service = new CustomerService();
	
	public PanelCustNumAll() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		lblTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setEnabled(true);
		add(lblTitle);
		
		JLabel lblContent = new JLabel("");
		try {
			int custNum = service.showVIPCustNum()+service.showNormalCustNum();
			lblContent.setText(Integer.toString(custNum) + "명");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		lblContent.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblContent.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblContent);
	}

}
