package gui;

import javax.swing.JOptionPane;

/**
 * Class "Title"
 * @author jhalee
 * @author 차나몬
 */
public class Title extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	/* constructor */
	public Title() {
		initComponents();
		setTitle("===========훈민정음 게임===========");
		setLocationRelativeTo(null); /* open GUI at the center */
		setVisible(true);
		setResizable(false);
	}

	/* initialize GUI */
	private void initComponents() {
		javax.swing.JLabel background = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/title_2.png"))); // NOI18N
		background.setAutoscrolls(true);
		background.setMaximumSize(new java.awt.Dimension(1200, 720));
		background.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				backgroundMouseClicked(evt);
			}
		});
		getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
		pack();
	}

	/* if you click background, change this to Login GUI */
	private void backgroundMouseClicked(java.awt.event.MouseEvent evt) {
		JOptionPane.showMessageDialog(null, "이름과 아이피 주소를 입력하세요.");
		this.dispose();
		new Login();
	}
}
