package yi_java3st_2team.uiDesign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class MainFrameNoAuthCust extends JFrame {

	private JPanel contentPane;
	private JPanel pCenter;
	private JPanel pNorth;
	private JPanel pEmployee;
	private JPanel pCust;
	private JPanel pEmpAuth;
	private JPanel pImg;
	private JLabel lblImg;
	private JLabel lblNewLabel;
	private JPanel pWest;
	private JLabel lblCust;
	private JLabel lblAuth;
	private DlgEmp dlgEmp;
	private JPanel pLog;
	private JPanel pMainLogo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameNoAuthCust frame = new MainFrameNoAuthCust();
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "권한이 없습니다.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrameNoAuthCust() {
		initialize();
	}
	private void initialize() {
		dlgEmp = new DlgEmp();
		setTitle("YN Bank 직원 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pNorth = new JPanel();
		contentPane.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new GridLayout(0, 4, 0, 0));
		
		pImg = new JPanel();
		pImg.setBackground(new Color(18, 66, 43));
		pNorth.add(pImg);
		pImg.setLayout(new BorderLayout(0, 0));
		
		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon("D:\\workspace_gradle\\yi_java3st_2team\\images\\logo.png"));
		lblImg.setBackground(new Color(18, 66, 43));
		pImg.add(lblImg, BorderLayout.CENTER);
		
		pEmployee = new JPanel();
		pNorth.add(pEmployee);
		pEmployee.setLayout(new BorderLayout(0, 0));
		pEmployee.setBackground(new Color(18,66,43));
		
		lblNewLabel = new JLabel("사원");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pEmployee.add(lblNewLabel, BorderLayout.SOUTH);
		
		pCust = new JPanel();
		pNorth.add(pCust);
		pCust.setLayout(new BorderLayout(0, 0));
		pCust.setBackground(new Color(18,66,43));
		
		lblCust = new JLabel("고객정보");
		lblCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblCust.setForeground(new Color(254,208,64));
		lblCust.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		pCust.add(lblCust, BorderLayout.SOUTH);
		
		pEmpAuth = new JPanel();
		pNorth.add(pEmpAuth);
		pEmpAuth.setLayout(new BorderLayout(0, 0));
		pEmpAuth.setBackground(new Color(18,66,43));
		
		lblAuth = new JLabel("사원권한");
		lblAuth.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuth.setForeground(Color.WHITE);
		lblAuth.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		pEmpAuth.add(lblAuth, BorderLayout.SOUTH);
		
		pCenter = new JPanel();
		pCenter.setForeground(new Color(255, 255, 255));
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		pLog = new JPanel();
		pLog.setBackground(Color.WHITE);
		pCenter.add(pLog, BorderLayout.NORTH);
		
		lblNewLabel_2 = new JLabel("User님 환영합니다");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pLog.add(lblNewLabel_2);
		
		btnNewButton = new JButton("로그아웃");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pLog.add(btnNewButton);
		
		pMainLogo = new JPanel();
		pMainLogo.setBackground(Color.WHITE);
		pCenter.add(pMainLogo, BorderLayout.CENTER);
		pMainLogo.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon("D:\\workspace_gradle\\yi_java3st_2team\\images\\mLogo.png").getImage().getScaledInstance(600, 300, 1)));
		pMainLogo.add(lblNewLabel_1);
		
		pWest = new JPanel();
		contentPane.add(pWest, BorderLayout.WEST);
		pWest.setLayout(new GridLayout(5, 0, 0, 0));
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(50, 50, 50, 50));
		panel.setBackground(new Color(18, 66, 43));
		pWest.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(18, 66, 43));
		pWest.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(18, 66, 43));
		pWest.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(18, 66, 43));
		pWest.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(18, 66, 43));
		pWest.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
	}

}
