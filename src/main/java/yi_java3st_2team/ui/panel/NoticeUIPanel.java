package yi_java3st_2team.ui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import yi_java3st_2team.dto.Notice;
import yi_java3st_2team.ui.MainFrame;
import yi_java3st_2team.ui.service.NoticeService;
import yi_java3st_2team.ui.table.NoticeTblPanel;

@SuppressWarnings("serial")
public class NoticeUIPanel extends JPanel implements ActionListener {
	private MainFrame main;
	private NoticeTblPanel pCenter;
	private NoticeService service;
	private JButton btnAdd;
	private JButton btnDel;
	private JButton btnMod;
	private NoticeDetailPanel dpPanel;
	private NoticeUIPanel dpUIPanel;
	/**
	 * Create the panel.
	 */
	public NoticeUIPanel() {
		initialize();
	}
	private void initialize() {
		service = new NoticeService();
		dpUIPanel = this;
		setLayout(new BorderLayout(0, 0));
		
		JPanel pNorth = new JPanel();
		add(pNorth, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("공지사항");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		pNorth.add(lblNewLabel);
		
		pCenter = new NoticeTblPanel();
		try {
			pCenter.loadTableData(service.showNoticeByAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(pCenter, BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSouth.getLayout();
		flowLayout.setHgap(20);
		add(pSouth, BorderLayout.SOUTH);
		
		btnAdd = new JButton("등록");
		btnAdd.addActionListener(this);
		btnAdd.setFont(new Font("굴림", Font.PLAIN, 16));
		pSouth.add(btnAdd);
		
		btnMod = new JButton("수정");
		btnMod.addActionListener(this);
		btnMod.setFont(new Font("굴림", Font.PLAIN, 16));
		pSouth.add(btnMod);
		
		btnDel = new JButton("삭제");
		btnDel.addActionListener(this);
		btnDel.setFont(new Font("굴림", Font.PLAIN, 16));
		pSouth.add(btnDel);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMod) {
			btnModActionPerformed(e);
		}
		if (e.getSource() == btnDel) {
			btnDelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			btnAddActionPerformed(e);
		}
	}
	
	public NoticeUIPanel getDpUIPanel() {
		return dpUIPanel;
	}
	public void setDpUIPanel(NoticeUIPanel dpUIPanel) {
		this.dpUIPanel = dpUIPanel;
	}
	public MainFrame getMain() {
		return main;
	}
	public void setMain(MainFrame main) {
		this.main = main;
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		dpPanel = new NoticeDetailPanel();
		ActionListener dpAddListner = setDpListner();
		dpPanel.getBtnAdd().addActionListener(dpAddListner);
		dpPanel.getBtnCancel().addActionListener(dpAddListner);
		dpPanel.getBtnReturn().addActionListener(dpAddListner);
		main.getRight().removeAll();
		main.getRight().add(dpPanel);
		main.getRight().repaint();
		main.getRight().revalidate();
	}
	public ActionListener setDpListner() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("등록")) {
					try {
						Notice notice = dpPanel.getItem();
						pCenter.loadTableData(service.showNoticeByAll());
						service.addNotice(notice);
						JOptionPane.showMessageDialog(null, "등록이 완료되었습니다");
						main.getRight().removeAll();
						main.getRight().add(dpUIPanel);
						main.getRight().repaint();
						main.getRight().revalidate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(e.getActionCommand().contentEquals("수정")) {
					try {	
						Notice notice = dpPanel.getItem();
						pCenter.loadTableData(service.showNoticeByAll());
						service.modifyNotice(notice);
						JOptionPane.showMessageDialog(null, "수정이 완료되었습니다");
						main.getRight().removeAll();
						main.getRight().add(dpUIPanel);
						main.getRight().repaint();
						main.getRight().revalidate();
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getActionCommand().equals("취소")) {
					dpPanel.tfClear();
					JOptionPane.showMessageDialog(null, "취소되었습니다");
				}
				else {
					main.getRight().removeAll();
					main.getRight().add(dpUIPanel);
					main.getRight().repaint();
					main.getRight().revalidate();
				}
			}
		};
	}	
	protected void btnDelActionPerformed(ActionEvent e) {
		int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
		if(res==0) {
			try {
				Notice notice = pCenter.getSelectedItem();
				service.removeNotice(notice);
				JOptionPane.showMessageDialog(null, "삭제되었습니다");
				pCenter.loadTableData(service.showNoticeByAll());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "삭제가 취소되었습니다");
		}
		
	}
	protected void btnModActionPerformed(ActionEvent e) {
		dpPanel = new NoticeDetailPanel();
		dpPanel.setItem(pCenter.getSelectedItem());
		ActionListener dpAddListner = setDpListner();
		dpPanel.getBtnAdd().setText("수정");
		dpPanel.getBtnAdd().addActionListener(dpAddListner);
		dpPanel.getBtnCancel().addActionListener(dpAddListner);
		dpPanel.getBtnReturn().addActionListener(dpAddListner);
		dpPanel.getTfSubject().setEditable(false);
		dpPanel.getTfWriter().setEditable(false);
		main.getRight().removeAll();
		main.getRight().add(dpPanel);
		main.getRight().repaint();
		main.getRight().revalidate();
	}
}
