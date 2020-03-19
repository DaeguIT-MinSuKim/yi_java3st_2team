package yi_java3st_2team.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.service.LoginService;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Component;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPanel pCenter;
	private JPanel pNorth;
	private JPanel pImg;
	private JLabel lblINorthLogo;
	private JPanel pcNorth;
	private JLabel lblBankImg;
	private JLabel lblGreeting;
	private JPanel pcCenter;
	private JLabel lblNId;
	private JTextField tfId;
	private JLabel lblPass;
	private JPasswordField pfPass;
	private JButton btnLogin;
	private JButton btnCheck;
	private JPanel panel;
	private LoginService service;
	private boolean chkLogin;
	private MainFrame main;
	private Employee chkEmp;
	private JButton btnLogout;
	private JPanel panel_1;
	
	private static LoginFrame frame;
	private String mainMessage;
	private JPanel pSouthForLogo;
	private JLabel lblLoginImg;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		

			public void run() {
				try {
					frame = new LoginFrame();
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		initialize();
	}
	private void initialize() {
		service = new LoginService();
		main = new MainFrame();
		//main.setTitle(mainMessage);
		main.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				main.setClear();
			}
			
		});
		setTitle("YN Bank 직원 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pNorth = new JPanel();
		pNorth.setBackground(Color.WHITE);
		contentPane.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new GridLayout(0, 2, 0, 0));
		
		pImg = new JPanel();
		pImg.setBackground(Color.WHITE);
		pNorth.add(pImg);
		pImg.setLayout(new BorderLayout(0, 0));
		
		lblINorthLogo = new JLabel("");
		lblINorthLogo.setIcon(new ImageIcon(new ImageIcon("D:\\workspace_gradle\\yi_java3st_2team\\images\\logo.png").getImage().getScaledInstance(200, 70, 1)));
		lblINorthLogo.setBackground(Color.WHITE);
		pImg.add(lblINorthLogo, BorderLayout.CENTER);
		
		pCenter = new JPanel();
		pCenter.setBackground(Color.WHITE);
		pCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		pCenter.setForeground(new Color(255, 255, 255));
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		pcNorth = new JPanel();
		pcNorth.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) pcNorth.getLayout();
		flowLayout.setHgap(20);
		pCenter.add(pcNorth, BorderLayout.NORTH);
		
		lblBankImg = new JLabel("");
		lblBankImg.setIcon(new ImageIcon(new ImageIcon("D:\\workspace_gradle\\yi_java3st_2team\\images\\money.png").getImage().getScaledInstance(65, 65, 1)));
		pcNorth.add(lblBankImg);
		
		lblGreeting = new JLabel("Welcome!!");
		lblGreeting.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		lblGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		pcNorth.add(lblGreeting);
		
		pcCenter = new JPanel();
		pcCenter.setBackground(Color.WHITE);
		pcCenter.setBorder(new EmptyBorder(20, 0, 20, 20));
		pCenter.add(pcCenter, BorderLayout.CENTER);
		pcCenter.setLayout(new GridLayout(0, 2, 10, 30));
		
		lblNId = new JLabel("User Id");
		lblNId.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNId.setHorizontalAlignment(SwingConstants.RIGHT);
		pcCenter.add(lblNId);
		
		tfId = new JTextField();
		tfId.setBackground(SystemColor.menu);
		tfId.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pcCenter.add(tfId);
		tfId.setColumns(10);
		
		
		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		pcCenter.add(lblPass);
		
		pfPass = new JPasswordField();
		pfPass.setBackground(SystemColor.menu);
		pfPass.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pcCenter.add(pfPass);
		
		panel_1 = new JPanel();
		panel_1.setVisible(false);
		pcCenter.add(panel_1);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pcCenter.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnLogin = new JButton("Login");
		panel.add(btnLogin);
		btnLogin.addActionListener(this);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnLogin.setBackground(SystemColor.menu);
		
		pSouthForLogo = new JPanel();
		pCenter.add(pSouthForLogo, BorderLayout.WEST);
		pSouthForLogo.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblLoginImg = new JLabel("");
		lblLoginImg.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLoginImg.setForeground(Color.WHITE);
		lblLoginImg.setOpaque(true);
		lblLoginImg.setBackground(Color.WHITE);
		lblLoginImg.setSize(new Dimension(255, 200));
		lblLoginImg.setPreferredSize(new Dimension(255, 200));
		lblLoginImg.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		lblLoginImg.setIcon(new ImageIcon(System.getProperty("user.dir")+"/images/loginlogo2.jpg"));
		pSouthForLogo.add(lblLoginImg);
		
		
	    btnLogout = main.getBtnLogout();
	    btnLogout.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			btnLoginActionPerformed(e);
		}

		if (e.getSource() == btnLogout) {
			btnLogoutActionPerformed(e);
	//		JOptionPane.showMessageDialog(null, "이거눌림");
		}
	}
	protected void btnLogoutActionPerformed(ActionEvent e) {
		main.setClear();
		main.dispose();
		frame.setVisible(true);
		tfId.setText("");
	    pfPass.setText("");
		chkLogin = false;
		
	}


	@SuppressWarnings("static-access")
	protected void btnLoginActionPerformed(ActionEvent e) {
		
		try {
			Employee emp = new Employee(tfId.getText().trim(),new String(pfPass.getPassword()).trim());
			chkEmp = service.GetLoginInfo(emp);
			if(chkEmp.getEmpName()==null && chkEmp.getEmpPwd()==null) {
				JOptionPane.showMessageDialog(null, "아이디나 비밀번호가 틀렸습니다. 다시 확인해주세요");
				return;
			}
			tfId.setEditable(false);
			pfPass.setEditable(false);
			chkLogin = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(chkLogin) {
			JOptionPane.showMessageDialog(null, "로그인이 성공하였습니다.");
			main.setLoginFrame(frame);
			tfId.setEditable(true);
			pfPass.setEditable(true);
			frame.setVisible(false);
			main.getLblGreeting().setText(chkEmp.getEmpName() + "님 환영합니다~");
			
			mainMessage = chkEmp.getEmpName()+"님 환영합니다~";
		//	System.out.println(mainMessage);
			main.initEmpAuth(chkEmp.getEmpName());  
			
			main.setTitle("YN Bank 직원 프로그램 : "+mainMessage);  //타이틀에 로그인된 회원 정보 표시하기
			main.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "확인부터 먼저 해주세요");
		}
	}


	
	
}
