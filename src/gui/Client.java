package gui;

import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class "Client"
 * @author Joe2357
 *
 * @param PORT[Integer] : server port
 *     - constant value (default = 12345)
 * @param IPAddress[String] : server ip address
 * @param MAXPLAYER[Integer] : max player in game room
 *     - constant value (defalut = 5)
 * 
 * @param socket[Socket] : connection socket to server
 * @param nickname[String] : nickname to use
 * 
 * @param in[BufferedReader] : input stream from server
 * @param out[PrintWriter] : output stream to server
 * 
 * @param gui[Login] : Login GUI
 * @param game[Ingame] : Ingame GUI
 * @param scoreboard[Scoreboard] : Scoreboard GUI
 * @param ranking[String[]] : ranking information to 1st ~ 5th
 * 
 * @param didVote[Boolean] : check whether this client already voted
 * @param voteResult[Character] : check what this client voted ( '1' : agree, '0' : deny )
 * 
 * @param selectRejoin[Boolean] : check whether the client want to rejoin
 */
public class Client {
	private static final int PORT = 12345;
	static String IPAddress;
	static final int MAXPLAYER = 5;

	static Socket socket = null;
	static String nickname = null;

	static BufferedReader in = null;
	static PrintWriter out = null;

	static Login gui;
	static Ingame game;
	static Scoreboard scoreboard;
	static String[] ranking;

	public static boolean didVote;
	public static char voteResult;

	public static boolean selectRejoin;

	/* constructor */
	public static void main(String[] args) throws IOException {
		new Title();
		getInformation();
		enterToServer();
	}

	/* entire join to game room & game play */
	public static void enterToServer() {
		try {
			/* connect to server */
			socket = new Socket(IPAddress, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			game = new Ingame(out);

			/* check nickname with server */
			while (true) {
				out.println("SETNICK " + nickname); /* ask to server */
				String answer = in.readLine();
				if (answer == null) {
				} else if (answer.equals("ACCEPTNICK")) { /* accepted */
					openIngame();
					break;
				} else if (answer.equals("DECLINENICK")) { /* declined */
					JOptionPane.showMessageDialog(null, "닉네임이 중복되었습니다.");
					new Login();
					getInformation();
				} else if (answer.equals("FULL")) { /* the room is full */
					System.out.println("[Server] 방이 꽉 차있어서 입장이 거부되었습니다.");
					System.exit(0);
				} else if (answer.equals("ALREADYSTARTED")) { /* game already started */
					System.out.println("[Server] 이미 게임이 진행중입니다.");
					System.exit(0);
				}
			}

			/* after connected to server */
			while (true) {
				String line = in.readLine();
				if (line.startsWith("MESSAGE")) { /* message from another player or server */
					game.jTextArea2.append(line.substring(8) + "\n");
				} else if (line.startsWith("JOIN")) { /* someone joined to room */
					joinPlayer(line.substring(5));
				} else if (line.startsWith("QUIT")) { /* someone quitted from room */
					quitPlayer(line.substring(5));
				} else if (line.startsWith("GAMESTART")) { /* game will be started */
					out.println("GAMEACK");
				} else if (line.startsWith("SETINFO")) { /* word initial has been set */
					game.word.setText(line.substring(8));
				} else if (line.startsWith("HISTURN")) { /* server alert you who has turn */
					alertTurn(line.substring(8));
				} else if (line.startsWith("YOURTURN")) { /* server alert that it's your turn */
					while (true) { /* wait until submit a word */
						if (game.isSubmitted) {
							break;
						}
						System.out.print("");
					}
					game.isSubmitted = false;
				} else if (line.startsWith("SUBMITTED")) { /* player submitted a word */
					applyWord(line.substring(10));
				} else if (line.startsWith("VOTE")) { /* server alert this player to vote */
					didVote = false;
					while (true) {
						if (didVote) {
							break;
						}
						System.out.print("");
					}
					out.println("VOTED " + voteResult); /* send vote result to server */
				}

				else if (line.startsWith("DEAD")) { /* alert a player has been rejected */
					death(line.substring(5));
				} else if (line.startsWith("LIVE")) { /* alert a player didn't reject (revive) */
					live(line.substring(5));
				} else if (line.startsWith("GAMEEND")) { /* alert that the game end */
					game.setVisible(false);
					out.println("ENDACK");
				} else if (line.startsWith("RANKING")) { /* tell the ranking information */
					showRank(line.substring(8));
					break;
				}
			}
		} catch (IOException e) { /* disconnected with server */
			System.out.println("서버가 응답하지 않습니다.");
			System.exit(0);
		}

		/* wait until player set rejoin or not */
		selectRejoin = false;
		while (true) {
			if (selectRejoin) {
				break;
			}
			System.out.println("");
		}
		enterToServer();
		return;
	}

	/* open Ingame GUI */
	public static void openIngame() {
		game.setVisible(true);
		game.pack();
		game.setLocationRelativeTo(null);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/* show joined player to GUI */
	public static void joinPlayer(String s) {
		char order = s.charAt(0);
		s = s.substring(2);
		if (order == '0') {
			game.name1.setText(s);
		} else if (order == '1') {
			game.name2.setText(s);
		} else if (order == '2') {
			game.name3.setText(s);
		} else if (order == '3') {
			game.name4.setText(s);
		} else if (order == '4') {
			game.name5.setText(s);
		}
	}

	/* show quitted player to GUI */
	public static void quitPlayer(String s) {
		char order = s.charAt(0);
		if (order == '0') {
			game.name1.setText("");
		} else if (order == '1') {
			game.name2.setText("");
		} else if (order == '2') {
			game.name3.setText("");
		} else if (order == '3') {
			game.name4.setText("");
		} else if (order == '4') {
			game.name5.setText("");
		}
	}

	/* show whose turn to GUI */
	public static void alertTurn(String s) {
		game.c1.setBackground(new java.awt.Color(242, 231, 207));
		game.c2.setBackground(new java.awt.Color(242, 231, 207));
		game.c3.setBackground(new java.awt.Color(242, 231, 207));
		game.c4.setBackground(new java.awt.Color(242, 231, 207));
		game.c5.setBackground(new java.awt.Color(242, 231, 207));
		char outindex = s.charAt(0);
		if (outindex == '0') {
			game.c1.setBackground(new java.awt.Color(255, 242, 0));
		} else if (outindex == '1') {
			game.c2.setBackground(new java.awt.Color(255, 242, 0));
		} else if (outindex == '2') {
			game.c3.setBackground(new java.awt.Color(255, 242, 0));
		} else if (outindex == '3') {
			game.c4.setBackground(new java.awt.Color(255, 242, 0));
		} else if (outindex == '4') {
			game.c5.setBackground(new java.awt.Color(255, 242, 0));
		}
	}

	/* show the word that player inputted */
	public static void applyWord(String s) {
		game.jTextPane1.setText("");
		game.jTextPane2.setText("");
		game.jTextPane3.setText("");
		game.jTextPane4.setText("");
		game.jTextPane6.setText("");
		char outindex = s.charAt(0);
		String word = s.substring(2);
		if (outindex == '0') {
			game.jTextPane1.setText(word);
		} else if (outindex == '1') {
			game.jTextPane6.setText(word);
		} else if (outindex == '2') {
			game.jTextPane4.setText(word);
		} else if (outindex == '3') {
			game.jTextPane2.setText(word);
		} else if (outindex == '4') {
			game.jTextPane3.setText(word);
		}
	}

	/* to show that client failed */
	public static void death(String s) {
		char outindex = s.charAt(0);
		if (outindex == '0') { /* first player */
			game.word_c1.setVisible(false);
			game.client1.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/dead.PNG")));
		} else if (outindex == '1') { /* second player */
			game.word_c2.setVisible(false);
			game.client2.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/dead.PNG")));
		} else if (outindex == '2') { /* third player */
			game.word_c3.setVisible(false);
			game.client3.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/dead.PNG")));
		} else if (outindex == '3') { /* fourth player */
			game.word_c4.setVisible(false);
			game.client4.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/dead.PNG")));
		} else if (outindex == '4') { /* fifth player */
			game.word_c5.setVisible(false);
			game.client5.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/dead.PNG")));
		}
	}

	/* to show that client revive */
	/* not in-game, this method was used when some players left in-game */
	public static void live(String s) {
		char outindex = s.charAt(0);
		if (outindex == '0') {
			game.word_c1.setVisible(true);
			game.client1
					.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		} else if (outindex == '1') {
			game.word_c2.setVisible(true);
			game.client2
					.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		} else if (outindex == '2') {
			game.word_c3.setVisible(true);
			game.client3
					.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		} else if (outindex == '3') {
			game.word_c4.setVisible(true);
			game.client4
					.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		} else if (outindex == '4') {
			game.word_c5.setVisible(true);
			game.client5
					.setIcon(new javax.swing.ImageIcon(game.getClass().getResource("/gui/resources/client_bg.PNG"))); // NOI18N
		}
	}

	/* open Scoreboard GUI */
	public static void showRank(String s) {
		scoreboard = new Scoreboard();
		scoreboard.setVisible(true);
		scoreboard.pack();
		scoreboard.setLocationRelativeTo(null);
		scoreboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s = s.substring(0);

		/* #name#name#name#name#name */
		ranking = s.split("#"); // name name name name name
		scoreboard.jTextField6.setText(ranking[1]); // 1등
		scoreboard.jTextField7.setText(ranking[2]); // 2등
		scoreboard.jTextField8.setText(ranking[3]); // 3등
		scoreboard.jTextField9.setText(ranking[4]); // 4등
		scoreboard.jTextField10.setText(ranking[5]); // 5등
	}

	/* open GUI to get information to enter server */
	public static void getInformation() {
		while (true) {
			if (Login.check_IP) {
				nickname = Login.nickname;
				IPAddress = Login.IPADDRESS;
				break;
			}
			System.out.print("");
		}
		Login.check_IP = false;
	}
}