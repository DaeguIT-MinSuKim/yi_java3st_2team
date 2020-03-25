package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CardService;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class DlgBankBookMod extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField tfAccountNum;
	private JDateChooser tfAccountOpenDate;
	private JTextField tfAccountInterest;
	private JButton btnOk;
	private JButton btnCancel;
	private JComboBox<Customer> cmbCust;
	private JComboBox<Plan> cmbPlan;
	private Employee emp;
	private BankBookService service;
	private List<Customer> custList;
	public DlgBankBookMod() {
		initialize();
	}
	private void initialize() {
		service = new BankBookService();
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
			JLabel lblAccountOpenDate = new JLabel("계좌개설일");
			lblAccountOpenDate.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblAccountOpenDate);
		}
		{
			tfAccountOpenDate = new JDateChooser(new Date(),"yyyy-MM-dd HH:mm:ss");
			contentPanel.add(tfAccountOpenDate);
		}
		{
			JLabel lblAccountInterest = new JLabel("이자율");
			lblAccountInterest.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblAccountInterest);
		}
		{
			tfAccountInterest = new JTextField();
			tfAccountInterest.setColumns(10);
			contentPanel.add(tfAccountInterest);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
			{
				btnOk = new JButton();
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("취소");
				buttonPane.add(btnCancel);
			}
			try {
				custList = service.showCustomers();
				DefaultComboBoxModel<Customer> cmbCustModel = new DefaultComboBoxModel<Customer>(new Vector<>(custList));
				cmbCust.setModel(cmbCustModel);
				List<Plan> planList = service.showPlanByBankBook();
				DefaultComboBoxModel<Plan> cmbPlanModel = new DefaultComboBoxModel<Plan>(new Vector<>(planList));
				cmbPlan.setModel(cmbPlanModel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	public JTextField getTfCardNum() {
		return tfAccountNum;
	}

	public void setTfCardNum(JTextField tfCardNum) {
		this.tfAccountNum = tfCardNum;
	}

	public JDateChooser getTfCardIssueDate() {
		return tfAccountOpenDate;
	}

	public void setTfCardIssueDate(JDateChooser tfCardIssueDate) {
		this.tfAccountOpenDate = tfCardIssueDate;
	}

	public JTextField getTfCardLimit() {
		return tfAccountInterest;
	}

	public void setTfCardLimit(JTextField tfCardLimit) {
		this.tfAccountInterest = tfCardLimit;
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}

	public BankBook getItem() {
		String accountNum = tfAccountNum.getText();
		Customer custCode = (Customer)cmbCust.getSelectedItem();
		Plan accountPlanCode = (Plan)cmbPlan.getSelectedItem();
		Date accountOpenDate = tfAccountOpenDate.getDate();
		Float accountInterest = Float.parseFloat(tfAccountInterest.getText());
		BankBook bankbook = new BankBook(accountNum, custCode, accountPlanCode, accountOpenDate, accountInterest);
		bankbook.setEmployee(emp);
		return bankbook;
	}
	public void setItem(BankBook bankbook) {
		tfAccountNum.setText(bankbook.getAccountNum());
		cmbCust.setSelectedItem(bankbook.getCustCode());
		cmbPlan.setSelectedItem(bankbook.getAccountPlanCode());
		tfAccountOpenDate.setDate(bankbook.getAccountOpenDate());
		tfAccountInterest.setText(bankbook.getAccountInterest()+"");
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		cmbCust.setSelectedIndex(-1);
		tfAccountNum.setText("");
		cmbPlan.setSelectedIndex(-1);
		tfAccountOpenDate.setDate(new Date());
		tfAccountInterest.setText("");

	}
}
