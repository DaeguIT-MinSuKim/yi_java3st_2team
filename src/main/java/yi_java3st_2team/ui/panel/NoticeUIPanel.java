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
import java.awt.Color;
import javax.swing.border.EmptyBorder;

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
	private int selIdx;
	/**
	 * Create the panel.
	 */
	public NoticeUIPanel() {
		initialize();
	}
	private void initialize() {
		setBorder(new EmptyBorder(0, 0, 20, 20));
		setBackground(Color.WHITE);
		service = new NoticeService();
		dpUIPanel = this;
		setLayout(new BorderLayout(0, 0));
		
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.WHITE);
		add(pNorth, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("공지사항");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		pNorth.add(lblNewLabel);
		
		pCenter = new NoticeTblPanel();
		pCenter.getTable().setBackground(Color.WHITE);
		pCenter.setBackground(Color.WHITE);
		try {
			pCenter.loadTableData(service.showNoticeByAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(pCenter, BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel();
		pSouth.setBackground(Color.WHITE);
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
	
	public NoticeTblPanel getpCenter() {
		return pCenter;
	}
	public void setpCenter(NoticeTblPanel pCenter) {
		this.pCenter = pCenter;
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
	
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}
	public JButton getBtnDel() {
		return btnDel;
	}
	public void setBtnDel(JButton btnDel) {
		this.btnDel = btnDel;
	}
	public JButton getBtnMod() {
		return btnMod;
	}
	public void setBtnMod(JButton btnMod) {
		this.btnMod = btnMod;
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("등록")) {
			dpPanel = new NoticeDetailPanel();
			ActionListener dpAddListner = setDpListner();
			dpPanel.getBtnAdd().addActionListener(dpAddListner);
			dpPanel.getBtnCancel().addActionListener(dpAddListner);
			dpPanel.getBtnReturn().addActionListener(dpAddListner);
			main.getRight().removeAll();
			main.getRight().add(dpPanel);
			main.setResizable(false);
			main.getRight().repaint();
			main.getRight().revalidate();
		}
		else {
			try {
				Notice notice = pCenter.getSelectedItem();
				dpPanel = new NoticeDetailPanel();
				dpPanel.setItem(notice);
				ActionListener dpAddListner = setDpListner();
				dpPanel.getTfWriter().setEditable(false);
				dpPanel.getTfSubject().setEditable(false);
				dpPanel.getTaContent().setEditable(false);
				dpPanel.getBtnAdd().setVisible(false);
				dpPanel.getBtnCancel().setVisible(false);
				dpPanel.getBtnReturn().addActionListener(dpAddListner);
				main.getRight().removeAll();
				main.getRight().add(dpPanel);
				main.setResizable(false);
				main.getRight().repaint();
				main.getRight().revalidate();
			}
			catch(RuntimeException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		
	}
	public ActionListener setDpListner() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("등록")) {
					try {
						dpPanel.chkVaildate();
						Notice notice = dpPanel.getItem();
						service.addNotice(notice);
						pCenter.loadTableData(service.showNoticeByAll());
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
						dpPanel.chkVaildate();
						Notice notice = dpPanel.getItem();
						pCenter.updateRow(notice, selIdx);
						notice.setNo(selIdx+1);
						int res = service.modifyNotice(notice);
						if(res==1) {
							JOptionPane.showMessageDialog(null, "수정이 완료되었습니다");
							main.getRight().removeAll();
							main.getRight().add(dpUIPanel);
							main.getRight().repaint();
							main.getRight().revalidate();
						}
					} catch(SQLException e1) {
						e1.printStackTrace();
					} catch(RuntimeException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
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
		try {
			Notice notice = pCenter.getSelectedItem();
			int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
			if(res==0) {
				try {
					service.removeNotice(notice);
					res = service.resetAutoIncrement();
					if(res==1) {
						JOptionPane.showMessageDialog(null, "삭제되었습니다");
						pCenter.loadTableData(service.showNoticeByAll());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "삭제가 취소되었습니다");
			}
		}
		catch(RuntimeException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		
	}
	protected void btnModActionPerformed(ActionEvent e) {
		try {
			dpPanel = new NoticeDetailPanel();
			dpPanel.setItem(pCenter.getSelectedItem());
			dpPanel.getBtnCancel().setVisible(false);
			selIdx = pCenter.getSelectedRowIdx();
			ActionListener dpAddListner = setDpListner();
			dpPanel.getBtnAdd().setText("수정");
			dpPanel.getBtnAdd().addActionListener(dpAddListner);
			dpPanel.getBtnReturn().addActionListener(dpAddListner);
			dpPanel.getTfSubject().setEditable(false);
			dpPanel.getTfWriter().setEditable(false);
			main.getRight().removeAll();
			main.getRight().add(dpPanel);
			main.getRight().repaint();
			main.getRight().revalidate();
		}
		catch(RuntimeException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
}
