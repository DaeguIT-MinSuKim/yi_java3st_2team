package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.service.CustomerService;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JTextPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Font;
import java.awt.Color;

public class DlgCustPlan extends JDialog  {

	private final JPanel contentPanel = new JPanel();
	private String[] detailList = {"예금(AA)", "적금(AB)", "마이너스(AC)", "체크카드(BA)", "신용카드(BB)", "일반 대출(CA)", "신용대출(CB)", "카드론(CC)"};
	private String[] divList = {"V", "N"};
	private JButton okButton;	
	private JTextField tfCustPlanCode;
	private JTextField tfCustDetail;
	private JTextField tfCustPlanName;
	private JComboBox cmbPlanDetail;
	private JComboBox cmbCustPlanDiv;
	private JTextArea textAreaPlanDesc;
	private JLabel lblSelectPlease;
	private Boolean selectCheck = true; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCustPlan dialog = new DlgCustPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setTitle("고객 상품");
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCustPlan() {
		initialize();
	}
	
	

	public JButton getOkButton() {
		return okButton;
	}
	
	


	public JPanel getContentPanel() {
		return contentPanel;
	}

	public String[] getDetailList() {
		return detailList;
	}

	public String[] getDivList() {
		return divList;
	}

	public JTextField getTfCustPlanCode() {
		return tfCustPlanCode;
	}

	public JTextField getTfCustDetail() {
		return tfCustDetail;
	}

	public JTextField getTfCustPlanName() {
		return tfCustPlanName;
	}

	public JComboBox getCmbPlanDetail() {
		return cmbPlanDetail;
	}

	public JComboBox getCmbCustPlanDiv() {
		return cmbCustPlanDiv;
	}

	public JTextArea getTextAreaPlanDesc() {
		return textAreaPlanDesc;
	}
	
	
	

	public Boolean getSelectCheck() {
		return selectCheck;
	}

	public void setSelectCheck(Boolean selectCheck) {
		this.selectCheck = selectCheck;
	}

	public JLabel getLblSelectPlease() {
		return lblSelectPlease;
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel upperPanel = new JPanel();
			upperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			contentPanel.add(upperPanel, BorderLayout.NORTH);
			upperPanel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblCustPlanCode = new JLabel("상품 코드");
				lblCustPlanCode.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustPlanCode.setMaximumSize(new Dimension(61, 24));
				upperPanel.add(lblCustPlanCode);
			}
			{
				tfCustPlanCode = new JTextField();
				tfCustPlanCode.setEditable(false);
				upperPanel.add(tfCustPlanCode);
				tfCustPlanCode.setColumns(10);
			}
			{
				JLabel lblCustDetail = new JLabel("상품 세부 코드");
				lblCustDetail.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustDetail.setMaximumSize(new Dimension(61, 24));
				upperPanel.add(lblCustDetail);
			}
			{
				JPanel upperDetail = new JPanel();
				upperPanel.add(upperDetail);
				upperDetail.setLayout(new GridLayout(0, 2, 0, 0));
				{
					cmbPlanDetail = new JComboBox(detailList);
					cmbPlanDetail.setEditable(true);
					cmbPlanDetail.setSelectedIndex(-1);
					upperDetail.add(cmbPlanDetail);
				}
				{
					tfCustDetail = new JTextField();
					upperDetail.add(tfCustDetail);
					tfCustDetail.setColumns(10);
				}
			}
			{
				JPanel panel = new JPanel();
				upperPanel.add(panel);
			}
			{
				JPanel panel = new JPanel();
				upperPanel.add(panel);
				{
					
					lblSelectPlease = new JLabel("상품 세부코드를 선택하세요.");
					lblSelectPlease.setForeground(new Color(128, 0, 0));
					lblSelectPlease.setFont(new Font("맑은 고딕", Font.BOLD, 12));
					
					
					panel.add(lblSelectPlease);
				}
			}
			{
				JLabel lblCustPlanName = new JLabel("상품명");
				lblCustPlanName.setHorizontalAlignment(SwingConstants.CENTER);
				upperPanel.add(lblCustPlanName);
			}
			{
				tfCustPlanName = new JTextField();
				upperPanel.add(tfCustPlanName);
				tfCustPlanName.setColumns(10);
			}
			{
				JLabel lblCustPlanDiv = new JLabel("상품 구분");
				lblCustPlanDiv.setHorizontalAlignment(SwingConstants.CENTER);
				upperPanel.add(lblCustPlanDiv);
			}
			{
				cmbCustPlanDiv = new JComboBox(divList);
				cmbCustPlanDiv.setSelectedIndex(-1);
				upperPanel.add(cmbCustPlanDiv);
			}
		}
		{
			JPanel lowerPanel = new JPanel();
			contentPanel.add(lowerPanel, BorderLayout.CENTER);
			lowerPanel.setLayout(new BorderLayout(0, 0));
			{
				JPanel lowerNorth = new JPanel();
				lowerNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
				lowerPanel.add(lowerNorth, BorderLayout.NORTH);
				lowerNorth.setLayout(new GridLayout(0, 2, 0, 0));
				{
					JLabel lblCustPlanDesc = new JLabel("상품 세부 설명");
					lblCustPlanDesc.setHorizontalAlignment(SwingConstants.CENTER);
					lowerNorth.add(lblCustPlanDesc);
				}
			}
			{
				JPanel lowerCenter = new JPanel();
				lowerPanel.add(lowerCenter, BorderLayout.CENTER);
				{
					textAreaPlanDesc = new JTextArea();
					textAreaPlanDesc.setColumns(30);
					textAreaPlanDesc.setRows(4);
					lowerCenter.add(textAreaPlanDesc);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tfCustPlanName.setText("");
						textAreaPlanDesc.setText("");
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	
	public Plan getItem() {
		if(getTfCustPlanCode()==null ||
			getTfCustDetail()==null ||
			getTfCustPlanName()==null ||
			getCmbPlanDetail().getSelectedIndex()== -1||
			getCmbCustPlanDiv().getSelectedIndex()==-1||
			getTextAreaPlanDesc()==null) {
			
			return null;
		}else {
			String planCode = tfCustPlanCode.getText();
			
			String detail_cmb = (String) cmbPlanDetail.getSelectedItem();
			int index = detail_cmb.indexOf(")");
			String detail_cmb_sub = detail_cmb.substring((detail_cmb.indexOf("(")+1),index);
		
			String detail_tf = tfCustDetail.getText();
			
			String planDetail = detail_cmb_sub.concat(detail_tf);
			String planName = tfCustPlanName.getText();
			String planDiv = (String) cmbCustPlanDiv.getSelectedItem();
			String planDesc = textAreaPlanDesc.getText();
			
			return new Plan(planCode, planDetail, planName, planDesc, planDiv);
		}
		
		
	}
	
	public void setItem(Plan plan) {
		tfCustPlanCode.setText(plan.getPlanCode());
		String detail = plan.getPlanDetail();//AA001
		String detail_cmb = detail.substring(0,2);//AA
		String detail_tf = detail.substring(2);//001
		
		if(detail_cmb.equals("AA")) {
			cmbPlanDetail.setSelectedIndex(0);
		}else if(detail_cmb.equals("AB")) {
			cmbPlanDetail.setSelectedIndex(1);
		}else if(detail_cmb.equals("AC")) {
			cmbPlanDetail.setSelectedIndex(2);
		}else if(detail_cmb.equals("BA")) {
			cmbPlanDetail.setSelectedIndex(3);
		}else if(detail_cmb.equals("BB")) {
			cmbPlanDetail.setSelectedIndex(4);
		}else if(detail_cmb.equals("CA")) {
			cmbPlanDetail.setSelectedIndex(5);
		}else if(detail_cmb.equals("CB")) {
			cmbPlanDetail.setSelectedIndex(6);
		}else if(detail_cmb.equals("CC")) {
			cmbPlanDetail.setSelectedIndex(7);
		}else {
			cmbPlanDetail.setSelectedIndex(-1);
		}
		
		tfCustDetail.setText(detail_tf);
		tfCustPlanName.setText(plan.getPlanName());
		
		String plan_div = plan.getPlanDiv();
		if(plan_div.equals("V")) {
			cmbCustPlanDiv.setSelectedIndex(0);
		}else {
			cmbCustPlanDiv.setSelectedIndex(1);
		}
		
		
		textAreaPlanDesc.setText(plan.getPlanDesc());
		
	}
	
	
	public void tfClear() {
		
		tfCustPlanCode.setText("");
		tfCustDetail.setText("");
		tfCustPlanName.setText("");
		cmbPlanDetail.setSelectedIndex(-1);
		cmbCustPlanDiv.setSelectedIndex(-1);
		textAreaPlanDesc.setText("");
	
		
	}
	
	
	
	public void setActiontoAdd() {
		okButton.setActionCommand("추가");
	}
	
	public void setActiontoEdit() {
		okButton.setActionCommand("수정");
	}
	

	
}
