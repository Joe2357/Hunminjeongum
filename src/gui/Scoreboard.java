package gui;

import java.awt.Color;
import javax.swing.*;

/**
 * Class "Scoreboard"
 * @author jhalee
 * @author 차나몬
 *
 * @param isSubmitted[Boolean] : client select to rejoin or exit
 * 
 * @param board[JPanel] : scoreboard GUI
 * @param exit[JButton] : button to exit
 * @param replay[JButton] : button to rejoin
 * 
 * @param jL_1~jL5[JLabel] : "1위" ~ "5위"
 * @param jLabel1,2[JLabel] : GUI background
 * @param show[JPanel] : gif of King Sejong
 * @param jLabel4[JLabel] : gif of King Sejong
 * 
 * 
 * @param jTextField6~10[JTextField] : nickname of clients to show rank
 */
public class Scoreboard extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	public boolean isSubmitted = false;

	private javax.swing.JPanel board;
	private javax.swing.JButton exit;
	private javax.swing.JButton replay;

	private javax.swing.JLabel jL_1;
	private javax.swing.JLabel jL_2;
	private javax.swing.JLabel jL_3;
	private javax.swing.JLabel jL_4;
	private javax.swing.JLabel jL_5;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel show;
	private javax.swing.JLabel jLabel4;

	public javax.swing.JTextField jTextField10;
	public javax.swing.JTextField jTextField6;
	public javax.swing.JTextField jTextField7;
	public javax.swing.JTextField jTextField8;
	public javax.swing.JTextField jTextField9;

	/* constructor */
	public Scoreboard() {
		initComponents();
		this.setLocationRelativeTo(null);
		exit.setBackground(new Color(0, 0, 0, 0));
		replay.setBackground(new Color(0, 0, 0, 0));
		this.setResizable(false);
	}

	/* initialize GUI */
	private void initComponents() {
		exit = new javax.swing.JButton();
		replay = new javax.swing.JButton();
		board = new javax.swing.JPanel();
		show = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jL_5 = new javax.swing.JLabel();
		jL_4 = new javax.swing.JLabel();
		jL_3 = new javax.swing.JLabel();
		jL_2 = new javax.swing.JLabel();
		jL_1 = new javax.swing.JLabel();
		jTextField10 = new javax.swing.JTextField();
		jTextField9 = new javax.swing.JTextField();
		jTextField8 = new javax.swing.JTextField();
		jTextField7 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		exit.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		exit.setText("[그만하기]");
		exit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		exit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				exitMouseClicked(evt);
			}
		});
		getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 550, 120, 40));

		replay.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		replay.setText("[다시하기]");
		replay.setActionCommand("");
		replay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		replay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		replay.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				replayMouseClicked(evt);
			}
		});
		getContentPane().add(replay, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, 120, 40));

		board.setBackground(new java.awt.Color(247, 233, 209, 100));
		board.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		show.setBackground(new java.awt.Color(225, 209, 173));
		show.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/sejong.gif"))); // NOI18N
		show.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 250, 260));

		board.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

		jL_5.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		jL_5.setText("5위");
		board.add(jL_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

		jL_4.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		jL_4.setText("4위");
		board.add(jL_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, -1, -1));

		jL_3.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		jL_3.setText("3위");
		board.add(jL_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

		jL_2.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		jL_2.setText("2위");
		board.add(jL_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

		jL_1.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		jL_1.setText("1위");
		board.add(jL_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

		jTextField10.setFont(new java.awt.Font("DX바른필기 M", 0, 20)); // NOI18N
		jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jTextField10.setEditable(false);
		board.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 100, -1));

		jTextField9.setFont(new java.awt.Font("DX바른필기 M", 0, 20)); // NOI18N
		jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jTextField9.setEditable(false);
		board.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 100, -1));

		jTextField8.setFont(new java.awt.Font("DX바른필기 M", 0, 20)); // NOI18N
		jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jTextField8.setEditable(false);
		board.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 100, -1));

		jTextField7.setFont(new java.awt.Font("DX바른필기 M", 0, 20)); // NOI18N
		jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jTextField7.setEditable(false);

		board.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 100, -1));

		jTextField6.setFont(new java.awt.Font("DX바른필기 M", 0, 20)); // NOI18N
		jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jTextField6.setEditable(false);
		board.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 100, -1));

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/scoreboard.PNG"))); // NOI18N
		board.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

		getContentPane().add(board, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 590, 390));

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/bg_scoreboard.png"))); // NOI18N
		getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 630));

		pack();
	}

	/* client select to exit */
	private void exitMouseClicked(java.awt.event.MouseEvent evt) {
		JOptionPane.showMessageDialog(null, "게임을 종료합니다.");
		System.exit(0);
	}

	/* client select to rejoin */
	private void replayMouseClicked(java.awt.event.MouseEvent evt) {
		this.setVisible(false);
		Client.selectRejoin = true;
	}
}
