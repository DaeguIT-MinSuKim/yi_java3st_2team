package yi_java3st_2team.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthMenuPanel;
import yi_java3st_2team.ui.absPanel.AbsSouthMenuPanel;
import yi_java3st_2team.ui.absPanel.AbsWestMenuPanel;
import yi_java3st_2team.ui.panel.CardCenterNorthMenuPanel;
import yi_java3st_2team.ui.panel.CardCenterStatisticPanel;
import yi_java3st_2team.ui.panel.CardEastMenuPanel;
import yi_java3st_2team.ui.panel.CardSouthMenuPanel;
import yi_java3st_2team.ui.panel.CardWestMenuPanel;
import yi_java3st_2team.ui.service.EmployeeService;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pCenter;
	private JPanel pNorth;
	private JPanel pEmployee;
	private JPanel pCust;
	private JPanel pEmpAuth;
	private JPanel pImg;
	private JLabel lblImg;
	private JLabel lblEmp;
	private JLabel lblCust;
	private JLabel lblAuth;
	private JPanel pcNorth;
	private JPanel pcCenter;
	private JLabel lblGreeting;
	private EmployeeService service;
	private Employee empAuth;
	private JPanel pSouth;
	private JPanel pWest;
	private JPanel pEast;
	private JLabel[] wMenus;
	private JLabel[] eMenus;
	private JLabel[] sMenus;
	private JLabel[] cnMenus;

	public MainFrame() {
		initialize();
	}
	private void initialize() {
		service = new EmployeeService();
		wMenus = new JLabel[5];
		eMenus = new JLabel[3];
		sMenus = new JLabel[2];
		cnMenus = new JLabel[2];
		setTitle("YN Bank 직원 프로그램");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
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
		
		lblEmp = new JLabel("사원");
		lblEmp.setForeground(Color.WHITE);
		lblEmp.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblEmp.setHorizontalAlignment(SwingConstants.CENTER);
		pEmployee.add(lblEmp, BorderLayout.SOUTH);
		
		pCust = new JPanel();
		pNorth.add(pCust);
		pCust.setLayout(new BorderLayout(0, 0));
		pCust.setBackground(new Color(18,66,43));
		
		lblCust = new JLabel("고객정보");
		lblCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblCust.setForeground(Color.WHITE);
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
		pCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		pCenter.setForeground(new Color(255, 255, 255));
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		pcNorth = getLoginPanel();
		pcCenter = getMainLogoPanel();
		pCenter.add(pcNorth,BorderLayout.NORTH);
		pCenter.add(pcCenter,BorderLayout.CENTER);
		
		initMouseListeners();
	}
	private void initMouseListeners() {
		MouseListener cnMenu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel chkLabel = (JLabel)e.getSource();
				switch(chkLabel.getText()) {
				case "카드 거래 내역 조회":
					cnMenus[0].setForeground(new Color(254,208,64));
					
					break;
				case "카드 정보 조회":
					cnMenus[1].setForeground(new Color(254,208,64));
					setCenterCenterMenu();
					break;
				}
			}
			private void setCenterCenterMenu() {
				pCenter.remove(pcCenter);
				pcCenter = new CardCenterStatisticPanel();
				pCenter.add(pcCenter,BorderLayout.CENTER);
				repaint();
				revalidate();
			}	
		};
		MouseListener southMenu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel chkLabel = (JLabel)e.getSource();
				switch(chkLabel.getText()) {
				case "카드 검색":
					break;
				case "카드 조회":
					sMenus[1].setForeground(new Color(254,208,64));
					setCenterNorthMenu();
					break;
				}
			}
			private void setCenterNorthMenu() {
				pcNorth = new CardCenterNorthMenuPanel();
				initpcNorthPanel();
				pCenter.add(pcNorth,BorderLayout.NORTH);
				repaint();
				revalidate();
			}

			private void initpcNorthPanel() {
				cnMenus[0] = ((AbsCenterNorthMenuPanel) pcNorth).getLblMenu1();
				cnMenus[1] = ((AbsCenterNorthMenuPanel) pcNorth).getLblMenu2();
				for(JLabel menu : cnMenus) {
					menu.addMouseListener(cnMenu);
				}
			}
		};
		
		MouseListener eastMenu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel chkLabel = (JLabel)e.getSource();
				switch(chkLabel.getText()) {
				case "통장관리":
					eMenus[0].setForeground(new Color(254,208,64));
					setSouthMenuPanel();
					break;
				case "카드관리":
					eMenus[1].setForeground(new Color(254,208,64));
					setSouthMenuPanel();
					break;
				case "대출관리":
					eMenus[2].setForeground(new Color(254,208,64));
					setSouthMenuPanel();
					break;
				}
			}
			private void setSouthMenuPanel() {
				pSouth = new CardSouthMenuPanel();
				initSouthPanel();
				contentPane.add(pSouth, BorderLayout.SOUTH);
				repaint();
				revalidate();
			}
			private void initSouthPanel() {
				sMenus[0] = ((AbsSouthMenuPanel) pSouth).getLblMenu1();
				sMenus[1] = ((AbsSouthMenuPanel) pSouth).getLblMenu2();
				for(JLabel menu : sMenus) {
					menu.addMouseListener(southMenu);
				}
			}
			
		};
		MouseListener westMenu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel chkLabel = (JLabel)e.getSource();
				switch(chkLabel.getText()) {
				case "고객 개인 정보":
					wMenus[0].setForeground(new Color(254,208,64));
					setEastPanel();
					break;
				case "고객 통계 조회":
					wMenus[1].setForeground(new Color(254,208,64));
					setEastPanel();
					break;
				case "고객 상품관리":
					wMenus[2].setForeground(new Color(254,208,64));
					setEastPanel();
					break;
				case "입출금 관리":
					wMenus[3].setForeground(new Color(254,208,64));
					setEastPanel();
					break;
				case "은행 업무 관리":
					wMenus[4].setForeground(new Color(254,208,64));
					setEastPanel();
					break;
				}
			}
			private void setEastPanel() {
				pEast = new CardEastMenuPanel();
				initEastPanel();
				contentPane.add(pEast, BorderLayout.EAST);
				repaint();
				revalidate();
			}
			private void initEastPanel() {
				eMenus[0] = ((CardEastMenuPanel) pEast).getLblMenu1();
				eMenus[1] = ((CardEastMenuPanel) pEast).getLblMenu2();
				eMenus[2] = ((CardEastMenuPanel) pEast).getLblMenu3();
				for(JLabel menu : eMenus) {
					menu.addMouseListener(eastMenu);
				}
			}
		};
		MouseListener northMenu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initEmpAuth();
				JLabel chkLabel = (JLabel)e.getSource();
				switch(chkLabel.getText()) {
				case "사원":
					if(empAuth.getEmpAuth().equals("CS")) {
						JOptionPane.showMessageDialog(null, "접근권한이 없습니다.");
						return;
					}
					break;
				case "고객정보":
					if(empAuth.getEmpAuth().equals("HR")) {
						JOptionPane.showMessageDialog(null, "접근권한이 없습니다.");
						return;
					}
					lblCust.setForeground(new Color(254,208,64));
					setWestPanel();
					break;
				case "사원권한":
					if(empAuth.getEmpAuth().equals("CS")) {
						JOptionPane.showMessageDialog(null, "접근권한이 없습니다.");
						return;
					}
					break;
				}
			}

			private void setWestPanel() {
				pCenter.remove(pcNorth);
				pWest = new CardWestMenuPanel();
				initWestPanel();
				contentPane.add(pWest, BorderLayout.WEST);
				repaint();
				revalidate();
			}

			private void initWestPanel() {
				wMenus[0] = ((AbsWestMenuPanel) pWest).getLblMenu1();
				wMenus[1] = ((AbsWestMenuPanel) pWest).getLblMenu2();
				wMenus[2] = ((AbsWestMenuPanel) pWest).getLblMenu3();
				wMenus[3] = ((AbsWestMenuPanel) pWest).getLblMenu4();
				wMenus[4] = ((AbsWestMenuPanel) pWest).getLblMenu5();
				for(JLabel menu : wMenus) {
					menu.addMouseListener(westMenu);
				}
			}
		};
		lblEmp.addMouseListener(northMenu);
		lblCust.addMouseListener(northMenu);
		lblAuth.addMouseListener(northMenu);
	}
	private JPanel getMainLogoPanel() {
		JPanel panel = new JPanel();
		String imgPath = System.getProperty("user.dir") + "//images//mLogo.png";
		JLabel mainLogo = new JLabel(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(800, 400, 1)));
		panel.add(mainLogo);
		return panel;
	}
	private JPanel getLoginPanel() {
		JPanel panel = new JPanel();
		lblGreeting = new JLabel();
		lblGreeting.setFont(new Font("맑은 고딕",Font.BOLD,20));
		JButton btnLogout = new JButton("로그 아웃");
		btnLogout.setFont(new Font("맑은 고딕",Font.BOLD,20));
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pEast!=null) {
					contentPane.remove(pEast);
					if(pSouth!=null) {
						contentPane.remove(pSouth);
					}
				}
				Component[] northComponents = pNorth.getComponents();
				for(Component c : northComponents) {
					if(lblEmp.getParent().hashCode()==c.hashCode()) {
						lblEmp.setForeground(Color.white);
					}
					else if(lblCust.getParent().hashCode()==c.hashCode()) {
						lblCust.setForeground(Color.white);
					}
					else {
						lblAuth.setForeground(Color.white);
					}
				}
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
				empAuth = service.GetEmpAuth(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JLabel getLblGreeting() {
		return lblGreeting;
	}
}
