package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.panel.bankbook.CardCenterUIPanel;
import yi_java3st_2team.ui.service.CardService;

@SuppressWarnings("serial")
public class DlgCardAdd extends JDialog implements ActionListener, ItemListener {
	private final JPanel contentPanel = new JPanel();
	private JTextField tfCardNum;
	private JTextField tfCVS;
	private JDateChooser tfCardIssueDate;
	private JTextField tfCardLimit;
	private JTextField tfCardBalance;
	private JButton btnOk;
	private JButton btnCancel;
	private JComboBox<Customer> cmbCust;
	private JComboBox<Plan> cmbPlan;
	private Employee emp;
	private CardCenterUIPanel uiPanel;
	private CardService service;
	private JLabel lblCardBalance;
	private JLabel lblCardLimit;

	public DlgCardAdd() {
		initialize();
	}
	private void initialize() {
		service = new CardService();
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
		{
			JLabel lblCustName = new JLabel("고객이름");
			lblCustName.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblCustName);
		}
		{
			cmbCust = new JComboBox<>();
			cmbCust.addItemListener(this);
			cmbCust.setEditable(true);
			contentPanel.add(cmbCust);
		}
		{
			JLabel lblCardNum = new JLabel("카드번호");
			lblCardNum.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblCardNum);
		}
		{
			tfCardNum = new JTextField();
			tfCardNum.setColumns(10);
			contentPanel.add(tfCardNum);
		}
		{
			JLabel lblPlanName = new JLabel("상품명");
			lblPlanName.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblPlanName);
		}
		{
			cmbPlan = new JComboBox<>();
			cmbPlan.addItemListener(this);
			cmbPlan.setEditable(true);
			contentPanel.add(cmbPlan);
		}
		{
			JLabel lblCVC = new JLabel("카드보안코드");
			lblCVC.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblCVC);
		}
		{
			tfCVS = new JTextField();
			tfCVS.setColumns(10);
			contentPanel.add(tfCVS);
		}
		{
			JLabel lblCardIssueDate = new JLabel("카드발급일");
			lblCardIssueDate.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblCardIssueDate);
		}
		{
			tfCardIssueDate = new JDateChooser(new Date(),"yyyy-MM-dd HH:mm:ss");
			contentPanel.add(tfCardIssueDate);
		}
		{
			lblCardLimit = new JLabel("카드한도");
			lblCardLimit.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblCardLimit);
		}
		{
			tfCardLimit = new JTextField();
			tfCardLimit.setColumns(10);
			contentPanel.add(tfCardLimit);
		}
		{
			lblCardBalance = new JLabel("카드잔액");
			lblCardBalance.setVisible(false);
			lblCardBalance.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblCardBalance);
		}
		{
			tfCardBalance = new JTextField();
			tfCardBalance.setColumns(10);
			contentPanel.add(tfCardBalance);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
			{
				btnOk = new JButton();
				btnOk.addActionListener(this);
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("취소");
				btnCancel.addActionListener(this);
				buttonPane.add(btnCancel);
			}
		}
		try {
			List<Customer> custList = service.showCusts();
			DefaultComboBoxModel<Customer> cmbCustModel = new DefaultComboBoxModel<Customer>(new Vector<>(custList));
			cmbCust.setModel(cmbCustModel);
			cmbCust.setSelectedIndex(-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public CardCenterUIPanel getUiPanel() {
		return uiPanel;
	}
	public void setUiPanel(CardCenterUIPanel uiPanel) {
		this.uiPanel = uiPanel;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public JTextField getTfCardNum() {
		return tfCardNum;
	}

	public void setTfCardNum(JTextField tfCardNum) {
		this.tfCardNum = tfCardNum;
	}

	public JTextField getTfCVS() {
		return tfCVS;
	}

	public void setTfCVS(JTextField tfCVS) {
		this.tfCVS = tfCVS;
	}
	
	public JDateChooser getTfCardIssueDate() {
		return tfCardIssueDate;
	}

	public void setTfCardIssueDate(JDateChooser tfCardIssueDate) {
		this.tfCardIssueDate = tfCardIssueDate;
	}

	public JTextField getTfCardLimit() {
		return tfCardLimit;
	}

	public void setTfCardLimit(JTextField tfCardLimit) {
		this.tfCardLimit = tfCardLimit;
	}

	public JTextField getTfCardBalance() {
		return tfCardBalance;
	}

	public void setTfCardBalance(JTextField tfCardBalance) {
		this.tfCardBalance = tfCardBalance;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JComboBox<Customer> getCmbCust() {
		return cmbCust;
	}

	public void setCmbCust(JComboBox<Customer> cmbCust) {
		this.cmbCust = cmbCust;
	}

	public JComboBox<Plan> getCmbPlan() {
		return cmbPlan;
	}

	public void setCmbPlan(JComboBox<Plan> cmbPlan) {
		this.cmbPlan = cmbPlan;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public JLabel getLblCardBalance() {
		return lblCardBalance;
	}
	public void setLblCardBalance(JLabel lblCardBalance) {
		this.lblCardBalance = lblCardBalance;
	}
	public JLabel getLblCardLimit() {
		return lblCardLimit;
	}
	public void setLblCardLimit(JLabel lblCardLimit) {
		this.lblCardLimit = lblCardLimit;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}
	public Card getItem() {
		String cardNum = tfCardNum.getText();
		Customer custCode = (Customer)cmbCust.getSelectedItem();
		Plan planCode = (Plan)cmbPlan.getSelectedItem();
		String cardSecuCode = tfCVS.getText();
		Date cardIssueDate = tfCardIssueDate.getDate();
		Card card = new Card(cardNum, custCode, planCode, cardSecuCode, cardIssueDate);
		if(cardNum.substring(6, 7).equals("1")) {
			card.setCardBalance(tfCardBalance.getText().equals("")?Long.parseLong("0"):Long.parseLong(tfCardBalance.getText()));
		} 
		else {
			card.setCardLimit(tfCardLimit.getText().equals("")?Integer.parseInt("0"):Integer.parseInt(tfCardLimit.getText()));
		}
		card.setEmployee(emp);
		return card;
	}
	public void setItem(Card card) {
		tfCardNum.setText(card.getCardNum());
		cmbCust.setSelectedItem(card.getCustCode());
		cmbPlan.setSelectedItem(card.getPlanCode());
		tfCVS.setText(card.getCardSecuCode());
		tfCardIssueDate.setDate(card.getCardIssueDate());
		tfCardLimit.setText(card.getCardLimit()+"");
		tfCardBalance.setText(card.getCardBalance()+"");
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		cmbCust.setSelectedIndex(-1);
		tfCardNum.setText("");
		cmbPlan.setSelectedIndex(-1);
		tfCVS.setText("");
		tfCardIssueDate.setDate(new Date());
		tfCardLimit.setText("");
		tfCardBalance.setText("");
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbPlan) {
			cmbPlanItemStateChanged(e);
		}
		if (e.getSource() == cmbCust) {
			cmbCustItemStateChanged(e);
		}
	}
	protected void cmbCustItemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			try {
				Customer cust = (Customer)cmbCust.getSelectedItem();
				if(cust.getCustRank().equals("D")) {
					List<Plan> planList = service.showPlansByCard();
					DefaultComboBoxModel<Plan> cmbPlanModel = new DefaultComboBoxModel<Plan>(new Vector<>(planList));
					cmbPlan.setModel(cmbPlanModel);
					cmbPlan.setSelectedIndex(0);
				}
				else {
					List<Plan> planList = service.showPlansByCardNormal();
					DefaultComboBoxModel<Plan> cmbPlanModel = new DefaultComboBoxModel<Plan>(new Vector<>(planList));
					cmbPlan.setModel(cmbPlanModel);
					cmbPlan.setSelectedIndex(0);
				}
				
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	protected void cmbPlanItemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			if(tfCardNum.getText().substring(6,7).equals("2")) {
				lblCardLimit.setVisible(true);
				tfCardLimit.setVisible(true);
			}
		}
	}
}
