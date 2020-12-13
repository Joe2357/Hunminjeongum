package gui;

import javax.swing.*;
import java.awt.Color;

/**
 * Class "Login"
 * @author jhalee
 * @author 차나몬
 * 
 * @param IPADDRESS[String] : server ip to connect
 * @param nickname[String] : nickname to use
 * @param check_IP[Boolean] : to check whether client entered all information
 * 
 * @param enter[JButton] : button to enter server
 * @param exit[JButton] : button to exit program
 * @param ip[JLabel] : label to show "[ IP 주소 ]"
 * @param jLabel2[JLabel] : background
 * @param jPasswordField1[JTextField] : text field to write server ip
 * @param jTextField3[JTextField] : text field to write client's name
 * @param name[JLabel] : label to show "[ 이름 ]"
 */
public class Login extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	public static String IPADDRESS = "0.0.0.0";
	public static String nickname = null;
	public static boolean check_IP = false;

	/* GUI variable */
	private javax.swing.JButton enter;
	private javax.swing.JButton exit;
	private javax.swing.JLabel ip;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jPasswordField1;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JLabel name;

	/* constructor */
	public Login() {
		initComponents();
		setTitle("===========들어가기===========");
		setLocationRelativeTo(null); /* move gui to center */
		exit.setBackground(new Color(0, 0, 0, 0)); /* to make it transparently */
		enter.setBackground(new Color(0, 0, 0, 0)); /* to make it transparently */
		setVisible(true);
		setResizable(false);
	}

	/* initialize GUI */
	private void initComponents() {
		enter = new javax.swing.JButton();
		exit = new javax.swing.JButton();
		jPasswordField1 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		name = new javax.swing.JLabel();
		ip = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		enter.setFont(new java.awt.Font("DX바른필기 M", 0, 36)); // NOI18N
		enter.setText("[입 력]");
		enter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				enterActionPerformed(evt);
			}
		});
		getContentPane().add(enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 440, 130, 60));

		exit.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		exit.setText("[그만하기]");
		exit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		exit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				exitMouseClicked(evt);
			}
		});
		getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 540, 120, 40));

		jPasswordField1.setText("localhost");
		getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 200, 40));

		jTextField3.setText("닉네임을 입력하세요.");
		getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 200, 40));

		name.setFont(new java.awt.Font("DX바른필기 M", 0, 36)); // NOI18N
		name.setText("[ 이  름 ]");
		getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 130, 40));

		ip.setFont(new java.awt.Font("DX바른필기 M", 0, 36)); // NOI18N
		ip.setText("[ IP 주소 ]");
		getContentPane().add(ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 160, 30));

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/bg_login.png"))); // NOI18N
		getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1130, 620));

		pack();
	}

	/* when client click the button to connect */
	private void enterActionPerformed(java.awt.event.ActionEvent evt) {
		nickname = jTextField3.getText();
		IPADDRESS = jPasswordField1.getText();
		setVisible(false);
		check_IP = true;
	}

	/* when client click the button to exit */
	private void exitMouseClicked(java.awt.event.MouseEvent evt) {
		JOptionPane.showMessageDialog(null, "게임을 종료합니다.");
		System.exit(0);
	}
}
