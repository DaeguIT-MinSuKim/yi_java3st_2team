package yi_java3st_2team.ui.panel;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Notice;

import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class NoticeDetailPanel extends JPanel {
	private JPanel pCenter;
	private JPanel pNorth;
	private JTextField tfSubject;
	private JTextField tfWriter;
	private JTextArea taContent;
	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnCancel;
	private NoticeUIPanel noticePanel;
	private JButton btnReturn;
	/**
	 * Create the panel.
	 */
	public NoticeDetailPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		pNorth = new JPanel();
		pNorth.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblSubject = new JLabel("제목");
		lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		pNorth.add(lblSubject);
		
		tfSubject = new JTextField();
		pNorth.add(tfSubject);
		tfSubject.setColumns(10);
		
		JLabel lblWriter = new JLabel("작성자");
		lblWriter.setHorizontalAlignment(SwingConstants.RIGHT);
		pNorth.add(lblWriter);
		
		tfWriter = new JTextField();
		tfWriter.setColumns(10);
		pNorth.add(tfWriter);
		
		pCenter = new JPanel();
		pCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		JLabel lblContent = new JLabel("내용");
		lblContent.setHorizontalAlignment(SwingConstants.LEFT);
		pCenter.add(lblContent, BorderLayout.NORTH);
		
		taContent = new JTextArea();
		pCenter.add(taContent, BorderLayout.CENTER);
		
		pBtns = new JPanel();
		pCenter.add(pBtns, BorderLayout.SOUTH);
		
		btnAdd = new JButton("등록");
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
		
		btnReturn = new JButton("이전으로");
		pBtns.add(btnReturn);
	}
	
	public JPanel getpCenter() {
		return pCenter;
	}
	public void setpCenter(JPanel pCenter) {
		this.pCenter = pCenter;
	}
	public JPanel getpNorth() {
		return pNorth;
	}
	public void setpNorth(JPanel pNorth) {
		this.pNorth = pNorth;
	}
	public JTextField getTfSubject() {
		return tfSubject;
	}
	public void setTfSubject(JTextField tfSubject) {
		this.tfSubject = tfSubject;
	}
	public JTextField getTfWriter() {
		return tfWriter;
	}
	public void setTfWriter(JTextField tfWriter) {
		this.tfWriter = tfWriter;
	}
	public JTextArea getTaContent() {
		return taContent;
	}
	public void setTaContent(JTextArea taContent) {
		this.taContent = taContent;
	}
	public JPanel getpBtns() {
		return pBtns;
	}
	public void setpBtns(JPanel pBtns) {
		this.pBtns = pBtns;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
	public JButton getBtnReturn() {
		return btnReturn;
	}
	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}
	public NoticeUIPanel getNoticePanel() {
		return noticePanel;
	}
	public void setNoticePanel(NoticeUIPanel noticePanel) {
		this.noticePanel = noticePanel;
	}
	public Notice getItem() {
		String subject = tfSubject.getText().trim();
		String writer = tfWriter.getText().trim();
		Date writeDate = new Date();
		String content = taContent.getText();
		return new Notice(subject, writer,writeDate,content);
	}
	public void tfClear() {
		tfSubject.setText("");
		tfWriter.setText("");
		taContent.setText("");
	}
	public void setItem(Notice notice) {
		tfSubject.setText(notice.getSubject());
		tfWriter.setText(notice.getWriter());
		taContent.append(notice.getContent());
	}
	public void chkVaildate() {
		if(tfSubject.getText().length()==0 || tfWriter.getText().length()==0 || taContent.getText().length()==0) {
			JOptionPane.showMessageDialog(null, "입력란을 다 채워주세요");
			return;
		}
	}
}
