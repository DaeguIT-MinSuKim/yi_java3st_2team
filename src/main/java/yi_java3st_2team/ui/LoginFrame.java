package yi_java3st_2team.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

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

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.service.LoginService;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPanel pCenter;
	private JPanel pcNorth;
	private JLabel lblBankImg; 
	private JLabel lblGreeting;
	private JPanel pcCenter;
	private JLabel lblNId;
	private JTextField tfId;
	private JLabel lblPass;
	private JPasswordField pfPass;
	private JButton btnLogin;
	private JPanel panel;
	private LoginService service;
	private boolean chkLogin;
	private MainFrame main;
	private Employee chkEmp;
	private JButton btnLogout;
	private JPanel panel_1;
	private static LoginFrame frame;
	private String mainMessage;
	private
	
	
	JPanel pNorthForLogo;
	private JLabel lblLogoImg;
	private JPanel panel_2;
	private JPanel panel_3;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		
			public void run() {
				try {
					frame = new LoginFrame();
					
					
					//UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
		setResizable(false);
		service = new LoginService();
		main = new MainFrame();
		main.setResizable(false);
		main.setTitle(mainMessage);
		main.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				main.setClear();
				main.getLoginFrame().setVisible(true);
				tfId.setText("");
				pfPass.setText("");
			}
			
		});
		setTitle("YN Bank 직원 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pCenter = new JPanel();
		pCenter.setBackground(Color.WHITE);
		pCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		pCenter.setForeground(new Color(255, 255, 255));
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		pcNorth = new JPanel();
		pcNorth.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) pcNorth.getLayout();
		flowLayout.setHgap(0);
		pCenter.add(pcNorth, BorderLayout.NORTH);
		
		lblBankImg = new JLabel("");
		lblBankImg.setIcon(new ImageIcon(System.getProperty("user.dir")+"/images/bank.png"));
		lblBankImg.setBackground(Color.WHITE);
		lblBankImg.setSize(new Dimension(100, 50));
		lblBankImg.setPreferredSize(new Dimension(100, 50));
		lblBankImg.setBorder(new EmptyBorder(0, 0, 0, 0));
		pcNorth.add(lblBankImg);
		
		lblGreeting = new JLabel("Welcome!!");
		lblGreeting.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		lblGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		pcNorth.add(lblGreeting);
		
		pcCenter = new JPanel();
		pcCenter.setBackground(Color.WHITE);
		pcCenter.setBorder(new EmptyBorder(20, 0, 20, 20));
		pCenter.add(pcCenter, BorderLayout.CENTER);
		pcCenter.setLayout(new GridLayout(0, 3, 10, 10));
		
		lblNId = new JLabel("User Id");
		lblNId.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNId.setHorizontalAlignment(SwingConstants.RIGHT);
		pcCenter.add(lblNId);
		
		tfId = new JTextField();
		tfId.setBackground(SystemColor.menu);
		tfId.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pcCenter.add(tfId);
		tfId.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pcCenter.add(panel_2);
		
		
		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		pcCenter.add(lblPass);
		
		pfPass = new JPasswordField();
		pfPass.setBackground(SystemColor.menu);
		pfPass.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pcCenter.add(pfPass);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setVisible(false);
		pcCenter.add(panel_1);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		pcCenter.add(panel_3);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pcCenter.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnLogin = new JButton("Login");
		btnLogin.setBorder(null);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnLogin.setBackground(SystemColor.activeCaption);
		panel.add(btnLogin);
		
		pNorthForLogo = new JPanel();
		pNorthForLogo.setBackground(Color.WHITE);

		pCenter.add(pNorthForLogo, BorderLayout.SOUTH);
		
		lblLogoImg = new JLabel("");
		lblLogoImg.setForeground(Color.WHITE);
		lblLogoImg.setOpaque(true);
		lblLogoImg.setBackground(Color.WHITE);
		lblLogoImg.setSize(new Dimension(520, 100));
		lblLogoImg.setPreferredSize(new Dimension(520, 100));
		lblLogoImg.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblLogoImg.setIcon(new ImageIcon(System.getProperty("user.dir")+"/images/banner1.jpg"));
		pNorthForLogo.add(lblLogoImg);
		
		
	    btnLogout = main.getBtnMenuLogout();
	    btnLogout.addActionListener(this);
		btnLogin.addActionListener(this);
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
	protected void btnLoginActionPerformed(ActionEvent e) {
		try {
			Employee emp = new Employee(tfId.getText().trim(),new String(pfPass.getPassword()).trim());
			chkEmp = service.GetLoginInfo(emp);
			if(chkEmp.getEmpName()==null && chkEmp.getEmpPwd()==null) {
				JOptionPane.showMessageDialog(null, "아이디나 비밀번호가 틀렸습니다. 다시 확인해주세요");
				return;
			}
			chkLogin = true;
			if(chkLogin) {
				JOptionPane.showMessageDialog(null, "로그인이 성공하였습니다.");
				main.setLoginFrame(frame);
				frame.setVisible(false);
				mainMessage = chkEmp.getEmpName() + "님 환영합니다~";
				main.setTitle("YN Bank 직원 프로그램 : "+mainMessage);  //타이틀에 로그인된 회원 정보 표시하기
				main.initEmpAuth(chkEmp.getEmpName());
				main.getCenterPanel();
				main.setVisible(true);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
}
