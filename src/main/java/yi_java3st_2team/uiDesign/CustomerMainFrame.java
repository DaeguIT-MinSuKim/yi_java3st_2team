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
public class CustomerMainFrame extends JFrame {

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
	private DlgCust dlgCust;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMainFrame frame = new CustomerMainFrame();
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
	public CustomerMainFrame() {
		initialize();
	}
	private void initialize() {
		dlgCust = new DlgCust();
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
		pcNorth.setBorder(new EmptyBorder(20, 20, 20, 20));
		pCenter.add(pcNorth, BorderLayout.NORTH);
		pcNorth.setLayout(new GridLayout(0, 2, 10, 10));
		
		pcCenter = new JPanel();
		pCenter.add(pcCenter, BorderLayout.CENTER);
		pcCenter.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(new ImageIcon("D:\\workspace_gradle\\yi_java3st_2team\\images\\mLogo.png").getImage().getScaledInstance(600, 300, 1)));
		pcCenter.add(lblNewLabel_3, BorderLayout.CENTER);
		
		pWest = new JPanel();
		contentPane.add(pWest, BorderLayout.WEST);
		pWest.setLayout(new GridLayout(5, 0, 0, 0));
		
		panel = new JPanel();
		pWest.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(new Color(18,66,43));
		
		lblNewLabel_1 = new JLabel("고객 개인정보");
		lblNewLabel_1.setForeground(Color.WHITE);
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
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setBackground(new Color(18, 66, 43));
		panel_4.add(label_2, BorderLayout.CENTER);
		
		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem add = new JMenuItem("추가");
		JMenuItem up = new JMenuItem("수정");
		JMenuItem del = new JMenuItem("삭제");
		ActionListener popListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("추가")) {
					dlgCust.setTitle("고객 " + e.getActionCommand());
					dlgCust.setModal(true);
					dlgCust.setVisible(true);
				}
				else if(e.getActionCommand().equals("수정")) {
					dlgCust.setTitle("고객 " + e.getActionCommand());
					dlgCust.setModal(true);
					dlgCust.setVisible(true);
				}
				else {
					int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
					switch(res) {
					case 0:
						JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "삭제가 취소되었습니다");
						break;
					}
				}
			}
		};
		add.addActionListener(popListener);
		up.addActionListener(popListener);
		del.addActionListener(popListener);
		popMenu.add(add);
		popMenu.add(up);
		popMenu.add(del);
		
	}

}
