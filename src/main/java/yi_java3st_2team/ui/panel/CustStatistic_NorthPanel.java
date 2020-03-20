package yi_java3st_2team.ui.panel;

import java.awt.BorderLayout;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticNortPanel_test;
import javax.swing.JPanel;
import javax.swing.JButton;

public class CustStatistic_NorthPanel extends AbsCenterStatisticNortPanel_test {
	private JPanel panel;
	
	public CustStatistic_NorthPanel() {
		
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));	
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
	}
	
	@Override
	protected String[] getText() {
		String[] texts = {"예금", "적금"};
		return texts;
	}
	
	
	

	

}
