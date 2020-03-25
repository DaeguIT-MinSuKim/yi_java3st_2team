package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.dto.Loan;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.service.LoanService;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class DlgLoan extends JDialog implements ActionListener, ItemListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfAccountNum;
	private JDateChooser tfLoanDate;
	private JTextField tfLoanInterest;
	private JButton btnOk;
	private JButton btnCancel;
	private JComboBox<Customer> cmbCust;
	private JComboBox<Plan> cmbPlan;
	private JLabel lblLoanBalance;
	private JTextField tfLoanBalance;
	private Employee emp;
	private LoanService service;

	public DlgLoan() {
		initialize();
	}
	private void initialize() {
		service = new LoanService();
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
			JLabel lblAccountNum = new JLabel("계좌 번호");
			lblAccountNum.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblAccountNum);
		}
		{
			tfAccountNum = new JTextField();
			tfAccountNum.setColumns(10);
			contentPanel.add(tfAccountNum);
		}
		{
			JLabel lblPlanName = new JLabel("상품명");
			lblPlanName.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblPlanName);
		}
		{
			cmbPlan = new JComboBox<>();
			cmbPlan.setEditable(true);
			contentPanel.add(cmbPlan);
		}
		{
			JLabel lblLoanDate = new JLabel("대출날짜");
			lblLoanDate.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblLoanDate);
		}
		{
			tfLoanDate = new JDateChooser(new Date(),"yyyy-MM-dd HH:mm:ss");
			contentPanel.add(tfLoanDate);
		}
		{
			JLabel lblLoanInterest = new JLabel("이자율");
			lblLoanInterest.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblLoanInterest);
		}
		{
			tfLoanInterest = new JTextField();
			tfLoanInterest.setColumns(10);
			contentPanel.add(tfLoanInterest);
		}
		{
			lblLoanBalance = new JLabel("대출금액");
			lblLoanBalance.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblLoanBalance);
		}
		{
			tfLoanBalance = new JTextField();
			tfLoanBalance.setColumns(10);
			contentPanel.add(tfLoanBalance);
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
			try {
				List<Customer> custList = service.showCust();
				DefaultComboBoxModel<Customer> cmbCustModel = new DefaultComboBoxModel<Customer>(new Vector<>(custList));
				cmbCust.setModel(cmbCustModel);
				cmbCust.setSelectedIndex(-1);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public JTextField getTfAccountNum() {
		return tfAccountNum;
	}
	public void setTfAccountNum(JTextField tfAccountNum) {
		this.tfAccountNum = tfAccountNum;
	}
	public JDateChooser getTfLoanDate() {
		return tfLoanDate;
	}
	public void setTfLoanDate(JDateChooser tfLoanDate) {
		this.tfLoanDate = tfLoanDate;
	}
	public JTextField getTfLoanInterest() {
		return tfLoanInterest;
	}
	public void setTfLoanInterest(JTextField tfLoanInterest) {
		this.tfLoanInterest = tfLoanInterest;
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
	public JLabel getLblLoanBalance() {
		return lblLoanBalance;
	}
	public void setLblLoanBalance(JLabel lblLoanBalance) {
		this.lblLoanBalance = lblLoanBalance;
	}
	public JTextField getTfLoanBalance() {
		return tfLoanBalance;
	}
	public void setTfLoanBalance(JTextField tfLoanBalance) {
		this.tfLoanBalance = tfLoanBalance;
	}
	public JPanel getContentPanel() {
		return contentPanel;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}

	public Loan getItem() {
		String loanAccountNum = tfAccountNum.getText();
		Customer custCode = (Customer)cmbCust.getSelectedItem();
		Plan planCode = (Plan)cmbPlan.getSelectedItem();
		Date loanDate = tfLoanDate.getDate();
		float loanInterest = Float.parseFloat(tfLoanInterest.getText());
		long loanBalance = Long.parseLong(tfLoanBalance.getText());
		Loan loan = new Loan(loanAccountNum, custCode, planCode, loanDate, loanInterest, loanBalance);
		loan.setEmployee(emp);
		return loan;
	}
	public void setItem(Loan loan) {
		tfAccountNum.setText(loan.getLoanAccountNum());
		cmbCust.setSelectedItem(loan.getCustCode());
		cmbPlan.setSelectedItem(loan.getPlanCode());
		tfLoanDate.setDate(loan.getLoanDate());
		tfLoanInterest.setText(loan.getLoanInterest()+"");
		tfLoanBalance.setText(loan.getLoanBalance()+"");
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		cmbCust.setSelectedIndex(-1);
		tfAccountNum.setText("");
		cmbPlan.setSelectedIndex(-1);
		tfLoanDate.setDate(new Date());
		tfLoanInterest.setText("");
		tfLoanBalance.setText("");
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbCust) {
			cmbCustItemStateChanged(e);
		}
	}
	protected void cmbCustItemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			Customer cust = (Customer)cmbCust.getSelectedItem();
			if(cust.getCustRank().equals("D")) {
				try {
					List<Plan> planList = service.showPlanByLoan();
					DefaultComboBoxModel<Plan> cmbPlanModel = new DefaultComboBoxModel<Plan>(new Vector<>(planList));
					cmbPlan.setModel(cmbPlanModel);
					cmbPlan.setSelectedIndex(0);
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			else {
				try {
					List<Plan> planList = service.showPlanByLoanNormal();
					DefaultComboBoxModel<Plan> cmbPlanModel = new DefaultComboBoxModel<Plan>(new Vector<>(planList));
					cmbPlan.setModel(cmbPlanModel);
					cmbPlan.setSelectedIndex(0);
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
