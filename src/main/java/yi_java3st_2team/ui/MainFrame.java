package yi_java3st_2team.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.panel.BankBookCenterUIPanel;
import yi_java3st_2team.ui.panel.BankBookInfoStatisticPanel;
import yi_java3st_2team.ui.panel.BankBookTransHisStatisticPanel;
import yi_java3st_2team.ui.panel.CardCenterStatisticPanel;
import yi_java3st_2team.ui.panel.CardCenterTransInfoPanel;
import yi_java3st_2team.ui.panel.CardCenterUIPanel;
import yi_java3st_2team.ui.panel.CustDWUIPanel;
import yi_java3st_2team.ui.panel.CustInfoUIPanel;
import yi_java3st_2team.ui.panel.CustPlanUIPanel;
import yi_java3st_2team.ui.panel.CustStatisticPanel;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel2Work;
import yi_java3st_2team.ui.panel.EmpCenterUIpanelAuth;
import yi_java3st_2team.ui.panel.EmpStaticPanel;
import yi_java3st_2team.ui.panel.LoanCenterUIPanel;
import yi_java3st_2team.ui.panel.LoanInfoStatisticPanel;
import yi_java3st_2team.ui.service.EmployeeService;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
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
	private String greeting;
	private JButton btnLogout;
	private static LoginFrame loginFrame;

	public MainFrame() {
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
		pImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(greeting == null) {
					greeting = lblGreeting.getText();
				}
				contentPane.remove(pCenter);
				pcNorth = getLoginPanel();
				lblGreeting.setText(greeting);
				pcCenter = getMainLogoPanel();
				pCenter = new JPanel();
				pCenter.setBackground(Color.white);
				pCenter.add(pcNorth,BorderLayout.NORTH);
				pCenter.add(pcCenter,BorderLayout.CENTER);
				contentPane.add(pCenter,BorderLayout.CENTER);
				repaint();
				revalidate();
			}
			
		});
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
				greeting = lblGreeting.getText();
				contentPane.remove(pCenter);
				pCenter = new CustStatisticPanel();
				contentPane.add(pCenter,BorderLayout.CENTER);
				revalidate();
				repaint();
				
			}
		});
		mnCust.add(mntmCustStatistic);
		
		
		mntmCustPlan = new JMenuItem("고객 상품관리");
		mntmCustPlan.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		mntmCustPlan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				greeting = lblGreeting.getText();
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
				contentPane.remove(pCenter);
				pCenter = new CustDWUIPanel();
				contentPane.add(pCenter,BorderLayout.CENTER);
				revalidate();
				repaint();
				
			}
		});
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
	private JPanel getMainLogoPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		String imgPath = System.getProperty("user.dir") + "//images//MainLogo.png";
		JLabel mainLogo = new JLabel(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(700, 250, 1)));
		panel.add(mainLogo);
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
//		btnLogout.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setClear();
//				dispose();
//			}
//		});  // 로그인프레임에서 처리하도록 함
		//로그아웃 버튼에 액션리스너 달기
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
		pCenter.setBackground(Color.white);
		pcNorth = getLoginPanel();
		pcCenter = getMainLogoPanel();
		pCenter.add(pcNorth,BorderLayout.NORTH);
		pCenter.add(pcCenter,BorderLayout.CENTER);
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
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
		//로그아웃
		if (e.getSource() == btnLogout) {
			loginFrame = loginFrame.getFrame();
			loginFrame.btnLogoutActionPerformed(e);
		}
	}
	
	protected void mntmEmpSearchActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		//센터 지우고  센터에 패널 모프시키기 
		contentPane.remove(pCenter);
		pCenter = new EmpCenterUIpanel();
	    contentPane.add(pCenter,BorderLayout.CENTER);
	    repaint();
	    revalidate();
	}
	//업무정보 조회 키 누르면 
	protected void mntmWorkInfoActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new EmpCenterUIpanel2Work();
	    contentPane.add(pCenter,BorderLayout.CENTER);
	    repaint();
	    revalidate();
		
	}
	//사원 현황 누르면  - 통계연결
	protected void mntmStatisticActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new EmpStaticPanel();
	    contentPane.add(pCenter,BorderLayout.CENTER);	
	    repaint();
        revalidate();
	}
	
	//사원권한 누르면
	protected void mnEmpAuthActionPerformed(ActionEvent e) {
		//hr부서라하더라도 직급이 과장, 대리, 사원이면 권한 수정할 수 없음
		if(empAuth.getEmpTitle().contentEquals("과장")||empAuth.getEmpTitle().contentEquals("대리")||empAuth.getEmpTitle().equals("사원")) {
			JOptionPane.showMessageDialog(null, "권한이 없습니다. HR부서 차장급 이상 접속가능");
			return;
		}
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new EmpCenterUIpanelAuth();
		contentPane.add(pCenter,BorderLayout.CENTER);
	    repaint();
	    revalidate();
	}
	protected void mntmCardActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new CardCenterUIPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmCardStatisticActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new CardCenterStatisticPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmCardTransInfoActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new CardCenterTransInfoPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmBankBookActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new BankBookCenterUIPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmBankBooTransInfoActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new BankBookTransHisStatisticPanel();
		contentPane.add(pCenter);
		repaint();
		revalidate();
	}
	protected void mntmBankBookStatisticActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new BankBookInfoStatisticPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmLoanSearchActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new LoanInfoStatisticPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
	protected void mntmLoanActionPerformed(ActionEvent e) {
		greeting = lblGreeting.getText();
		contentPane.remove(pCenter);
		pCenter = new LoanCenterUIPanel();
		contentPane.add(pCenter,BorderLayout.CENTER);
		repaint();
		revalidate();
	}
}
