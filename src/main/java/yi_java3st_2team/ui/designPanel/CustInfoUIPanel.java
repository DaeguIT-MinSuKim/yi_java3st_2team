package yi_java3st_2team.ui.designPanel;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.ui.panel.CustInfoCenterNorthSearchPanel;
import yi_java3st_2team.ui.service.CustomerService;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.panel.CustInfoCenterCenterTblPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustInfoUIPanel extends JPanel implements ActionListener {
	CustomerService custService = new CustomerService();
	private CustInfoCenterNorthSearchPanel panel;
	private List<Customer> listForCustName = new ArrayList<>();
	private CustInfoCenterCenterTblPanel panel_1;
	public CustInfoUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new CustInfoCenterNorthSearchPanel();
		panel.getBtnSearch().addActionListener(this);
		add(panel, BorderLayout.NORTH);
		
		panel_1 = new CustInfoCenterCenterTblPanel();
		try {
			panel_1.loadTableData(custService.showCustomers());
			panel_1.setPopupMenu(createPopup());
		} catch (SQLException e) {
			System.out.println("해당 고객이 없습니다.");
			e.printStackTrace();
		}
		add(panel_1, BorderLayout.CENTER);
	}
	private JPopupMenu createPopup() {
		JPopupMenu popup = new JPopupMenu();
		
		JMenuItem addMenu = new JMenuItem("추가");
		popup.add(addMenu);
		
		JMenuItem editMenu = new JMenuItem("수정");
		popup.add(editMenu);
		
		JMenuItem deleteMenu = new JMenuItem("삭제");
		popup.add(deleteMenu);
		return popup;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panel.getBtnSearch()) {
			panelBtnSearchActionPerformed(e);
		}
	}
	protected void panelBtnSearchActionPerformed(ActionEvent e) {
		String custName = panel.getTfSearch().getText();
		try {
			Customer newCust = custService.showCustomerByName(custName);
			if(custName.equals(newCust.getCustName())) {
				JOptionPane.showMessageDialog(null, "이미 조회된 고객입니다.");
			}
			
			listForCustName.add(newCust);
			panel_1.loadTableData(listForCustName);
		} catch (SQLException e1) {
			System.out.println("해당 고객이 없습니다.");
			e1.printStackTrace();
		}
	}
}
