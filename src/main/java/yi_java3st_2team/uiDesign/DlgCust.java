package yi_java3st_2team.uiDesign;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DlgCust extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCust dialog = new DlgCust();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCust() {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
		{
			JLabel lblNewLabel = new JLabel("고객코드");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("고객이름");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		{
			JLabel label = new JLabel("고객등급");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		{
			JLabel label = new JLabel("고객신용등급");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			contentPanel.add(textField_3);
		}
		{
			JLabel label = new JLabel("고객주소");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			contentPanel.add(textField_4);
		}
		{
			JLabel label = new JLabel("고객연락처");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label);
		}
		{
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			contentPanel.add(textField_5);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
			{
				JButton btnOk = new JButton("확인");
				btnOk.setActionCommand("확인");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancel = new JButton("취소");
				btnCancel.setActionCommand("취소");
				buttonPane.add(btnCancel);
			}
		}
	}

}
