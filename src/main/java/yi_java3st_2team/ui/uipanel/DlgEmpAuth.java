package yi_java3st_2team.ui.uipanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class DlgEmpAuth extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfEmpCode;
	private JTextField tfEmpName;
	private JTextField tfEmpTitle;

	
	
	///
	private JButton btnOk;
	private JButton btnCancel;
	private static DlgEmpAuth dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new DlgEmpAuth();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgEmpAuth() {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
		{
			JLabel lblEmpCode = new JLabel("코드");
			lblEmpCode.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblEmpCode);
		}
		{
			tfEmpCode = new JTextField();
			tfEmpCode.setEditable(false);
			contentPanel.add(tfEmpCode);
			tfEmpCode.setColumns(10);
		}
		{
			JLabel lblEmpName = new JLabel("이름");
			lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblEmpName);
		}
		{
			tfEmpName = new JTextField();
			tfEmpName.setEditable(false);
			tfEmpName.setColumns(10);
			contentPanel.add(tfEmpName);
		}
		{
			JLabel lblEmpTitle = new JLabel("직책");
			lblEmpTitle.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblEmpTitle);
		}
		{
			tfEmpTitle = new JTextField();
			tfEmpTitle.setEditable(false);
			tfEmpTitle.setColumns(10);
			contentPanel.add(tfEmpTitle);
		}
		{
			JLabel lblEmpAuth = new JLabel("권한");
			lblEmpAuth.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblEmpAuth);
		}
		{
			cmbAuth = new JComboBox();
			cmbAuth.setModel(new DefaultComboBoxModel(new String[] {"CS", "AD", "HR"}));
			contentPanel.add(cmbAuth);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
			{
				btnOk = new JButton("수정");
				btnOk.setActionCommand("수정");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("취소");
				btnCancel.setActionCommand("취소");
				buttonPane.add(btnCancel);
			}
		}
	}
	
	//버튼들 외부에서 얻어올 수 있도록 
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public JButton getBtnOk() {
		return btnOk;
	}	
	
	
	ActionListener myActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//다이얼로그 창의 확인과 취소
			if(e.getActionCommand() == "확인") {
				
			}if(e.getActionCommand() == "취소") {
				dialog.setVisible(false); //아예 닫기는건 어떻게 하지?? 0222
			}
			
		}
	};
	private JComboBox cmbAuth;


	
	//다이얼로그의 값 insert위해 가져오기
		public Employee getItem(Employee emp) { //넣어서 새로운  emp생성....?
			String empCode = emp.getEmpCode();
			String empName = emp.getEmpName();
			String empTitle = emp.getEmpTitle();
			String empAuth = ((String) cmbAuth.getSelectedItem()).trim();
				
			int empSalary = emp.getEmpSalary();
			String empTel = emp.getEmpTel();
			String empId = emp.getEmpId();
			String empPwd = emp.getEmpPwd();
			Department dept = emp.getDept();
			
//            if(empAuth.equals("HR")|| empAuth.equals("CS")||empAuth.contentEquals("AD")) {
//            	return new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
//				
//			}
//            return null;
			
			return new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
		}
		


		public void clearTf() {
			cmbAuth.setSelectedIndex(-1);
			//권한 부분만 삭제하면 되기에
		}
		
		//수정 시 권한이 올라가도록
		public void setItem(Employee item) {
			tfEmpCode.setText(item.getEmpCode());
			tfEmpName.setText(item.getEmpName());
			tfEmpTitle.setText(item.getEmpTitle());
			cmbAuth.setSelectedItem(item.getEmpAuth());
	
		}

}
