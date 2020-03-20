package yi_java3st_2team.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel_test;
import yi_java3st_2team.ui.chart.PanelBarChart;
import yi_java3st_2team.ui.chart.PanelDPsLoanAllBarChart;
import yi_java3st_2team.ui.chart.PanelMonthlyDepositOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelMonthlyDpOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelMonthlySvOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelMonthlyWithDrawalOpenNumBarChart;
import yi_java3st_2team.ui.chart.PanelPieChart;

public class CustStatistic_panels extends AbsCenterStatisticPanel_test implements ActionListener{
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private PanelMonthlyDpOpenNumBarChart panel_chart_Deposit;//예금
	private PanelMonthlySvOpenNumBarChart panel_chart_Saving;//적금
	private PanelMonthlyDepositOpenNumBarChart penal_chart_DPnum;//입금
	private PanelMonthlyWithDrawalOpenNumBarChart panel_chartWDnum;//출금
	private PanelDPsLoanAllBarChart panel_chart_DPsLoan; //예금 총금액
	private PanelBarChart panel_chart_custRankNum;//등급별 고객 숫자
	private PanelPieChart panel_chart_custVIP;//vip 고객 비율
	private Thread initPanelThread;
	/**
	 * Create the panel.
	 */
	public CustStatistic_panels() {
		
	}
	
	
	
	public CustStatistic_panels(Thread initPanelThread) {
		this.initPanelThread = initPanelThread;
		initPanelThread.start();
		initialize();
	}



	public void initChartDPSV() {
		panel_chart_Deposit = new PanelMonthlyDpOpenNumBarChart();
		panel_chart_Saving = new PanelMonthlySvOpenNumBarChart();
		Platform.runLater(() -> initFX(panel_chart_Deposit));
		Platform.runLater(() -> initFX(panel_chart_Saving));
	}
	
	public void initChartDPWD() {
		penal_chart_DPnum = new PanelMonthlyDepositOpenNumBarChart();
		panel_chartWDnum = new PanelMonthlyWithDrawalOpenNumBarChart();
		Platform.runLater(() -> initFX(penal_chart_DPnum));
		Platform.runLater(() -> initFX(panel_chartWDnum));
	}

	public void initChartDPsLoan() {
		panel_chart_DPsLoan = new PanelDPsLoanAllBarChart();
		Platform.runLater(() -> initFX(panel_chart_DPsLoan));
	}
	
	
	
	public void initChartCustNums() {
		panel_chart_custRankNum = new PanelBarChart();
		panel_chart_custVIP = new PanelPieChart();
		Platform.runLater(() -> initFX(panel_chart_custRankNum));
		Platform.runLater(() -> initFX(panel_chart_custVIP));
	}
	
	public void initChart() {
		panel_chart_Deposit = new PanelMonthlyDpOpenNumBarChart();
		panel_chart_Saving = new PanelMonthlySvOpenNumBarChart();
		Platform.runLater(() -> initFX(panel_chart_Deposit));
		Platform.runLater(() -> initFX(panel_chart_Saving));
		
		penal_chart_DPnum = new PanelMonthlyDepositOpenNumBarChart();
		panel_chartWDnum = new PanelMonthlyWithDrawalOpenNumBarChart();
		Platform.runLater(() -> initFX(penal_chart_DPnum));
		Platform.runLater(() -> initFX(panel_chartWDnum));
		
		panel_chart_DPsLoan = new PanelDPsLoanAllBarChart();
		Platform.runLater(() -> initFX(panel_chart_DPsLoan));
		
		panel_chart_custRankNum = new PanelBarChart();
		panel_chart_custVIP = new PanelPieChart();
		Platform.runLater(() -> initFX(panel_chart_custRankNum));
		Platform.runLater(() -> initFX(panel_chart_custVIP));
	}

	
	
	private void initialize() {
		this.setPanelInit(this.panel, this.panel_1, this.panel_2, this.panel_3, this.panel_7, this.panel_8, this.panel_9);
		this.setLblMouseListener(this.panel, this.panel_1, this.panel_2, this.panel_3, this.panel_7, this.panel_8, this.panel_9);
		this.setLabelInit(this.menu1, this.menu2, this.menu3, this.menu4,this.menu5,this.menu6, this.menu7);
		makingBtnsDPSV(this.btn_panel);
		//initChartDPSV();
	}
	private void makingBtnsDPSV(JPanel btn_panel) {
		btnNewButton = new JButton("예금");
		btnNewButton.addActionListener(this);
		btn_panel.add(btnNewButton);
		btnNewButton_1 = new JButton("적금");
		btnNewButton_1.addActionListener(this);
		btn_panel.add(btnNewButton_1);
		
		this.setBtnInit(btnNewButton,btnNewButton_1);
	}
	
	private void makingBtnsDPWD(JPanel btn_panel) {
		btnNewButton = new JButton("입금");
		btnNewButton.addActionListener(this);
		btn_panel.add(btnNewButton);
		btnNewButton_1 = new JButton("출금");
		btnNewButton_1.addActionListener(this);
		btn_panel.add(btnNewButton_1);
		
		this.setBtnInit(btnNewButton,btnNewButton_1);
	}
	
	private void makingBtnsCustNums(JPanel btn_panel) {
		btnNewButton = new JButton("등급별 고객수");
		btnNewButton.addActionListener(this);
		btn_panel.add(btnNewButton);
		btnNewButton_1 = new JButton("VIP고객 비율");
		btnNewButton_1.addActionListener(this);
		btn_panel.add(btnNewButton_1);
		
		this.setBtnInit(btnNewButton,btnNewButton_1);
	}



	@Override
	protected String[] getTexts() {
		return new String[] {
				"예/적금건수(월별)",
				"입/출금 건수(월별)",
				"예금/적금/대출 총 금액",
				"일반고객/VIP고객",
				"",
				"",
				""
				//총 고객 숫자/전체 고객 대비 VIP 고객 비율/등급별 고객 숫자
			};
	}
	
	protected void setLblMouseListener(JPanel... menus) {
		MouseListener myLblListener = new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
				for(int i=0; i<menus.length; i++) {
					menus[i].setBackground(new Color(255,255,255));
				}
				JPanel selMenu = (JPanel)e.getSource();
				JLabel label = (JLabel) selMenu.getComponent(0);
				String title = label.getText();
				if(title.equals("예/적금건수(월별)")) {
					panel_5.removeAll();
					panel_4.removeAll();
					//System.out.println(title);
					panel_4 = new JPanel();
					center_panel.add(panel_4, BorderLayout.NORTH);
					panel_4.setBorder(new EmptyBorder(10,0,10,0));
					panel_4.setLayout(new GridLayout(0, 2, 0, 0));
					
					panel_6 = new JPanel();
					panel_4.add(panel_6);

					btn_panel = new JPanel();
					makingBtnsDPSV(btn_panel);
					panel_4.add(btn_panel);
					panel_4.revalidate();
					panel_4.repaint();
					panel_5.revalidate();
					panel_5.repaint();
				}else if(title.equals("입/출금 건수(월별)")) {
					panel_5.removeAll();
					panel_4.removeAll();
					panel_4 = new JPanel();
					center_panel.add(panel_4, BorderLayout.NORTH);
					panel_4.setBorder(new EmptyBorder(10,0,10,0));
					panel_4.setLayout(new GridLayout(0, 2, 0, 0));
					
					panel_6 = new JPanel();
					panel_4.add(panel_6);

					btn_panel = new JPanel();
					makingBtnsDPWD(btn_panel);
					panel_4.add(btn_panel);
					panel_4.revalidate();
					panel_4.repaint();
					panel_5.revalidate();
					panel_5.repaint();
				}else if(title.equals("예금/적금/대출 총 금액")) {
					panel_5.removeAll();
					panel_4.removeAll();
					panel_4 = new JPanel();
					center_panel.add(panel_4, BorderLayout.NORTH);
					panel_4.setBorder(new EmptyBorder(10,0,10,0));
					panel_4.setLayout(new GridLayout(0, 2, 0, 0));
					
					panel_6 = new JPanel();
					panel_4.add(panel_6);

					btn_panel = new JPanel();
					panel_4.add(btn_panel);
					panel_4.revalidate();
					panel_4.repaint();
					panel_5.revalidate();
					panel_5.repaint();
					
					//initChartDPsLoan();
					JPanel panel_5 = new JPanel();
					center_panel.add(panel_5, BorderLayout.CENTER);
					panel_5.add(panel_chart_DPsLoan);
					panel_5.revalidate();
					panel_5.repaint();
					
				}else if(title.equals("일반고객/VIP고객")) {
					panel_5.removeAll();
					panel_4.removeAll();
					panel_4 = new JPanel();
					center_panel.add(panel_4, BorderLayout.NORTH);
					panel_4.setBorder(new EmptyBorder(10,0,10,0));
					panel_4.setLayout(new GridLayout(0, 2, 0, 0));
					
					panel_6 = new JPanel();
					panel_4.add(panel_6);
					
					btn_panel = new JPanel();
					makingBtnsCustNums(btn_panel);
					panel_4.add(btn_panel);
					panel_4.revalidate();
					panel_4.repaint();
					panel_5.revalidate();
					panel_5.repaint();
				}
				selMenu.setBackground(new Color(254,208,64));
				
			}
		};
		for(int i=0;i<menus.length;i++) {
			menus[i].addMouseListener(myLblListener);
		}
	}


	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton_1) {
			btnNewButton_1ActionPerformed(e);
		}
		if (e.getSource() == btnNewButton) {
			btnNewButtonActionPerformed(e);
		}
	}
	protected void btnNewButtonActionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("예금")) {
			//initChartDPSV();
			JPanel panel_5 = new JPanel();
			center_panel.add(panel_5, BorderLayout.CENTER);
			panel_5.add(panel_chart_Deposit);
			panel_5.revalidate();
			panel_5.repaint();
		}else if(e.getActionCommand().equals("입금")) {
			//initChartDPWD();
			JPanel panel_5 = new JPanel();
			center_panel.add(panel_5, BorderLayout.CENTER);
			panel_5.add(penal_chart_DPnum);
			panel_5.revalidate();
			panel_5.repaint();
		}else if(e.getActionCommand().equals("등급별 고객수")) {
			//initChartCustNums();
			JPanel panel_5 = new JPanel();
			center_panel.add(panel_5, BorderLayout.CENTER);
			panel_5.add(panel_chart_custRankNum);
			panel_5.revalidate();
			panel_5.repaint();
		}
	}
	protected void btnNewButton_1ActionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("적금")) {
		//	initChartDPSV();
			JPanel panel_5 = new JPanel();
			center_panel.add(panel_5, BorderLayout.CENTER);
			panel_5.add(panel_chart_Saving);
			center_panel.revalidate();
			center_panel.repaint();
		}else if(e.getActionCommand().equals("출금")) {
			//initChartDPWD();
			JPanel panel_5 = new JPanel();
			center_panel.add(panel_5, BorderLayout.CENTER);
			panel_5.add(panel_chartWDnum);
			center_panel.revalidate();
			center_panel.repaint();
		}else if(e.getActionCommand().equals("VIP고객 비율")) {
			//initChartCustNums();
			JPanel panel_5 = new JPanel();
			center_panel.add(panel_5, BorderLayout.CENTER);
			panel_5.add(panel_chart_custVIP);
			panel_5.revalidate();
			panel_5.repaint();
		}
		
	}
}
