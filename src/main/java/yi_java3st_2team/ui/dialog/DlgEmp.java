package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel;
import yi_java3st_2team.ui.table.EmpCenterTblPanel;

@SuppressWarnings("serial")
public class DlgEmp extends JDialog implements ActionListener{

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
	private Employee emp;
	private static DlgEmp dialog;
	private JButton btnOk;
	private JButton btnCancel;

	private Dimension picDimension=new Dimension(100, 150);
	private String picPath;
	
	private int basicPic = getClass().getClassLoader().getResource("no-img.png").getPath().length();
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
//		JOptionPane.showMessageDialog(null, basicPic);
//		int basicPic1 = getClass().getClassLoader().getResource("no-img.png").hashCode();
//		int iconLength = icon.getClass().getClassLoader().hashCode();
//		JOptionPane.showMessageDialog(null, basicPic1);
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 383);
		getContentPane().setLayout(new BorderLayout());
		{
			panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
	
		}
		panel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(20, 0, 20, 50));
		panel.add(contentPanel);
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
			pForPic = new JPanel();
			pForPic.setBorder(new EmptyBorder(20, 50, 20, 0));
			panel.add(pForPic, BorderLayout.WEST);
			pForPic.setLayout(new BoxLayout(pForPic, BoxLayout.Y_AXIS));
			{
				//사진부분
				lblPic = new JLabel();
				lblPic.setIcon(null);
				lblPic.setSize(new Dimension(100, 150));
				lblPic.setPreferredSize(new Dimension(100, 150));
				lblPic.setHorizontalAlignment(SwingConstants.CENTER);
				lblPic.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				setPic(getClass().getClassLoader().getResource("no-img.png").getPath());
				pForPic.add(lblPic);
			}
			{
				btnPic = new JButton("사원사진");
				btnPic.setMaximumSize(new Dimension(100, 23));
				btnPic.setPreferredSize(new Dimension(100, 23));
				pForPic.add(btnPic);
				
			}
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
		btnPic.addActionListener(this);
	}
	
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
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
		String empAuth = tfEmpAuth.getText().trim();  
		int empSalary = Integer.parseInt((tfEmpSalary.getText().trim()).replace(",", ""));
		String empTel = tfEmpTel.getText().trim();
		String empId = tfEmpId.getText().trim();
		String empPwd = tfEmpPwd.getText().trim();
		icon = (ImageIcon)lblPic.getIcon();
		byte[] pic = getImage(icon);
		//JOptionPane.showMessageDialog(null, pic.length);
		//lblPic.setIcon(new ImageIcon(pic));
		
        
		setPic(pic);
		//System.out.println(pic.hashCode()); //1255441253
		//System.out.println(pic.toString()); //[B@4ad48365
		//System.out.println(lblPic.getIcon().toString() +"이건?"); //javax.swing.ImageIcon@74ea2e99이건?
		Department dept = (Department)cmbDept.getSelectedItem();


		if(cmbDept.getSelectedIndex()== -1) {
			JOptionPane.showMessageDialog(null, "부서를 선택하세요");
			return null;
		}
		return new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept ,pic);
	}
	
	private byte[] getImage(ImageIcon icon) {
		// 노이미지일때 기본 사진 가져오는것
	  
	//if(icon != null)	
	byte[] pic = null;
	     File file = new File(picPath);
	     try(InputStream is = new FileInputStream(file)){
	    	 pic = new byte[is.available()]; //메모리 부족하면 반복문 돌려야한다 512바이트씩
	    	 is.read(pic); //읽어서 pic배열에 담아라
	     } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    		
		return pic;
	}
	private Department dept;
	private JPanel panel;
	private JPanel pForPic;
	private JLabel lblPic;
	private JButton btnPic;
	private ImageIcon icon;
	
	//다이얼로그의 값 update위해 가져오기
//		public Employee getItemForUpdate() {
//			String empCode = tfEmpCode.getText().trim();
//			String empName = tfEmpName.getText().trim();
//			int nameLength = empName.length();
//			if(nameLength >= 6) {
//				JOptionPane.showMessageDialog(null, "이름은 다섯자 이내입니다");
//				return null;
//			}
//			String empTitle = tfEmpTitle.getText().trim();
//			String empAuth = null;  //null로 넣어도 되는걸까
//			int empSalary = Integer.parseInt((tfEmpSalary.getText().trim()).replace(",", ""));
//			String empTel = tfEmpTel.getText().trim();
//			String empId = tfEmpId.getText().trim();
//			String empPwd = tfEmpPwd.getText().trim();
//			
//			int deptNum = cmbDept.getSelectedIndex();
//			System.out.println(deptNum +"deptNum");
//			if(deptNum ==0) {
//				System.out.println("0번 선택됨");
//				dept = new Department(1);
//				dept.setDeptName("인사");
//				
//			}if(deptNum ==1) {
//				dept = new Department(2);
//			}
//			
//			
//			Department dept = (Department)cmbDept.getSelectedItem();
//			System.out.println(dept +"GETiTEM에서 DEPT는");
//
//			if(cmbDept.getSelectedIndex()== -1) {
//				JOptionPane.showMessageDialog(null, "부서를 선택하세요");
//				return null;
//			}
//			
//			
//			//byte[] pic = lblPic.getIcon().toString()
//			return new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
//		}
//	
	

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
		tfEmpPwd.setText("**********");
		cmbDept.setSelectedIndex(item.getDept().getDeptNo()-1);
        //JOptionPane.showMessageDialog(null, item.getPic().toString());
		
		if(item.getPic() == null) {
	    	setPic(getClass().getClassLoader().getResource("no-img.png").getPath());
	    }else {
	    	setPic(item.getPic()); //메소드 오버로딩 필요  //이게 null일 수 있음 
	    }
		
	}

	private void setPic(byte[] byteImg) {
		new ImageIcon(byteImg);
		lblPic.setIcon(new ImageIcon(new ImageIcon(byteImg).getImage().getScaledInstance((int)picDimension.getWidth(),
				(int)picDimension.getHeight(), Image.SCALE_DEFAULT)));
	  	 
		}
	
	private void setPic(String imgPath) {
		picPath = imgPath; //사진이 없다면 클릭하면 경로가 바뀔것
		lblPic.setIcon(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance((int)picDimension.getWidth(),
				(int)picDimension.getHeight(), Image.SCALE_DEFAULT)));
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPic) {
			btnPicActionPerformed(e);
		}
	}
	protected void btnPicActionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG or PNG or GIF", "jpg","png","gif");
		chooser.setFileFilter(filter);
		
		int res = chooser.showOpenDialog(null);
	    if(res != JFileChooser.APPROVE_OPTION) {
	    	JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다","경고",JOptionPane.WARNING_MESSAGE);
	    	return;
	    }
	    picPath = chooser.getSelectedFile().getPath();
	    setPic(picPath); //만든메소드
		
	}

	public JTextField getTfEmpCode() {
		return tfEmpCode;
	}

	public void setTfEmpCode(JTextField tfEmpCode) {
		this.tfEmpCode = tfEmpCode;
	}

	public void setEmpCode(String v, EmpCenterTblPanel empTblPanel) {
		//마지막 숫자 
		int lastIndex = empTblPanel.getLastIndex(); 
		tfEmpCode.setText(v+ String.format("%03d", lastIndex));
	}
	
	public void setComboDept(int deptno) {
		this.cmbDept.setSelectedIndex(deptno);
		
	}
	
	
	
}
