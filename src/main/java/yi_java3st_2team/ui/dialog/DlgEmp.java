package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class DlgEmp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfEmpCode;
	private JTextField tfEmpName;
	private JTextField tfEmpTitle;
	private JTextField tfEmpAuth;
	private JTextField tfEmpSalary;
	private JTextField tfEmpTel;
	private JTextField tfEmpId;
	private JTextField tfEmpPwd;
	private JComboBox<Department> cmbDept;
	
	//	
	private static DlgEmp dialog;
	private JButton btnOk;
	private JButton btnCancel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new DlgEmp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgEmp() {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
		{
			JLabel lblNewLabel = new JLabel("코드");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel);
		}
		{
			tfEmpCode = new JTextField();
			contentPanel.add(tfEmpCode);
			tfEmpCode.setColumns(10);
		}
		{
			JLabel label = new JLabel("이름");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			tfEmpName = new JTextField();
			tfEmpName.setColumns(10);
			contentPanel.add(tfEmpName);
		}
		{
			JLabel label = new JLabel("직책");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			tfEmpTitle = new JTextField();
			tfEmpTitle.setColumns(10);
			contentPanel.add(tfEmpTitle);
		}
		{
			JLabel label = new JLabel("권한");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			tfEmpAuth = new JTextField();
			tfEmpAuth.setEditable(false);
			tfEmpAuth.setColumns(10);
			contentPanel.add(tfEmpAuth);
		}
		{
			JLabel label = new JLabel("월급");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			tfEmpSalary = new JTextField();
			tfEmpSalary.setColumns(10);
			contentPanel.add(tfEmpSalary);
		}
		{
			JLabel label = new JLabel("연락처");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			tfEmpTel = new JTextField();
			tfEmpTel.setColumns(10);
			contentPanel.add(tfEmpTel);
		}
		{
			JLabel label = new JLabel("아이디");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			tfEmpId = new JTextField();
			tfEmpId.setColumns(10);
			contentPanel.add(tfEmpId);
		}
		{
			JLabel label = new JLabel("비밀번호");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			tfEmpPwd = new JTextField();
			tfEmpPwd.setColumns(10);
			contentPanel.add(tfEmpPwd);
		}
		{
			JLabel label = new JLabel("부서");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			cmbDept = new JComboBox();
			contentPanel.add(cmbDept);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
			{
				btnOk = new JButton("추가");
				btnOk.setActionCommand("추가");
		    	btnOk.addActionListener(myActionListener);
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("취소");
				btnCancel.setActionCommand("취소");
				btnCancel.addActionListener(myActionListener);
				buttonPane.add(btnCancel);
			}
		}
	}
	
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public JButton getBtnOk() {
		return btnOk;
	}	
	

	public JButton getBtnUpdate() {
		btnOk.setActionCommand("수정");
		btnOk.setText("수정");
		return btnOk;
	}
	
	public JButton setActionCommendToUpdate() {
		btnOk.setText("수정");
		btnOk.setActionCommand("수정");
		return btnOk;
	}
	
    public JButton setActionCommendClose() {
    	btnCancel.setText("닫기");
    	btnCancel.setActionCommand("닫기");
    	return btnCancel;
    }
	
	ActionListener myActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//다이얼로그 창의 확인과 취소
			if(e.getActionCommand() == "확인") {
				
				
			}if(e.getActionCommand() == "취소") {
				
			}
			
		}
	};
	
	public JTextField getTextField() {
		return tfEmpCode;
	}
	
	public void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<>(new Vector<>(deptList));
		cmbDept.setModel(model);
		cmbDept.setSelectedIndex(-1);
	}
	
	//다이얼로그의 값 insert위해 가져오기
	public Employee getItem() {
		String empCode = tfEmpCode.getText().trim();
		String empName = tfEmpName.getText().trim();
		int nameLength = empName.length();
		if(nameLength >= 6) {
			JOptionPane.showMessageDialog(null, "이름은 다섯자 이내입니다");
			return null;
		}
		String empTitle = tfEmpTitle.getText().trim();
		String empAuth = null;  //null로 넣어도 되는걸까
		int empSalary = Integer.parseInt((tfEmpSalary.getText().trim()).replace(",", ""));
		String empTel = tfEmpTel.getText().trim();
		String empId = tfEmpId.getText().trim();
		String empPwd = tfEmpPwd.getText().trim();
		

		
		Department dept = (Department)cmbDept.getSelectedItem();


		if(cmbDept.getSelectedIndex()== -1) {
			JOptionPane.showMessageDialog(null, "부서를 선택하세요");
			return null;
		}
		return new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
	}
	
	private Department dept;
	
	//다이얼로그의 값 update위해 가져오기
		public Employee getItemForUpdate() {
			String empCode = tfEmpCode.getText().trim();
			String empName = tfEmpName.getText().trim();
			int nameLength = empName.length();
			if(nameLength >= 6) {
				JOptionPane.showMessageDialog(null, "이름은 다섯자 이내입니다");
				return null;
			}
			String empTitle = tfEmpTitle.getText().trim();
			String empAuth = null;  //null로 넣어도 되는걸까
			int empSalary = Integer.parseInt((tfEmpSalary.getText().trim()).replace(",", ""));
			String empTel = tfEmpTel.getText().trim();
			String empId = tfEmpId.getText().trim();
			String empPwd = tfEmpPwd.getText().trim();
			
			int deptNum = cmbDept.getSelectedIndex();
			System.out.println(deptNum +"deptNum");
			if(deptNum ==0) {
				System.out.println("0번 선택됨");
				dept = new Department(1);
				dept.setDeptName("인사");
				
			}if(deptNum ==1) {
				dept = new Department(2);
			}
			
			
			Department dept = (Department)cmbDept.getSelectedItem();
			System.out.println(dept +"GETiTEM에서 DEPT는");

			if(cmbDept.getSelectedIndex()== -1) {
				JOptionPane.showMessageDialog(null, "부서를 선택하세요");
				return null;
			}
			return new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
		}
	
	

	public void clearTf() {
		tfEmpCode.setText("");
		tfEmpName.setText("");
		tfEmpTitle.setText("");
		tfEmpAuth.setText("");
		tfEmpSalary.setText("");
		tfEmpTel.setText("");
		tfEmpId.setText("");
		tfEmpPwd.setText("");
		cmbDept.setSelectedIndex(-1);
	}
	
	public void setItem(Employee item) {
		tfEmpCode.setText(item.getEmpCode());
		tfEmpCode.setEditable(false);  //수정버튼을 누르더라도 사원번호는 수정할 수 없도록 함
		tfEmpName.setText(item.getEmpName());
		tfEmpTitle.setText(item.getEmpTitle());
		tfEmpAuth.setText(item.getEmpAuth());
		tfEmpSalary.setText(item.getEmpSalary()+"");
		tfEmpTel.setText(item.getEmpTel());
		tfEmpId.setText(item.getEmpId());
		tfEmpPwd.setText(item.getEmpPwd());
		cmbDept.setSelectedIndex(item.getDept().getDeptNo()-1);
		
	}

	


	
}
