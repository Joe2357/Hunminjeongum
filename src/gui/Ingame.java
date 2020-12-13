package gui;

import java.awt.Font;
import java.io.PrintWriter;
import javax.swing.JTextField;

/**
 * Class "Ingame"
 * @author jhalee
 * @author 차나몬
 *  
 * @param out[PrintWriter] : client's printwriter
 * @param isSubmitted[Boolean] : client was submitted a word
 *  
 * @param agree[JButton] : button to agree in voting system
 * @param deny[JButton] : button to deny in voting system
 * 
 * @param c1[JPanel] ~ c5[JPanel] : space to place client information
 * @param chat[JTextField] : space for entering sentences to send in chats
 * @param chatboard[JPanel] : space to place chat information
 * @param chatscreen[JScrollPane] : space to display system and user's sentence
 * @param chatsend[JButton] : button to send message
 * @param client1[JLabel]~client5[JLabel] : image to decorate user information
 * @param jLabel_name1[JLabel]~jLabel_name5[JLabel] : image to decorate user information
 * @param info[JLabel] : label to show "제시된 초성"
 * @param jLabel1[JLabel] : label to show "투표하기"
 * @param jTextArea2[JTextArea] : space to enter sentence to chat
 * @param jTextPane1[JTextField]~jTextPane4[JTextField], jTextPane6[JTextField] : the purpose of showing each player the word entered.
 * @param kb_enter[JButton] : send typed words
 * @param keyboard[JTextField] : enter words to send to the server by the player
 * @param manager[JLabel] : sign of the manager of a room
 * @param name1[JLabel] ~ name5[JLabel] : show plyaer's name
 * @param start[JButton] : button to forward message to server to start game
 * @param user[JPanel] : panel that binds word entry elements.
 * @param word[JTextField] : label to show random initial to players
 * @param word_c1[JScrollPane] ~ word_c5[JScrollPane] : panel to hold jTextPane
 */
public class Ingame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	public boolean isSubmitted = false;

	private javax.swing.JButton agree;
	private javax.swing.JButton deny;

	public javax.swing.JPanel c1;
	public javax.swing.JPanel c2;
	public javax.swing.JPanel c3;
	public javax.swing.JPanel c4;
	public javax.swing.JPanel c5;

	private javax.swing.JLabel background;
	private javax.swing.JTextField chat;
	private javax.swing.JPanel chatboard;
	private javax.swing.JScrollPane chatscreen;
	private javax.swing.JButton chatsend;
	public javax.swing.JLabel client1;
	public javax.swing.JLabel client2;
	public javax.swing.JLabel client3;
	public javax.swing.JLabel client4;
	public javax.swing.JLabel client5;
	private javax.swing.JLabel info;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel_name1;
	private javax.swing.JLabel jLabel_name2;
	private javax.swing.JLabel jLabel_name3;
	private javax.swing.JLabel jLabel_name4;
	private javax.swing.JLabel jLabel_name5;
	public javax.swing.JTextArea jTextArea2;
	public javax.swing.JTextField jTextPane1;
	public javax.swing.JTextField jTextPane2;
	public javax.swing.JTextField jTextPane3;
	public javax.swing.JTextField jTextPane4;
	public javax.swing.JTextField jTextPane6;
	private javax.swing.JButton kb_enter;
	private javax.swing.JTextField keyboard;
	private javax.swing.JLabel manager;
	public javax.swing.JLabel name1;
	public javax.swing.JLabel name2;
	public javax.swing.JLabel name3;
	public javax.swing.JLabel name4;
	public javax.swing.JLabel name5;
	private javax.swing.JButton start;
	private javax.swing.JPanel user;
	private javax.swing.JPanel voting;
	public javax.swing.JTextField word;
	public javax.swing.JScrollPane word_c1;
	public javax.swing.JScrollPane word_c2;
	public javax.swing.JScrollPane word_c3;
	public javax.swing.JScrollPane word_c4;
	public javax.swing.JScrollPane word_c5;

	/* constructor */
	public Ingame() {
		initComponents();
		setTitle("===========훈민정음 게임===========");
		this.setLocationRelativeTo(null); // center form in the screen
		this.setResizable(false);
	}

	public Ingame(PrintWriter o) {
		initComponents();
		setTitle("===========훈민정음 게임===========");
		this.setLocationRelativeTo(null); // center form in the screen
		this.setResizable(false);
		this.out = o;
	}

	/* initialize GUI */
	private void initComponents() {
		info = new javax.swing.JLabel();
		word = new javax.swing.JTextField();
		start = new javax.swing.JButton();
		c1 = new javax.swing.JPanel();
		name1 = new javax.swing.JLabel();
		word_c1 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextField();
		jLabel_name1 = new javax.swing.JLabel();
		manager = new javax.swing.JLabel();
		client1 = new javax.swing.JLabel();
		c2 = new javax.swing.JPanel();
		name2 = new javax.swing.JLabel();
		word_c2 = new javax.swing.JScrollPane();
		jTextPane2 = new javax.swing.JTextField();
		jLabel_name2 = new javax.swing.JLabel();
		client2 = new javax.swing.JLabel();
		c3 = new javax.swing.JPanel();
		name3 = new javax.swing.JLabel();
		word_c3 = new javax.swing.JScrollPane();
		jTextPane6 = new javax.swing.JTextField();
		jLabel_name3 = new javax.swing.JLabel();
		client3 = new javax.swing.JLabel();
		c4 = new javax.swing.JPanel();
		name4 = new javax.swing.JLabel();
		word_c4 = new javax.swing.JScrollPane();
		jTextPane3 = new javax.swing.JTextField();
		jLabel_name4 = new javax.swing.JLabel();
		client4 = new javax.swing.JLabel();
		c5 = new javax.swing.JPanel();
		name5 = new javax.swing.JLabel();
		word_c5 = new javax.swing.JScrollPane();
		jTextPane4 = new javax.swing.JTextField();
		jLabel_name5 = new javax.swing.JLabel();
		client5 = new javax.swing.JLabel();
		voting = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		agree = new javax.swing.JButton();
		deny = new javax.swing.JButton();
		chatboard = new javax.swing.JPanel();
		chat = new javax.swing.JTextField();
		chatsend = new javax.swing.JButton();
		chatscreen = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		user = new javax.swing.JPanel();
		keyboard = new javax.swing.JTextField();
		kb_enter = new javax.swing.JButton();
		background = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		info.setFont(new java.awt.Font("DX새날B", 0, 48)); // NOI18N
		info.setText("제시된 초성");
		getContentPane().add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 170, 60));

		word.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		word.setHorizontalAlignment(JTextField.CENTER);
		word.setFont(new Font("DX새날B", Font.BOLD, 75));
		word.setEnabled(false);

		getContentPane().add(word, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 190, 60));

		start.setFont(new java.awt.Font("DX바른필기 M", 0, 18)); // NOI18N
		start.setText("시작하기");
		start.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startActionPerformed(evt);
			}
		});
		getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

		c1.setBackground(new java.awt.Color(251, 238, 218, 100));
		c1.setLayout(null);

		name1.setFont(new java.awt.Font("DX새날B", 0, 36)); // NOI18N
		name1.setForeground(new java.awt.Color(255, 255, 255));
		name1.setHorizontalAlignment(JTextField.CENTER);
		name1.setText("");
		c1.add(name1);
		name1.setBounds(40, 60, 107, 37);

		jTextPane1.setEditable(false);
		jTextPane1.setFont(new java.awt.Font("돋움", 0, 25));
		jTextPane1.setHorizontalAlignment(JTextField.CENTER);
		jTextPane1.setToolTipText("client가 입력한 단어를 잠깐 보여주는 용도");
		word_c1.setViewportView(jTextPane1);

		c1.add(word_c1);
		word_c1.setBounds(20, 120, 150, 70);

		jLabel_name1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg2.PNG"))); // NOI18N
		c1.add(jLabel_name1);
		jLabel_name1.setBounds(10, 50, 170, 50);

		manager.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/crown.png"))); // NOI18N
		c1.add(manager);
		manager.setBounds(10, 10, 70, 50);

		client1.setForeground(new java.awt.Color(51, 0, 255));
		client1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		client1.setToolTipText("");
		c1.add(client1);
		client1.setBounds(10, 50, 170, 180);

		getContentPane().add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 190, 240));

		c4.setBackground(new java.awt.Color(251, 238, 218, 100));
		c4.setLayout(null);

		name4.setFont(new java.awt.Font("DX새날B", 0, 36)); // NOI18N
		name4.setForeground(new java.awt.Color(255, 255, 255));
		name4.setHorizontalAlignment(JTextField.CENTER);
		name4.setText("");
		c4.add(name4);
		name4.setBounds(50, 20, 130, 37);

		jTextPane2.setEditable(false);
		jTextPane2.setFont(new java.awt.Font("돋움", 0, 25));
		jTextPane2.setHorizontalAlignment(JTextField.CENTER);
		jTextPane2.setToolTipText("client가 입력한 단어를 잠깐 보여주는 용도");
		word_c4.setViewportView(jTextPane2);

		c4.add(word_c4);
		word_c4.setBounds(20, 80, 160, 70);

		jLabel_name2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg2.PNG"))); // NOI18N
		c4.add(jLabel_name2);
		jLabel_name2.setBounds(10, 10, 180, 50);

		client4.setForeground(new java.awt.Color(51, 0, 255));
		client4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		client4.setToolTipText("");
		c4.add(client4);
		client4.setBounds(10, 10, 180, 180);

		getContentPane().add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 200, 200));

		c2.setBackground(new java.awt.Color(251, 238, 218, 100));
		c2.setLayout(null);

		name2.setFont(new java.awt.Font("DX새날B", 0, 36)); // NOI18N
		name2.setForeground(new java.awt.Color(255, 255, 255));
		name2.setHorizontalAlignment(JTextField.CENTER);
		name2.setText("");
		c2.add(name2);
		name2.setBounds(50, 20, 130, 37);

		jTextPane6.setEditable(false);
		jTextPane6.setFont(new java.awt.Font("돋움", 0, 25));
		jTextPane6.setHorizontalAlignment(JTextField.CENTER);
		jTextPane6.setToolTipText("client가 입력한 단어를 잠깐 보여주는 용도");
		word_c2.setViewportView(jTextPane6);

		c2.add(word_c2);
		word_c2.setBounds(20, 80, 160, 70);

		jLabel_name3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg2.PNG"))); // NOI18N
		c2.add(jLabel_name3);
		jLabel_name3.setBounds(10, 10, 180, 50);

		client2.setForeground(new java.awt.Color(51, 0, 255));
		client2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		client2.setToolTipText("");
		c2.add(client2);
		client2.setBounds(10, 10, 180, 180);

		getContentPane().add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 200, 200));

		c5.setBackground(new java.awt.Color(251, 238, 218, 100));
		c5.setLayout(null);

		name5.setFont(new java.awt.Font("DX새날B", 0, 36)); // NOI18N
		name5.setForeground(new java.awt.Color(255, 255, 255));
		name5.setHorizontalAlignment(JTextField.CENTER);
		name5.setText("");
		c5.add(name5);
		name5.setBounds(50, 20, 130, 37);

		jTextPane3.setEditable(false);
		jTextPane3.setFont(new java.awt.Font("돋움", 0, 25));
		jTextPane3.setHorizontalAlignment(JTextField.CENTER);
		jTextPane3.setToolTipText("client가 입력한 단어를 잠깐 보여주는 용도");
		word_c5.setViewportView(jTextPane3);

		c5.add(word_c5);
		word_c5.setBounds(20, 80, 160, 70);

		jLabel_name4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg2.PNG"))); // NOI18N
		c5.add(jLabel_name4);
		jLabel_name4.setBounds(10, 10, 180, 50);

		client5.setForeground(new java.awt.Color(51, 0, 255));
		client5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		client5.setToolTipText("");
		c5.add(client5);
		client5.setBounds(10, 10, 180, 180);

		getContentPane().add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, 200, 200));

		c3.setBackground(new java.awt.Color(251, 238, 218, 100));
		c3.setLayout(null);

		name3.setFont(new java.awt.Font("DX새날B", 0, 36)); // NOI18N
		name3.setForeground(new java.awt.Color(255, 255, 255));
		name3.setHorizontalAlignment(JTextField.CENTER);
		name3.setText("");
		c3.add(name3);
		name3.setBounds(50, 20, 130, 37);

		jTextPane4.setEditable(false);
		jTextPane4.setFont(new java.awt.Font("돋움", 0, 25));
		jTextPane4.setHorizontalAlignment(JTextField.CENTER);
		jTextPane4.setToolTipText("client가 입력한 단어를 잠깐 보여주는 용도");
		word_c3.setViewportView(jTextPane4);

		c3.add(word_c3);
		word_c3.setBounds(20, 80, 160, 70);

		jLabel_name5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg2.PNG"))); // NOI18N
		c3.add(jLabel_name5);
		jLabel_name5.setBounds(10, 10, 180, 50);

		client3.setForeground(new java.awt.Color(51, 0, 255));
		client3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		client3.setToolTipText("");
		c3.add(client3);
		client3.setBounds(10, 10, 180, 180);

		getContentPane().add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 200, 200));

		voting.setBackground(new java.awt.Color(255, 255, 204, 80));

		jLabel1.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("투표하기");
		jLabel1.setToolTipText("");

		agree.setBackground(new java.awt.Color(153, 153, 255));
		agree.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		agree.setText("찬성");
		agree.setActionCommand("");
		agree.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				agreeActionPerformed(evt);
			}
		});

		deny.setBackground(new java.awt.Color(255, 153, 153));
		deny.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		deny.setText("반대");
		deny.setActionCommand("");
		deny.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				denyActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout votingLayout = new javax.swing.GroupLayout(voting);
		voting.setLayout(votingLayout);
		votingLayout.setHorizontalGroup(votingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(votingLayout.createSequentialGroup().addGroup(votingLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(votingLayout.createSequentialGroup().addContainerGap().addComponent(agree)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(deny))
						.addGroup(votingLayout.createSequentialGroup().addGap(42, 42, 42)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		votingLayout
				.setVerticalGroup(votingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, votingLayout.createSequentialGroup()
								.addContainerGap(17, Short.MAX_VALUE).addComponent(jLabel1).addGap(18, 18, 18)
								.addGroup(votingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(agree, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(deny, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(20, 20, 20)));

		getContentPane().add(voting, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, -1, -1));

		chatboard.setBackground(new java.awt.Color(251, 238, 218, 100));
		chatboard.setLayout(null);

		chat.setColumns(20);
		chat.setFont(new java.awt.Font("돋움", 0, 12)); // NOI18N
		chat.setToolTipText("sending chat msg");

		chatboard.add(chat);
		chat.setBounds(10, 190, 220, 50);

		chatsend.setFont(new java.awt.Font("DX바른필기 M", 0, 14)); // NOI18N
		chatsend.setText("전송");
		chatsend.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				enterActionPerformed(evt);
			}
		});
		chatboard.add(chatsend);
		chatsend.setBounds(220, 190, 60, 50);

		jTextArea2.setEditable(false);
		jTextArea2.setColumns(20);
		jTextArea2.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
		jTextArea2.setRows(5);
		jTextArea2.setToolTipText("chatting");
		chatscreen.setViewportView(jTextArea2);

		chatboard.add(chatscreen);
		chatscreen.setBounds(10, 10, 270, 180);

		getContentPane().add(chatboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 290, 250));

		user.setBackground(new java.awt.Color(255, 255, 255, 100));

		keyboard.setFont(new java.awt.Font("돋움", 0, 18)); // NOI18N
		keyboard.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		keyboard.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

		kb_enter.setFont(new java.awt.Font("DX바른필기 M", 0, 24)); // NOI18N
		kb_enter.setText("확인");
		kb_enter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				kb_enterActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout userLayout = new javax.swing.GroupLayout(user);
		user.setLayout(userLayout);
		userLayout.setHorizontalGroup(userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(userLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(keyboard, javax.swing.GroupLayout.PREFERRED_SIZE, 190,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(kb_enter)
						.addGap(22, 22, 22)));
		userLayout.setVerticalGroup(userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				userLayout.createSequentialGroup().addContainerGap(45, Short.MAX_VALUE)
						.addGroup(userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(keyboard, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(kb_enter))
						.addGap(20, 20, 20)));

		getContentPane().add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 490, 290, -1));

		background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/resources/bg_default.png"))); // NOI18N
		background.setText("전송");
		background.setToolTipText("");
		getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 630));

		pack();
	}

	/* send "start game" */
	private void startActionPerformed(java.awt.event.ActionEvent evt) {
		out.println("STARTGAME");
	}

	/* send that this client accepts that word */
	private void agreeActionPerformed(java.awt.event.ActionEvent evt) {
		Client.voteResult = '1';
		Client.didVote = true;
	}

	/* send that this client denied that word */
	private void denyActionPerformed(java.awt.event.ActionEvent evt) {
		Client.voteResult = '0';
		Client.didVote = true;
	}

	/* send chat message to server */
	public void enterActionPerformed(java.awt.event.ActionEvent evt) {
		out.println("CHATMESSAGE " + chat.getText());
		chat.setText("");
	}

	/* send choosed word to server */
	public void kb_enterActionPerformed(java.awt.event.ActionEvent evt) {
		this.isSubmitted = true;
		out.println("SUBMIT " + keyboard.getText());
		keyboard.setText("");
	}
}