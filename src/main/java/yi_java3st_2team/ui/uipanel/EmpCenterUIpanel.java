package yi_java3st_2team.ui.uipanel;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import yi_java3st_2team.ui.panel.EmpCenterNorthSearchPanel;
import yi_java3st_2team.ui.service.EmployeeUIService;
import yi_java3st_2team.ui.table.EmpCenterTblPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpCenterUIpanel extends JPanel implements ActionListener {
	private EmployeeUIService service;
	private EmpCenterNorthSearchPanel pEmpSerch;
	
	
	public EmpCenterUIpanel() {
		service = new EmployeeUIService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pEmpSerch = new EmpCenterNorthSearchPanel();
	
		add(pEmpSerch);
		
		EmpCenterTblPanel pEmpTblPanel = new EmpCenterTblPanel();
	    //리스트불러오기
		pEmpTblPanel.loadTableData(service.showEmpList());
		add(pEmpTblPanel);
		
		
		pEmpSerch.getBtnSearch().addActionListener(this);
		pEmpSerch.getBtnCancel().addActionListener(this);
	}
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pEmpSerch.getBtnSearch()) {
			pEmpSerchBtnSearchActionPerformed(e);
		}
		if (e.getSource() == pEmpSerch.getBtnCancel()) {
			pEmpSerchBtnCancelActionPerformed(e);
		}
	}
	protected void pEmpSerchBtnCancelActionPerformed(ActionEvent e) {
		pEmpSerch.getTfSearch().setText("");
	}
	protected void pEmpSerchBtnSearchActionPerformed(ActionEvent e) {
		String eName = pEmpSerch.getTfSearch().getText();
		JOptionPane.showConfirmDialog(null, eName);
		
		
	}
}
