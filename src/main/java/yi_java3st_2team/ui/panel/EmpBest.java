package yi_java3st_2team.ui.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.service.EmployeeUIService;
import yi_java3st_2team.ui.service.EmployeeService;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;





public class EmpBest extends JPanel {

   	
	private Dimension picDimension=new Dimension(100, 150);
	private String picPath;
	private JLabel lblRank1Name;
	private JLabel lblRank1Title;
	private JLabel lblRank2Name;
	private JLabel lblRank2Title;
	private JLabel lblRank3Name;
	private JLabel lblRank3Title;
	
	private EmployeeUIService service;
	private JLabel lblPicEmp1;
	private JLabel lblPicEmp2;
	private JLabel lblPicEmp3;
	/**
	 * Create the panel.
	 */
	public EmpBest() {
        service = new EmployeeUIService();
		initialize();
	}
	private void initialize() {
		setBorder(new EmptyBorder(10, 20, 20, 20));
		setBackground(Color.WHITE);
		List<Employee> list = new ArrayList<>();
		list = service.showRank();
		// JOptionPane.showMessageDialog(null, list.get(0).toString());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pSubject = new JPanel();
		pSubject.setBackground(Color.WHITE);
		add(pSubject);
		pSubject.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblBestLogo = new JLabel("");
		lblBestLogo.setBorder(null);
		lblBestLogo.setBackground(Color.WHITE);
		lblBestLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestLogo.setIcon(new ImageIcon(System.getProperty("user.dir")+"/images/thebest.png"));
		pSubject.add(lblBestLogo);
		
		JLabel lblTitle = new JLabel("우 수 사 원");
		lblTitle.setFont(new Font("새굴림", Font.BOLD, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pSubject.add(lblTitle);
		
		JLabel lblNewLabel_2 = new JLabel("");
		pSubject.add(lblNewLabel_2);
		
		JPanel pEmpRank = new JPanel();
		pEmpRank.setBackground(Color.WHITE);
		add(pEmpRank);
		pEmpRank.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("No.1");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
		pEmpRank.add(lblNewLabel_1);
		
		lblPicEmp1 = new JLabel("");
		lblPicEmp1.setBackground(Color.WHITE);
		lblPicEmp1.setIcon(null);
		lblPicEmp1.setSize(new Dimension(100, 150));
		lblPicEmp1.setPreferredSize(new Dimension(100, 150));
		lblPicEmp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicEmp1.setBorder(null);
		//setPic(lblPicEmp1,getClass().getClassLoader().getResource("no-img.png").getPath());
		if(list.get(0).getPic() ==null){
			setPic(lblPicEmp1,getClass().getClassLoader().getResource("no-img.png").getPath());
		}else {
			setPic(lblPicEmp1,list.get(0).getPic());
		}
		
		pEmpRank.add(lblPicEmp1);
		
		JPanel pRank1 = new JPanel();
		pRank1.setBackground(Color.WHITE);
		pEmpRank.add(pRank1);
		pRank1.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblRank1Name = new JLabel(list.get(0).getEmpName());
		lblRank1Name.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblRank1Name.setFont(new Font("굴림", Font.BOLD, 18));
		lblRank1Name.setHorizontalAlignment(SwingConstants.CENTER);
		pRank1.add(lblRank1Name);
		
		lblRank1Title = new JLabel(list.get(0).getEmpTitle());
		lblRank1Title.setHorizontalAlignment(SwingConstants.CENTER);
		pRank1.add(lblRank1Title);
		
		JLabel lblRank1Perf = new JLabel(list.get(0).getPerf()+"건");
		lblRank1Perf.setHorizontalAlignment(SwingConstants.CENTER);
		pRank1.add(lblRank1Perf);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("No. 2");
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		pEmpRank.add(lblNewLabel_3);
		
		lblPicEmp2 = new JLabel("");
		lblPicEmp2.setBackground(Color.WHITE);
		lblPicEmp2.setIcon(null);
		lblPicEmp2.setSize(new Dimension(100, 150));
		lblPicEmp2.setPreferredSize(new Dimension(100, 150));
		lblPicEmp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicEmp2.setBorder(null);
		if(list.get(1).getPic() ==null){
			setPic(lblPicEmp2,getClass().getClassLoader().getResource("no-img.png").getPath());
		}else {
			setPic(lblPicEmp2,list.get(1).getPic());
		}
		
		pEmpRank.add(lblPicEmp2);
		
		JPanel pRank2 = new JPanel();
		pRank2.setBackground(Color.WHITE);
		pEmpRank.add(pRank2);
		pRank2.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblRank2Name = new JLabel(list.get(1).getEmpName());
		lblRank2Name.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblRank2Name.setFont(new Font("굴림", Font.BOLD, 13));
		lblRank2Name.setHorizontalAlignment(SwingConstants.CENTER);
		pRank2.add(lblRank2Name);
		
		lblRank2Title = new JLabel(list.get(1).getEmpTitle());
		lblRank2Title.setHorizontalAlignment(SwingConstants.CENTER);
		pRank2.add(lblRank2Title);
		
		JLabel lblRank2Perf = new JLabel(list.get(0).getPerf()+"건");
		lblRank2Perf.setHorizontalAlignment(SwingConstants.CENTER);
		pRank2.add(lblRank2Perf);
		
		JLabel lblNewLabel_4 = new JLabel("No. 3");
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		pEmpRank.add(lblNewLabel_4);
		
		lblPicEmp3 = new JLabel("");
		lblPicEmp3.setBackground(Color.WHITE);
		lblPicEmp3.setIcon(null);
		lblPicEmp3.setSize(new Dimension(100, 150));
		lblPicEmp3.setPreferredSize(new Dimension(100, 150));
		lblPicEmp3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicEmp3.setBorder(null);
		if(list.get(2).getPic() ==null){
			setPic(lblPicEmp3,getClass().getClassLoader().getResource("no-img.png").getPath());
		}else {
			setPic(lblPicEmp3,list.get(2).getPic());
		}
		pEmpRank.add(lblPicEmp3);
		
		JPanel pRank3 = new JPanel();
		pRank3.setBackground(Color.WHITE);
		pEmpRank.add(pRank3);
		pRank3.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblRank3Name = new JLabel(list.get(2).getEmpName());
		lblRank3Name.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblRank3Name.setFont(new Font("굴림", Font.BOLD, 13));
		lblRank3Name.setHorizontalAlignment(SwingConstants.CENTER);
		pRank3.add(lblRank3Name);
		
		lblRank3Title = new JLabel(list.get(2).getEmpTitle());
		lblRank3Title.setHorizontalAlignment(SwingConstants.CENTER);
		pRank3.add(lblRank3Title);
		
		JLabel lblRank3Perf = new JLabel(list.get(2).getPerf()+"건");
		lblRank3Perf.setHorizontalAlignment(SwingConstants.CENTER);
		pRank3.add(lblRank3Perf);
	}

	
	private void setPic(JLabel lblPic, byte[] byteImg) {
		new ImageIcon(byteImg);
		lblPic.setIcon(new ImageIcon(new ImageIcon(byteImg).getImage().getScaledInstance((int)picDimension.getWidth(),
				(int)picDimension.getHeight(), Image.SCALE_DEFAULT)));
	  	 
		}
	
	private void setPic(JLabel lblPic, String imgPath) {
		picPath = imgPath; //사진이 없다면 클릭하면 경로가 바뀔것
		lblPic.setIcon(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance((int)picDimension.getWidth(),
				(int)picDimension.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 600);;
		EmpBest empBest = new EmpBest();
		frame.getContentPane().add(empBest);
		frame.setVisible(true);
	}
}
