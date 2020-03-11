package yi_java3st_2team.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel2Work;
import yi_java3st_2team.ui.panel.EmpCenterUIpanelAuth;
import yi_java3st_2team.ui.panel.EmpStaticPanel;
import yi_java3st_2team.ui.service.CardService;
import yi_java3st_2team.ui.service.EmployeeService;
import yi_java3st_2team.ui.table.EmpCenterTblPanel2Work;

import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MainFrameHS extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPanel pCenter;
	private JPanel pNorth;
	private JPanel pcNorth;
	private JPanel pcCenter;
	private JLabel lblGreeting;
	private EmployeeService empService;
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
	private JMenuItem mntmEmpSearch;
	private JMenuItem mnEmpAuth;
	private JMenu mnEmpInfo;
	private JMenuItem mntmWorkInfo;
	private JMenuItem mntmStatistic;
	private JMenuItem mntmCustInfo;
	private JMenuItem mntmCustStatistic;
	private JMenuItem mntmCustPlan;
	private JMenuItem mntmDepositWithdraw;
	private JMenu mnBankBook;
	private JMenu mnCard;
	private JMenu mnLoan;
	private JMenuItem mntmBankBook;
	private JMenu mnBankBookSearch;
	private JMenuItem mntmBankBooTransInfo;
	private JMenuItem mntmBankBookStatistic;
	private JMenuItem mntmCard;
	private JMenu muCardSearch;
	private JMenuItem mntmCardTransInfo;
	private JMenuItem mntmCardStatistic;
	private JMenuItem mntmLoan;
	private JMenuItem mntmLoanSearch;

	
	//
	private EmpCenterUIpanel pEmpUIPanel;
	private EmpCenterUIpanel2Work pEmpUIPanel2;
	private EmpStaticPanel pEmpUIPanel3;
	private EmpCenterUIpanelAuth pEmpUIPanel4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameHS frame = new MainFrameHS();
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public MainFrameHS() {
		initialize();
	}
	private void initialize() {
		empService = new EmployeeService();
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
		
		mntmEmpSearch = new JMenuItem("사원 검색");
		
		mntmEmpSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmp.add(mntmEmpSearch);

		
		mnEmpInfo = new JMenu("사원 정보");
		mnEmpInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmp.add(mnEmpInfo);
		
		mntmWorkInfo = new JMenuItem("사원 업무 정보 조회");
		
		mntmWorkInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmpInfo.add(mntmWorkInfo);
		
		mntmStatistic = new JMenuItem("사원 현황 조회");
		
		mntmStatistic.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmpInfo.add(mntmStatistic);
		
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
		
		mntmCustInfo = new JMenuItem("고객 개인정보");
		mntmCustInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmCustInfo);
		
		mntmCustStatistic = new JMenuItem("고객 통계 정보");
		mntmCustStatistic.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmCustStatistic);
		
		mntmCustPlan = new JMenuItem("고객 상품관리");
		mntmCustPlan.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmCustPlan);
		
		mntmDepositWithdraw = new JMenuItem("입출금 관리");
		mntmDepositWithdraw.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCust.add(mntmDepositWithdraw);
		
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
		
		mnBankBook = new JMenu("통장 관리");
		mnBankBook.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankWork.add(mnBankBook);
		
		mntmBankBook = new JMenuItem("통장 관리");
		mntmBankBook.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankBook.add(mntmBankBook);
		
		mnBankBookSearch = new JMenu("통장 조회");
		mnBankBookSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankBook.add(mnBankBookSearch);
		
		mntmBankBooTransInfo = new JMenuItem("통장 거래 내역 조회");
		mntmBankBooTransInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankBookSearch.add(mntmBankBooTransInfo);
		
		mntmBankBookStatistic = new JMenuItem("통장 정보 조회");
		mntmBankBookStatistic.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankBookSearch.add(mntmBankBookStatistic);
		
		mnCard = new JMenu("카드 관리");
		mnCard.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankWork.add(mnCard);
		
		mntmCard = new JMenuItem("카드 관리");
		mntmCard.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCard.add(mntmCard);
		
		muCardSearch = new JMenu("카드 조회");
		muCardSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnCard.add(muCardSearch);
		
		mntmCardTransInfo = new JMenuItem("카드 거래 내역 조회");
		mntmCardTransInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		muCardSearch.add(mntmCardTransInfo);
		
		mntmCardStatistic = new JMenuItem("카드 정보 조회");
		mntmCardStatistic.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		muCardSearch.add(mntmCardStatistic);
		
		mnLoan = new JMenu("대출 관리");
		mnLoan.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnBankWork.add(mnLoan);
		
		mntmLoan = new JMenuItem("대출 관리");
		mntmLoan.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnLoan.add(mntmLoan);
		
		mntmLoanSearch = new JMenuItem("대출 조회");
		mntmLoanSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnLoan.add(mntmLoanSearch);
		
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
		
		
		//사원검색 액션리스너 밑쪽으로 빼기
		mntmEmpSearch.addActionListener(this);
		mntmWorkInfo.addActionListener(this);
		mntmStatistic.addActionListener(this);
		//사원권한
		mnEmpAuth.addActionListener(this);
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
		
	//사원 액션리스너
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnEmpAuth) {
			mnEmpAuthActionPerformed(e);
		}
		if (e.getSource() == mntmStatistic) {
			mntmStatisticActionPerformed(e);
		}
		if (e.getSource() == mntmWorkInfo) {
			mntmWorkInfoActionPerformed(e);
		}
		if (e.getSource() == mntmEmpSearch) {
			mntmEmpSearchActionPerformed(e); //사원관리 누르면 
		}
	}
	protected void mntmEmpSearchActionPerformed(ActionEvent e) {
		//센터 지우고  센터에 패널 모프시키기 
		pCenter.removeAll();
		pEmpUIPanel = new EmpCenterUIpanel();
        pCenter.add(pEmpUIPanel,BorderLayout.CENTER);
        repaint();
        revalidate();
	}
	//업무정보 조회 키 누르면 
	protected void mntmWorkInfoActionPerformed(ActionEvent e) {
		pCenter.removeAll();
		pEmpUIPanel2 = new EmpCenterUIpanel2Work();
        pCenter.add(pEmpUIPanel2,BorderLayout.CENTER);
        repaint();
        revalidate();
		
	}
	//사원 현황 누르면  - 통계연결
	protected void mntmStatisticActionPerformed(ActionEvent e) {
		pCenter.removeAll();
		pEmpUIPanel3 = new EmpStaticPanel();
        pCenter.add(pEmpUIPanel3,BorderLayout.CENTER);
        repaint();
        revalidate();
	}
	
	//사원권한 누르면
	protected void mnEmpAuthActionPerformed(ActionEvent e) {
		pCenter.removeAll();
		pEmpUIPanel4 = new EmpCenterUIpanelAuth();
        pCenter.add(pEmpUIPanel4,BorderLayout.CENTER);
        repaint();
        revalidate();
	}
	
}
