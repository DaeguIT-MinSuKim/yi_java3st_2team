package yi_java3st_2team.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.service.CardService;
import yi_java3st_2team.ui.service.EmployeeService;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel contentPane;
	private JPanel pCenter;
	private JPanel pNorth;
	private JPanel pcNorth;
	private JPanel pcCenter;
	private JLabel lblGreeting;
	private EmployeeService empService;
	private CardService cardService;
	private Employee empAuth;
	private JPanel pImg;
	private JPanel pEmp;
	private JPanel pCust;
	private JPanel pBankWork;
	private JLabel lblLogo;
	private JMenuBar mnBarEmp;
	private JMenu mnEmp;
	private JMenuBar mnBarCust;
	private JMenuBar mnBarBankWork;
	private JMenu mnCust;
	private JMenu mnBankWork;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mnEmpAuth;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu menu;
	private JMenuItem mntmNewMenuItem_7;
	private JMenu mnNewMenu_4;
	private JMenuItem mntmNewMenuItem_10;
	private JMenuItem mntmNewMenuItem_11;
	private JMenuItem menuItem;
	private JMenu menu_1;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;


	public MainFrame() {
		initialize();
	}
	private void initialize() {
		empService = new EmployeeService();
		cardService = new CardService();
		setTitle("YN Bank 직원 프로그램");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pNorth = new JPanel();
		pNorth.setBackground(Color.WHITE);
		contentPane.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new GridLayout(0, 4, 0, 0));
		
		pImg = new JPanel();
		pImg.setBackground(Color.WHITE);
		pNorth.add(pImg);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(System.getProperty("user.dir")+"//images//Logo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		pImg.add(lblLogo);
		
		pEmp = new JPanel();
		pEmp.setBackground(Color.WHITE);
		pNorth.add(pEmp);
		pEmp.setLayout(new BorderLayout(0, 0));
		
		mnBarEmp = new JMenuBar();
		mnBarEmp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnBarEmp.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBarEmp.setBorder(UIManager.getBorder("Button.border"));
		mnBarEmp.setBackground(Color.WHITE);
		pEmp.add(mnBarEmp, BorderLayout.CENTER);
		
		mnEmp = new JMenu("사원 관리");
		mnEmp.setBackground(Color.WHITE);
		mnEmp.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mnEmp.setHorizontalAlignment(SwingConstants.CENTER);
		mnBarEmp.add(mnEmp);
		
		mntmNewMenuItem = new JMenuItem("사원 검색");
		mntmNewMenuItem.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmp.add(mntmNewMenuItem);
		
		mnNewMenu = new JMenu("사원 정보");
		mnNewMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmp.add(mnNewMenu);
		
		mntmNewMenuItem_1 = new JMenuItem("사원 업무 정보 조회");
		mntmNewMenuItem_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("사원 현황 조회");
		mntmNewMenuItem_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		mnEmpAuth = new JMenuItem("사원 권한");
		mnEmpAuth.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmp.add(mnEmpAuth);
		
		pCust = new JPanel();
		pCust.setBackground(Color.WHITE);
		pNorth.add(pCust);
		pCust.setLayout(new BorderLayout(0, 0));
		
		mnBarCust = new JMenuBar();
		mnBarCust.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnBarCust.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBarCust.setBorder(UIManager.getBorder("Button.border"));
		mnBarCust.setBackground(Color.WHITE);
		pCust.add(mnBarCust, BorderLayout.CENTER);
		
		mnCust = new JMenu("고객 정보 관리");
		mnCust.setBackground(Color.WHITE);
		mnCust.setHorizontalAlignment(SwingConstants.CENTER);
		mnCust.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mnBarCust.add(mnCust);
		
		mntmNewMenuItem_3 = new JMenuItem("고객 개인정보");
		mntmNewMenuItem_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_4 = new JMenuItem("고객 통계 정보");
		mntmNewMenuItem_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_5 = new JMenuItem("고객 상품관리");
		mntmNewMenuItem_5.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmNewMenuItem_5);
		
		mntmNewMenuItem_6 = new JMenuItem("입출금 관리");
		mntmNewMenuItem_6.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmNewMenuItem_6);
		
		pBankWork = new JPanel();
		pBankWork.setBackground(Color.WHITE);
		pNorth.add(pBankWork);
		pBankWork.setLayout(new BorderLayout(0, 0));
		
		mnBarBankWork = new JMenuBar();
		mnBarBankWork.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnBarBankWork.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBarBankWork.setBorder(UIManager.getBorder("Button.border"));
		mnBarBankWork.setBackground(Color.WHITE);
		pBankWork.add(mnBarBankWork, BorderLayout.CENTER);
		
		mnBankWork = new JMenu("은행 업무 관리");
		mnBankWork.setBackground(SystemColor.menu);
		mnBankWork.setHorizontalAlignment(SwingConstants.CENTER);
		mnBankWork.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mnBarBankWork.add(mnBankWork);
		
		mnNewMenu_1 = new JMenu("통장 관리");
		mnNewMenu_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankWork.add(mnNewMenu_1);
		
		mntmNewMenuItem_7 = new JMenuItem("통장 관리");
		mntmNewMenuItem_7.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		mnNewMenu_4 = new JMenu("통장 조회");
		mnNewMenu_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu_1.add(mnNewMenu_4);
		
		mntmNewMenuItem_10 = new JMenuItem("통장 거래 내역 조회");
		mntmNewMenuItem_10.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu_4.add(mntmNewMenuItem_10);
		
		mntmNewMenuItem_11 = new JMenuItem("통장 정보 조회");
		mntmNewMenuItem_11.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu_4.add(mntmNewMenuItem_11);
		
		mnNewMenu_2 = new JMenu("카드 관리");
		mnNewMenu_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankWork.add(mnNewMenu_2);
		
		menuItem = new JMenuItem("카드 관리");
		menuItem.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu_2.add(menuItem);
		
		menu_1 = new JMenu("카드 조회");
		menu_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnNewMenu_2.add(menu_1);
		
		menuItem_1 = new JMenuItem("카드 거래 내역 조회");
		menuItem_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		menu_1.add(menuItem_1);
		
		menuItem_2 = new JMenuItem("카드 정보 조회");
		menuItem_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		menu_1.add(menuItem_2);
		
		menu = new JMenu("대출 관리");
		menu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankWork.add(menu);
		
		menuItem_3 = new JMenuItem("대출 관리");
		menuItem_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		menu.add(menuItem_3);
		
		menuItem_4 = new JMenuItem("대출 조회");
		menuItem_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		menu.add(menuItem_4);
		
		pCenter = new JPanel();
		pCenter.setBackground(Color.WHITE);
		pCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		pCenter.setForeground(new Color(255, 255, 255));
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		pcNorth = getLoginPanel();
		pcCenter = getMainLogoPanel();
		pCenter.add(pcNorth,BorderLayout.NORTH);
		pCenter.add(pcCenter,BorderLayout.CENTER);
	}
	
	private JPanel getMainLogoPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		String imgPath = System.getProperty("user.dir") + "//images//MainLogo.png";
		JLabel mainLogo = new JLabel(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(700, 250, 1)));
		panel.add(mainLogo);
		return panel;
	}
	private JPanel getLoginPanel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		lblGreeting = new JLabel();
		lblGreeting.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMenuClear();
				dispose();
			}
		});
		panel.add(lblGreeting);
		panel.add(btnLogout);
		return panel;
	}
	public void initEmpAuth() {
		try {
			Employee emp = new Employee();
			emp.setEmpName(lblGreeting.getText().substring(0,lblGreeting.getText().indexOf("님")));
			if(empAuth==null) {
				empAuth = empService.GetEmpAuth(emp);
				empAuth.setEmpName(lblGreeting.getText().substring(0,lblGreeting.getText().indexOf("님")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setMenuClear() {
		
	}

	public JLabel getLblGreeting() {
		return lblGreeting;
	}
}
