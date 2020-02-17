package yi_java3st_2team.uiDesign;

import java.awt.BorderLayout;
import java.awt.Color;
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

@SuppressWarnings("serial")
public class CardTransHisStatisticFrame extends JFrame {

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
	private JPanel pcNorth;
	private JLabel lblCust;
	private JLabel lblAuth;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblNewLabel_2;
	private JPanel pcCenter;
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
	private JPanel panel_10;
	private JPanel panel_11;
	private JLabel label_6;
	private JLabel label_7;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel label_8;
	private JLabel label_9;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JButton btnNewButton;
	private JPanel panel_16;
	private JLabel lblNewLabel_3;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardTransHisStatisticFrame frame = new CardTransHisStatisticFrame();
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
	public CardTransHisStatisticFrame() {
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
		
		pcNorth = new JPanel();
		pcNorth.setBorder(new EmptyBorder(5, 5, 5, 5));
		pCenter.add(pcNorth, BorderLayout.NORTH);
		pcNorth.setLayout(new GridLayout(0, 2, 10, 10));
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(18, 66, 43));
		pcNorth.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		label_8 = new JLabel("카드 거래 내역 조회");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(new Color(254,208,64));
		label_8.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		panel_5.add(label_8, BorderLayout.CENTER);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(18, 66, 43));
		pcNorth.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		label_9 = new JLabel("카드 정보 조회");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		panel_6.add(label_9, BorderLayout.CENTER);
		
		pcCenter = new JPanel();
		pCenter.add(pcCenter, BorderLayout.CENTER);
		pcCenter.setLayout(new BorderLayout(0, 0));
		
		panel_12 = new JPanel();
		pcCenter.add(panel_12, BorderLayout.SOUTH);
		panel_12.setLayout(new GridLayout(0, 4, 0, 0));
		
		panel_13 = new JPanel();
		panel_12.add(panel_13);
		
		panel_14 = new JPanel();
		panel_12.add(panel_14);
		
		panel_15 = new JPanel();
		panel_12.add(panel_15);
		
		btnNewButton = new JButton("조회");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_12.add(btnNewButton);
		
		panel_16 = new JPanel();
		panel_16.setBorder(new EmptyBorder(5, 5, 5, 5));
		pcCenter.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNewLabel_3 = new JLabel("일간 내역");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_16.add(lblNewLabel_3);
		
		label_10 = new JLabel("주간 내역");
		label_10.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_16.add(label_10);
		
		label_11 = new JLabel("월간 내역");
		label_11.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_16.add(label_11);
		
		label_12 = new JLabel("년간 내역");
		label_12.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_16.add(label_12);
		
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
		label_3.setForeground(Color.white);
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setBackground(new Color(18, 66, 43));
		panel_7.add(label_3, BorderLayout.CENTER);
		
		panel_8 = new JPanel();
		panel_8.setBackground(new Color(18, 66, 43));
		pEast.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		label_4 = new JLabel("카드관리");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(new Color(254,208,64));
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
		pSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new GridLayout(0, 2, 0, 0));
		pSouth.setBackground(new Color(18, 66, 43));
		
		panel_10 = new JPanel();
		panel_10.setBackground(new Color(18, 66, 43));
		pSouth.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		label_6 = new JLabel("카드 검색");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.white);
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_6.setBackground(new Color(18, 66, 43));
		panel_10.add(label_6,BorderLayout.CENTER);
		
		panel_11 = new JPanel();
		panel_11.setBackground(new Color(18, 66, 43));
		pSouth.add(panel_11);
		
		label_7 = new JLabel("카드 조회");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(new Color(254,208,64));
		label_7.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_7.setBackground(new Color(18, 66, 43));
		panel_11.add(label_7,BorderLayout.CENTER);
	}

}
