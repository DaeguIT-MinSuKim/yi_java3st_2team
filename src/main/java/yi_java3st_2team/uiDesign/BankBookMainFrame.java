

package yi_java3st_2team.uiDesign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BankBookMainFrame extends JFrame {

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
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblNewLabel_2;
	private DlgBook dlgBook;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JPanel pEast;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JPanel pSouth;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankBookMainFrame frame = new BankBookMainFrame();
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
	public BankBookMainFrame() {
		initialize();
	}
	private void initialize() {
		dlgBook = new DlgBook();
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
		pCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		pCenter.setForeground(new Color(255, 255, 255));
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(new ImageIcon("D:\\workspace_gradle\\yi_java3st_2team\\images\\mLogo.png").getImage().getScaledInstance(530, 300, 1)));
		pCenter.add(lblNewLabel_3, BorderLayout.CENTER);
		
		pWest = new JPanel();
		contentPane.add(pWest, BorderLayout.WEST);
		pWest.setLayout(new GridLayout(5, 0, 0, 0));
		
		panel = new JPanel();
		pWest.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(new Color(18,66,43));
		
		lblNewLabel_1 = new JLabel("고객 개인정보");
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setBackground(new Color(18,66,43));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		panel_1 = new JPanel();
		pWest.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.setBackground(new Color(18,66,43));
		
		lblNewLabel_2 = new JLabel("고객 통계조회");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_2.setBackground(new Color(18,66,43));
		panel_1.add(lblNewLabel_2, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(18,66,43));
		pWest.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("고객 상품관리");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBackground(new Color(18, 66, 43));
		panel_2.add(label, BorderLayout.CENTER);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(18,66,43));
		pWest.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JLabel("입출금 관리");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBackground(new Color(18, 66, 43));
		panel_3.add(label_1, BorderLayout.CENTER);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(18,66,43));
		pWest.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		label_2 = new JLabel("은행 업무 관리");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(254,208,64));
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setBackground(new Color(18, 66, 43));
		panel_4.add(label_2, BorderLayout.CENTER);
		
		pEast = new JPanel();
		contentPane.add(pEast, BorderLayout.EAST);
		pEast.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(18, 66, 43));
		pEast.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		label_3 = new JLabel("통장관리");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(254,208,64));
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setBackground(new Color(18, 66, 43));
		panel_7.add(label_3, BorderLayout.CENTER);
		
		panel_8 = new JPanel();
		panel_8.setBackground(new Color(18, 66, 43));
		pEast.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		label_4 = new JLabel("카드관리");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_4.setBackground(new Color(18, 66, 43));
		panel_8.add(label_4, BorderLayout.CENTER);
		
		panel_9 = new JPanel();
		panel_9.setBackground(new Color(18, 66, 43));
		pEast.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		label_5 = new JLabel("대출관리");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_5.setBackground(new Color(18, 66, 43));
		panel_9.add(label_5, BorderLayout.CENTER);
		
		pSouth = new JPanel();
		pSouth.setBackground(new Color(18, 66, 43));
		pSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(18, 66, 43));
		pSouth.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		label_6 = new JLabel("통장 검색");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_6.setBackground(new Color(18, 66, 43));
		panel_5.add(label_6, BorderLayout.NORTH);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(18, 66, 43));
		label_7 = new JLabel("통장 조회");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_7.setBackground(new Color(18, 66, 43));
		panel_6.add(label_7);
		pSouth.add(panel_6);
	}

}
