package yi_java3st_2team.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.chart.PanelBarChart;
import yi_java3st_2team.ui.chart.PanelBarChartBankBookDeposit;
import yi_java3st_2team.ui.chart.PanelCustNumAll;
import yi_java3st_2team.ui.chart.PanelDPsLoanAllBarChart;
import yi_java3st_2team.ui.chart.PanelEmpPieChartForCountEmp;
import yi_java3st_2team.ui.chart.PanelMonthlyDepositOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelMonthlyDpOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelMonthlySvOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelMonthlyWithDrawalOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelPieChart;
import yi_java3st_2team.ui.panel.BankBookCenterUIPanel;
import yi_java3st_2team.ui.panel.BankBookTransInfoNorthPanel;
import yi_java3st_2team.ui.panel.BankBookTransInfoWestMenuPanel;
import yi_java3st_2team.ui.panel.CardCenterUIPanel;
import yi_java3st_2team.ui.panel.CardTransInfoNorthPanel;
import yi_java3st_2team.ui.panel.CardTransInfoWestMenuPanel;
import yi_java3st_2team.ui.panel.CustDWUIPanel;
import yi_java3st_2team.ui.panel.CustInfoUIPanel;
import yi_java3st_2team.ui.panel.CustPlanUIPanel;
import yi_java3st_2team.ui.panel.CustStatistic_CenterPanel;
import yi_java3st_2team.ui.panel.CustStatistic_NorthPanel_CustNum;
import yi_java3st_2team.ui.panel.CustStatistic_NorthPanel_DPWD;
import yi_java3st_2team.ui.panel.CustStatistic_NorthPanel_DepositSaving;
import yi_java3st_2team.ui.panel.CustStatistic_WestPanel;
import yi_java3st_2team.ui.panel.EmpBest;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel2Work;
import yi_java3st_2team.ui.panel.EmpCenterUIpanelAuth;
import yi_java3st_2team.ui.panel.EmpStaticPanel;
import yi_java3st_2team.ui.panel.EmpStatistic_CenterPanel;
import yi_java3st_2team.ui.panel.EmpStatistic_WestPanel;
import yi_java3st_2team.ui.panel.LoanCenterUIPanel;
import yi_java3st_2team.ui.panel.NoticeUIPanel;
import yi_java3st_2team.ui.service.EmployeeService;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPanel pCenter;
	private JPanel pWest;
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
	private String greeting;
	private JButton btnLogout;
	private LoginFrame loginFrame;
	private JPanel pLogout;
	private JButton btnMenuLogout;
	private JPanel left;
	private JPanel right;
	private Thread chartThread;
	private PanelMonthlyDpOpenNumBarChart panel_chart_Deposit;
	private PanelMonthlySvOpenNumBarChart panel_chart_Saving;
	private PanelMonthlyDepositOpenNumBarChart penal_chart_DPnum;
	private PanelMonthlyWithDrawalOpenNumBarChart panel_chartWDnum;
	private PanelDPsLoanAllBarChart panel_chart_DPsLoan;
	private PanelBarChart panel_chart_custRankNum;
	private PanelPieChart panel_chart_custVIP;
	private CustStatistic_WestPanel cust_statistic_west;
	private CustStatistic_CenterPanel cust_statistic_center;
	private CustStatistic_NorthPanel_DepositSaving statistic_north_DepositSaving;
	private CustStatistic_NorthPanel_DPWD statistic_north_DPWD;
	private CustStatistic_NorthPanel_CustNum statistic_north_CustNum;
	private MouseAdapter menuAdapter;

	private EmpStatistic_WestPanel emp_statistic_west;
	private PanelCustNumAll statistic_CustNumAll;
	private EmpBest pBestEmp;
	private EmpStatistic_CenterPanel emp_statistic_center;
	private PanelEmpPieChartForCountEmp panelEmpPieChartForCountEmp;
	
	private BankBookTransInfoWestMenuPanel bankbook_statistic_west;
	private BankBookTransInfoNorthPanel transInfo_north_bankbook;
	private CardTransInfoWestMenuPanel transInfo_west_card;
	private CardTransInfoNorthPanel transInfo_north_card;
	private PanelBarChartBankBookDeposit bankBook_barChard_Deposit;
	public MainFrame() {
		initialize();
	}
	private void initialize() {
		empService = new EmployeeService();
		setThread();
		menuAdapter = getMouseAdapter();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1224, 700);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pNorth = new JPanel();
		pNorth.setBackground(Color.WHITE);
		contentPane.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new GridLayout(0, 5, 0, 0));
		pImg = new JPanel();
		pImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getCenterPanel();
			}
			
		});

		pImg.setBackground(Color.WHITE);
		pNorth.add(pImg);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(System.getProperty("user.dir")+"//images//Logo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		pImg.add(lblLogo);
		
		pEmp = new JPanel();
		pEmp.setBorder(new EmptyBorder(0, 90, 0, 0));
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
		mnEmp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(empAuth.getEmpAuth().equals("CS")) {
					JOptionPane.showMessageDialog(null, "권한이 없습니다");
					setMenuDisabled(e);
				}
			}
			
		});
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
		mntmStatistic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pWest!=null) {
					contentPane.remove(pWest);
				}
				contentPane.remove(pCenter);
				pCenter = new JPanel(new BorderLayout());
				emp_statistic_west = new EmpStatistic_WestPanel();
				pWest = new JPanel(new BorderLayout());
				JPanel[] menuPanels = emp_statistic_west.getPanels();
				for(JPanel pMenu : menuPanels) {
					pMenu.addMouseListener(menuAdapter);
				}
				pWest.add(emp_statistic_west,BorderLayout.WEST);
				contentPane.add(pWest,BorderLayout.WEST);
				contentPane.add(pCenter,BorderLayout.CENTER);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		mnEmpInfo.add(mntmStatistic);
		
		mnEmpAuth = new JMenuItem("사원 권한");
		mnEmpAuth.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mnEmp.add(mnEmpAuth);
		
		pCust = new JPanel();
		pCust.setBorder(new EmptyBorder(0, 70, 0, 0));
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
		mnCust.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(empAuth.getEmpAuth().equals("HR")) {
					JOptionPane.showMessageDialog(null, "권한이 없습니다");
					setMenuDisabled(e);
				}
			}
			
		});
		mnCust.setBackground(Color.WHITE);
		mnCust.setHorizontalAlignment(SwingConstants.CENTER);
		mnCust.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mnBarCust.add(mnCust);
		
		mntmCustInfo = new JMenuItem("고객 개인정보");
		mntmCustInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mntmCustInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				greeting = lblGreeting.getText();
				if(cust_statistic_west!=null) {
					contentPane.remove(cust_statistic_west);
				}
				contentPane.remove(pCenter);
				pCenter = new CustInfoUIPanel();
				contentPane.add(pCenter,BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		mnCust.add(mntmCustInfo);
		
		mntmCustStatistic = new JMenuItem("고객 통계 정보");
		mntmCustStatistic.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mntmCustStatistic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cust_statistic_west!=null) {
					contentPane.remove(cust_statistic_west);
				}
				contentPane.remove(pCenter);
				pCenter = new JPanel(new BorderLayout());
				cust_statistic_west = new CustStatistic_WestPanel();
				JPanel[] menuPanels = cust_statistic_west.getPanels();
				for(JPanel pMenu : menuPanels) {
					pMenu.addMouseListener(menuAdapter);
				}
				contentPane.add(cust_statistic_west,BorderLayout.WEST);
				contentPane.add(pCenter,BorderLayout.CENTER);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		
		mnCust.add(mntmCustStatistic);
		
		mntmCustPlan = new JMenuItem("고객 상품관리");
		mntmCustPlan.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mntmCustPlan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				greeting = lblGreeting.getText();
				if(cust_statistic_west!=null) {
					contentPane.remove(cust_statistic_west);
				}
				contentPane.remove(pCenter);
				pCenter = new CustPlanUIPanel();
				contentPane.add(pCenter,BorderLayout.CENTER);
				revalidate();
				repaint();
				
			}
		});
		mnCust.add(mntmCustPlan);
		
		mntmDepositWithdraw = new JMenuItem("입출금 관리");
		mntmDepositWithdraw.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mntmDepositWithdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				greeting = lblGreeting.getText();
				if(cust_statistic_west!=null) {
					contentPane.remove(cust_statistic_west);
				}
				contentPane.remove(pCenter);
				pCenter = new CustDWUIPanel();
				contentPane.add(pCenter,BorderLayout.CENTER);
				revalidate();
				repaint();
				
			}
		});
		mnCust.add(mntmDepositWithdraw);
		
		pBankWork = new JPanel();
		pBankWork.setBorder(new EmptyBorder(0, 70, 0, 0));
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
		mnBankWork.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(empAuth.getEmpAuth().equals("HR")) {
					JOptionPane.showMessageDialog(null, "권한이 없습니다");
					setMenuDisabled(e);
				}
			}
			
		});
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
		
		pLogout = new JPanel();
		pLogout.setBackground(Color.WHITE);
		pLogout.setBorder(new EmptyBorder(20, 80, 20, 80));
		pNorth.add(pLogout);
		
		btnMenuLogout = new JButton("Logout");
		btnMenuLogout.setFocusPainted(false);
		btnMenuLogout.setBorder(null);
		//btnMenuLogout.setBorderPainted(false);
		btnMenuLogout.setBackground(SystemColor.activeCaption);
		btnMenuLogout.addActionListener(this);
		pLogout.setLayout(new BorderLayout(0, 0));
		pLogout.add(btnMenuLogout);
		
		pCenter = new JPanel();
		pCenter.setBackground(Color.WHITE);
		pCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		pCenter.setForeground(new Color(255, 255, 255));
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		//사원검색 액션리스너 밑쪽으로 빼기
		mntmEmpSearch.addActionListener(this);
		mntmWorkInfo.addActionListener(this);
		mntmStatistic.addActionListener(this);
		//사원권한
		mnEmpAuth.addActionListener(this);
		//은행업무관리
		mntmBankBook.addActionListener(this);
		mntmBankBooTransInfo.addActionListener(this);
		mntmBankBookStatistic.addActionListener(this);
		mntmCard.addActionListener(this);
		mntmCardTransInfo.addActionListener(this);
		mntmCardStatistic.addActionListener(this);
		mntmLoan.addActionListener(this);
		mntmLoanSearch.addActionListener(this);	
	}
	public JButton getBtnMenuLogout() {
		return btnMenuLogout;
	}
	public void setBtnMenuLogout(JButton btnMenuLogout) {
		this.btnMenuLogout = btnMenuLogout;
	}
	public LoginFrame getLoginFrame() {
		return loginFrame;
	}
	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	private void setTitle() {
		// TODO Auto-generated method stub
		
	}
	public JLabel getLblGreeting() {
		return lblGreeting;
	}
	private void setMenuDisabled(MouseEvent e) {
		if(empAuth.getEmpAuth().equals("CS")) {
			mnEmp.setEnabled(false);
			mntmEmpSearch.setEnabled(false);
			mnEmpInfo.setEnabled(false);
			mnEmpAuth.setEnabled(false);
		}
		else {
			JMenu menu = (JMenu)e.getSource();
			if(mnCust.equals(menu)) {
				mnCust.setEnabled(false);
				mntmCustInfo.setEnabled(false);
				mntmCustPlan.setEnabled(false);
				mntmCustStatistic.setEnabled(false);
				mntmDepositWithdraw.setEnabled(false);
			}
			else {
				mnBankWork.setEnabled(false);
				mnBankBook.setEnabled(false);
				mnCard.setEnabled(false);
				mnLoan.setEnabled(false);
			}
			
		}
	}
	public JPanel getMainLogoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2,20,20));
		panel.setBackground(Color.white);
		left = new JPanel();
		panel.add(left);
		left.setLayout(new BorderLayout(0, 0));
		
		pBestEmp = new EmpBest();
		left.add(pBestEmp);
		right = new JPanel();
		right.setLayout(new BorderLayout());
		NoticeUIPanel noUIPanel = new NoticeUIPanel();
		if(!empAuth.getEmpAuth().equals("AD")) {
			noUIPanel.getBtnMod().setVisible(false);
			noUIPanel.getBtnDel().setVisible(false);
			noUIPanel.getBtnAdd().setText("보기");
		}
		noUIPanel.setMain(this);
		right.add(noUIPanel,BorderLayout.CENTER);
		panel.add(right);
		return panel;
	}
	public JPanel getLoginPanel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		lblGreeting = new JLabel();
		lblGreeting.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnLogout = new JButton("로그아웃"); 
		btnLogout.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnLogout.addActionListener(this);
		panel.add(lblGreeting);
		panel.add(btnLogout);
		return panel;
	}
	
	public void setClear() {
		mnEmp.setEnabled(true);
		mntmEmpSearch.setEnabled(true);
		mnEmpInfo.setEnabled(true);
		mnEmpAuth.setEnabled(true);
		mnCust.setEnabled(true);
		mntmCustInfo.setEnabled(true);
		mntmCustPlan.setEnabled(true);
		mntmCustStatistic.setEnabled(true);
		mntmDepositWithdraw.setEnabled(true);
		mnBankWork.setEnabled(true);
		mnBankBook.setEnabled(true);
		mnCard.setEnabled(true);
		mnLoan.setEnabled(true);
		contentPane.remove(pCenter);
		pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		pCenter.setBackground(Color.white);
		pcNorth = getLoginPanel();
		pcCenter = getMainLogoPanel();
		pCenter.add(pcNorth,BorderLayout.NORTH);
		pCenter.add(pcCenter,BorderLayout.CENTER);
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	public JPanel getPcCenter() {
		return pcCenter;
	}
	public void setPcCenter(JPanel pcCenter) {
		this.pcCenter = pcCenter;
	}
	public JPanel getLeft() {
		return left;
	}
	public void setLeft(JPanel left) {
		this.left = left;
	}
	public JPanel getRight() {
		return right;
	}
	public void setRight(JPanel right) {
		this.right = right;
	}
	public JButton getBtnLogout() {
		return btnLogout;
	}
	public void initEmpAuth(String empName) {
		try {
			Employee emp = new Employee();
			emp.setEmpName(empName);
			empAuth = empService.GetEmpAuth(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//사원 액션리스너
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenuLogout || e.getSource() == btnLogout) {
			loginFrame.btnLogoutActionPerformed(e);
		}
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
		if (e.getSource() == mntmLoan) {
			mntmLoanActionPerformed(e);
		}
		if (e.getSource() == mntmLoanSearch) {
			mntmLoanSearchActionPerformed(e);
		}
		if (e.getSource() == mntmBankBookStatistic) {
			mntmBankBookStatisticActionPerformed(e);
		}
		if (e.getSource() == mntmBankBooTransInfo) {
			mntmBankBooTransInfoActionPerformed(e);
		}
		if (e.getSource() == mntmBankBook) {
			mntmBankBookActionPerformed(e);
		}
		if (e.getSource() == mntmCardTransInfo) {
			mntmCardTransInfoActionPerformed(e);
		}
		if (e.getSource() == mntmCardStatistic) {
			mntmCardStatisticActionPerformed(e);
		}
		if (e.getSource() == mntmCard) {
			mntmCardActionPerformed(e);
		}
		
	}
	
	protected void mntmEmpSearchActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		//센터 지우고  센터에 패널 모프시키기 
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new EmpCenterUIpanel();
	    contentPane.add(pCenter,BorderLayout.CENTER);
	    repaint();
	    revalidate();
	}
	//업무정보 조회 키 누르면 
	protected void mntmWorkInfoActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new EmpCenterUIpanel2Work();
	    contentPane.add(pCenter,BorderLayout.CENTER);
	    repaint();
	    revalidate();
		
	}
	//사원 현황 누르면  - 통계연결
	protected void mntmStatisticActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new EmpStaticPanel();
	    contentPane.add(pCenter,BorderLayout.CENTER);	
	    repaint();
        revalidate();
	}
	
	//사원권한 누르면
	protected void mnEmpAuthActionPerformed(ActionEvent e) {
		//부서라하더라도 직급이 과장, 대리, 사원이면 권한 수정할 수 없음
		if(empAuth.getEmpTitle().contentEquals("과장")||empAuth.getEmpTitle().contentEquals("대리")||empAuth.getEmpTitle().equals("사원")) {
			JOptionPane.showMessageDialog(null, "권한이 없습니다. HR부서 차장급 이상 접속가능");
			return;
		}
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new EmpCenterUIpanelAuth();
		contentPane.add(pCenter,BorderLayout.CENTER);
	    repaint();
	    revalidate();
	}
	protected void mntmCardActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new JPanel(new BorderLayout());
		pWest = new JPanel(new BorderLayout());
		pCenter = new CardCenterUIPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmCardStatisticActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.removeAll();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmCardTransInfoActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		pCenter = new JPanel(new BorderLayout());
		pWest = new JPanel(new BorderLayout());
		pWest.setBackground(new Color(255,255,255));
		transInfo_west_card = new CardTransInfoWestMenuPanel();
		JPanel[] panels = transInfo_west_card.getPanels();
		for(JPanel panel : panels) {
			panel.addMouseListener(getMouseAdapter());
		}
		pWest.add(transInfo_west_card,BorderLayout.CENTER);
		contentPane.add(pWest,BorderLayout.WEST);
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmBankBookActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new BankBookCenterUIPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmBankBooTransInfoActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new JPanel(new BorderLayout());
		pWest = new JPanel(new BorderLayout());
		pWest.setBackground(new Color(255,255,255));
		bankbook_statistic_west = new BankBookTransInfoWestMenuPanel();
		JPanel[] panels = bankbook_statistic_west.getPanels();
		for(JPanel panel : panels) {
			panel.addMouseListener(getMouseAdapter());
		}
		pWest.add(bankbook_statistic_west,BorderLayout.CENTER);
		contentPane.add(pWest,BorderLayout.WEST);
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmBankBookStatisticActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmLoanSearchActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmLoanActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		greeting = lblGreeting.getText();
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new LoanCenterUIPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	
	private synchronized MouseAdapter getMouseAdapter() {
		MouseAdapter menuAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel chkPanel = (JPanel)e.getSource();
				for(Component c : chkPanel.getParent().getComponents()) {
					JPanel panel = (JPanel)c;
					panel.setBackground(Color.white);
				}
				chkPanel.setBackground(new Color(254,208,64));
				JLabel chkLabel = (JLabel)chkPanel.getComponent(0);
				ActionListener northCustBtnListener = CustbuttonActionListener();
				ActionListener northBankBookBtnListener = BankWorkInfoButtonsActionListener(chkLabel.getText());
				switch(chkLabel.getText()) {
				//사원
				case "전체 직원 수/ 부서별 직원 비율":
					pCenter.removeAll();
					
					//센터패널
					emp_statistic_center = new EmpStatistic_CenterPanel();
					emp_statistic_center.setBackground(Color.white);
					emp_statistic_center.add(panelEmpPieChartForCountEmp);
						
					pCenter.add(emp_statistic_center,BorderLayout.CENTER);
					pCenter.repaint();
					pCenter.revalidate();
					 break;
				
				
				//고객
				case "예/적금건수(월별)":
					pCenter.removeAll();
					statistic_north_DepositSaving = new CustStatistic_NorthPanel_DepositSaving();
					cust_statistic_center = new CustStatistic_CenterPanel();
					
					
					cust_statistic_center.setBackground(Color.white);
					JButton[] buttons_DepositSaving = statistic_north_DepositSaving.getBtns();
					for(JButton btn : buttons_DepositSaving) {
						btn.addActionListener(northCustBtnListener);
					}
					pCenter.add(statistic_north_DepositSaving,BorderLayout.NORTH);
					pCenter.add(cust_statistic_center,BorderLayout.CENTER);
					pCenter.repaint();
					pCenter.revalidate();
					 break;
				case "입/출금 건수(월별)":
					pCenter.removeAll();
					statistic_north_DPWD = new CustStatistic_NorthPanel_DPWD();
					cust_statistic_center = new CustStatistic_CenterPanel();
					cust_statistic_center.setBackground(Color.white);
					JButton[] buttons_DPWD = statistic_north_DPWD.getBtns();
					for(JButton btn : buttons_DPWD) {
						btn.addActionListener(northCustBtnListener);
					}
					pCenter.add(statistic_north_DPWD,BorderLayout.NORTH);
					pCenter.add(cust_statistic_center,BorderLayout.CENTER);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				case "예금/적금/대출 총 금액":
					pCenter.removeAll();
					//statistic_north_DepositSaving = new CustStatistic_NorthPanel_DepositSaving();
					cust_statistic_center = new CustStatistic_CenterPanel();
					cust_statistic_center.setBackground(Color.white);
					//pCenter.add(statistic_north_DepositSaving,BorderLayout.NORTH);
					pCenter.add(panel_chart_DPsLoan,BorderLayout.CENTER);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				case "일반고객/VIP고객":
					pCenter.removeAll();
					statistic_north_CustNum = new CustStatistic_NorthPanel_CustNum();
					cust_statistic_center = new CustStatistic_CenterPanel();
					statistic_CustNumAll = new PanelCustNumAll();
					statistic_CustNumAll.setBackground(new Color(255,255,255));
					cust_statistic_center.setBackground(Color.white);
					JButton[] buttons_CustNum = statistic_north_CustNum.getBtns();
					for(JButton btn : buttons_CustNum) {
						btn.addActionListener(northCustBtnListener);
					}
					pCenter.add(statistic_north_CustNum,BorderLayout.NORTH);
					pCenter.add(cust_statistic_center,BorderLayout.CENTER);
					pCenter.add(statistic_CustNumAll, BorderLayout.SOUTH);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				//은행업무
				case "예금":
					pCenter.removeAll();
					transInfo_north_bankbook = new BankBookTransInfoNorthPanel();
					transInfo_north_bankbook.setBackground(new Color(255,255,255));
					JButton[] buttons_transInfo = transInfo_north_bankbook.getBtns();
					for(JButton btn : buttons_transInfo) {
						btn.addActionListener(northBankBookBtnListener);
					}
					pCenter.add(transInfo_north_bankbook,BorderLayout.NORTH);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				case "적금":
					pCenter.removeAll();
					transInfo_north_bankbook = new BankBookTransInfoNorthPanel();
					transInfo_north_bankbook.setBackground(new Color(255,255,255));
					buttons_transInfo = transInfo_north_bankbook.getBtns();
					for(JButton btn : buttons_transInfo) {
						btn.addActionListener(northBankBookBtnListener);
					}
					pCenter.add(transInfo_north_bankbook,BorderLayout.NORTH);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				case "마이너스":
					pCenter.removeAll();
					transInfo_north_bankbook = new BankBookTransInfoNorthPanel();
					transInfo_north_bankbook.setBackground(new Color(255,255,255));
					buttons_transInfo = transInfo_north_bankbook.getBtns();
					for(JButton btn : buttons_transInfo) {
						btn.addActionListener(northBankBookBtnListener);
					}
					pCenter.add(transInfo_north_bankbook,BorderLayout.NORTH);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				case "체크카드":
					pCenter.removeAll();
					transInfo_north_card = new CardTransInfoNorthPanel();
					transInfo_north_card.setBackground(new Color(255,255,255));
					buttons_transInfo = transInfo_north_card.getBtns();
					for(JButton btn : buttons_transInfo) {
						btn.addActionListener(northBankBookBtnListener);
					}
					pCenter.add(transInfo_north_card,BorderLayout.NORTH);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				case "신용카드":
					pCenter.removeAll();
					transInfo_north_card = new CardTransInfoNorthPanel();
					transInfo_north_card.setBackground(new Color(255,255,255));
					buttons_transInfo = transInfo_north_card.getBtns();
					for(JButton btn : buttons_transInfo) {
						btn.addActionListener(northBankBookBtnListener);
					}
					pCenter.add(transInfo_north_card,BorderLayout.NORTH);
					pCenter.repaint();
					pCenter.revalidate();
					break;
				}
				chartThread.interrupt();
				chartThread.run();
			}
		};
		return menuAdapter;
	}
	
	
	public void setThread() {
		chartThread = initChartThread();
		chartThread.run();
	}
	
	private Thread initChartThread() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				
				//사원
				panelEmpPieChartForCountEmp = new PanelEmpPieChartForCountEmp();
				Platform.runLater(() -> initFX((InitScene) panelEmpPieChartForCountEmp));
				
				
				panel_chart_Deposit = new PanelMonthlyDpOpenNumBarChart();
				panel_chart_Saving = new PanelMonthlySvOpenNumBarChart();
				penal_chart_DPnum = new PanelMonthlyDepositOpenNumBarChart();
				panel_chartWDnum = new PanelMonthlyWithDrawalOpenNumBarChart();
				panel_chart_DPsLoan = new PanelDPsLoanAllBarChart();
				panel_chart_custRankNum = new PanelBarChart();
				panel_chart_custVIP = new PanelPieChart();
				bankBook_barChard_Deposit = new PanelBarChartBankBookDeposit();
				
				Platform.runLater(() -> initFX((InitScene) panel_chart_Deposit));
				Platform.runLater(() -> initFX((InitScene) panel_chart_Saving));
				Platform.runLater(() -> initFX((InitScene) penal_chart_DPnum));
				Platform.runLater(() -> initFX((InitScene) panel_chartWDnum));
				Platform.runLater(() -> initFX((InitScene) panel_chart_DPsLoan));
				Platform.runLater(() -> initFX((InitScene) panel_chart_custRankNum));
				Platform.runLater(() -> initFX((InitScene) panel_chart_custVIP));
				Platform.runLater(() -> initFX((InitScene) bankBook_barChard_Deposit));
			}
		});
		return thread;
	}
	
	public void initFX(InitScene fxPanel) {
		Scene scene = fxPanel.createScene();
		JFXPanel panel = (JFXPanel) fxPanel;
		panel.setScene(scene);
	}
	public void getCenterPanel() {
		if(pWest!=null) {
			contentPane.remove(pWest);
		}
		contentPane.remove(pCenter);
		pCenter = new JPanel();
		pCenter.setBackground(Color.white);
		pcNorth = getLoginPanel();
		greeting = empAuth.getEmpName() + "님 환영합니다~";
		lblGreeting.setText(greeting);
		pcCenter = getMainLogoPanel();		
		pCenter.add(pcNorth,BorderLayout.NORTH);
		pCenter.add(pcCenter,BorderLayout.CENTER);
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	private ActionListener BankWorkInfoButtonsActionListener(String command) {
		ActionListener butonBankBookActionListner = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "일간 거래 내역":
					divBankWorkDaily(command);
					break;
				case "주간 거래 내역":
					divBankWorkWeekly(command);
					break;
				case "월간 거래 내역":
					divBankWorkMonthly(command);
					break;
				case "연간 거래 내역":
					divBankWorkYearly(command);
					break;
				}
			}

			private void divBankWorkYearly(String command) {
				switch(command) {
				case "예금":
					break;
				case "적금":
					break;
				case "마이너스":	
					break;
				case "체크카드":
					break;
				case "신용카드":
					break;
				}
			}

			private void divBankWorkMonthly(String command) {
				switch(command) {
				case "예금":
					break;
				case "적금":
					break;
				case "마이너스":
					break;
				case "체크카드":
					break;
				case "신용카드":
					break;
				}
			}

			private void divBankWorkWeekly(String command) {
				switch(command) {
				case "예금":
					break;
				case "적금":
					break;
				case "마이너스":
					break;
				case "체크카드":
					break;
				case "신용카드":
					break;
				}
			}

			private void divBankWorkDaily(String command) {
				switch(command) {
				case "예금":
					break;
				case "적금":
					break;
				case "마이너스":
					break;
				case "체크카드":
					break;
				case "신용카드":
					break;
				}
			}
		};
		return butonBankBookActionListner;
	}
	
	
	private ActionListener CustbuttonActionListener() {
		ActionListener northBtnListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("예금")) {
					cust_statistic_center.removeAll();
					cust_statistic_center.add(panel_chart_Deposit,BorderLayout.CENTER);
					cust_statistic_center.repaint();
					cust_statistic_center.revalidate();
				}
				else if(e.getActionCommand().equals("적금")) {
					cust_statistic_center.removeAll();
					cust_statistic_center.add(panel_chart_Saving,BorderLayout.CENTER);
					cust_statistic_center.repaint();
					cust_statistic_center.revalidate();
				}else if(e.getActionCommand().equals("입금")) {
					cust_statistic_center.removeAll();
					cust_statistic_center.add(penal_chart_DPnum, BorderLayout.CENTER);
					cust_statistic_center.repaint();
					cust_statistic_center.revalidate();
				}else if(e.getActionCommand().equals("출금")) {
					cust_statistic_center.removeAll();
					cust_statistic_center.add(panel_chartWDnum, BorderLayout.CENTER);
					cust_statistic_center.repaint();
					cust_statistic_center.revalidate();
				}else if(e.getActionCommand().equals("등급별 고객수")) {
					cust_statistic_center.removeAll();
					cust_statistic_center.add(panel_chart_custRankNum, BorderLayout.CENTER);
					cust_statistic_center.repaint();
					cust_statistic_center.revalidate();
				}else if(e.getActionCommand().equals("VIP 고객 비율")) {
					cust_statistic_center.removeAll();
					cust_statistic_center.add(panel_chart_custVIP, BorderLayout.CENTER);
					cust_statistic_center.repaint();
					cust_statistic_center.revalidate();
				}
			}
		};
		return northBtnListener;
	}
	
}
