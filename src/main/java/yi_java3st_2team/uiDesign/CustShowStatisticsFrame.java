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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class CustShowStatisticsFrame extends JFrame {

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
	private JPanel pcCenter;
	private JPanel panel_6;
	private JPanel panel_5;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JButton btnNewButton;
	private JLabel lblNewLabel_4;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustShowStatisticsFrame frame = new CustShowStatisticsFrame();
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
	public CustShowStatisticsFrame() {
		initialize();
	}
	private void initialize() {
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
		
		pcCenter = new JPanel();
		pCenter.add(pcCenter, BorderLayout.CENTER);
		pcCenter.setLayout(new BorderLayout(0, 0));
		
		panel_6 = new JPanel();
		pcCenter.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(20, 30, 20, 30));
		panel_6.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNewLabel_4 = new JLabel("예/적금 건수(월별/연도별)");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(lblNewLabel_4);
		
		label_1 = new JLabel("입/출금 건수(월별/연도별)");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(label_1);
		
		label_2 = new JLabel("예금 총 금액");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(label_2);
		
		label_3 = new JLabel("대출 총 금액");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(label_3);
		
		label_4 = new JLabel("적금 총 금액");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(label_4);
		
		label_5 = new JLabel("총 고객 숫자");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(label_5);
		
		label_7 = new JLabel("전체 고객 대비 VIP 고객 비율");
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(label_7);
		
		label_8 = new JLabel("등급별 고객 숫자");
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_5.add(label_8);
		
		panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new GridLayout(0, 4, 0, 0));
		
		panel_8 = new JPanel();
		panel_7.add(panel_8);
		
		panel_9 = new JPanel();
		panel_7.add(panel_9);
		
		panel_10 = new JPanel();
		panel_7.add(panel_10);
		
		btnNewButton = new JButton("조회");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_7.add(btnNewButton);
		
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
		
		lblNewLabel_2 = new JLabel("고객 통계 정보");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(254,208,64));
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_2.setBackground(new Color(18,66,43));
		panel_1.add(lblNewLabel_2, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(18,66,43));
		pWest.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("고객 상품관리");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBackground(new Color(18, 66, 43));
		panel_2.add(label, BorderLayout.CENTER);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(18,66,43));
		pWest.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		label_6 = new JLabel("입출금 관리");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_6.setBackground(new Color(18, 66, 43));
		panel_3.add(label_6, BorderLayout.CENTER);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(18,66,43));
		pWest.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		label_9 = new JLabel("은행 업무 관리");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_9.setBackground(new Color(18, 66, 43));
		panel_4.add(label_9, BorderLayout.CENTER);
	}

}
