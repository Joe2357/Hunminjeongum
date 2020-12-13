package gui;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Class "Server"
 * @author Joe2357
 *
 * @param PORT[Integer] : server port
 *     - constant value (default = 12345)
 * @param server[ServerSocket] : server socket
 * @param MAXPLAYER[Integer] : max player in game room
 *     - constant value (default = 5)
 * 
 * @param firstLetter[String[]] : available initials
 *     - constant value (default = "ㄹㅂ", "ㅈㅂ", "ㅊㅇ", "ㅎㅇ")
 * @param WORDSCOUNT[Integer] : available initials count
 *     - constant value (default = 4)
 * @param wordsSelected[Integer] : index that was chosen
 * @param dictionary[Dictionary] : Dictionary object
 * 
 * @param nicknameSet[String[]] : all players' nickname list
 * @param writerList[PrintWriter[]] : all players' outputstream list
 * @param isDead[Boolean[]] : check whether ith player has been rejected
 * 
 * @param isGameStarted[Boolean] : check whether game started or not
 * @param playerCount[Integer] : online player count
 * 
 * @param deathCount[Integer] : rejected player count
 * @param whosTurn[Integer] : index who has turn
 * @param ranking[String[]] : nickname of ranking (1 ~ 5)
 * @param rankCount[Integer] : count of player
 * @param acked[Integer] : room exit players count
 * 
 * @param voted[Boolean[]] : check who remained to vote
 * @param voteResult[Boolean[]] : accept or deny 
 * @param needVote[Integer] : remained vote count
 */
public class Server {

	private static final int PORT = 12345;
	private static ServerSocket server;
	private static final int MAXPLAYER = 5;

	public static final String[] firstLetter = { "ㄹㅂ", "ㅈㅂ", "ㅊㅇ", "ㅎㅇ" };
	public static final int WORDSCOUNT = 4;
	public static int wordsSelected;
	public static Dictionary dictionary;

	private static HashSet<String> nicknameSet;
	private static HashSet<PrintWriter> writerSet;

	private static String[] nickNameList;
	private static PrintWriter[] writerList;
	private static boolean[] isDead;

	private static boolean isGameStarted;
	private static int playerCount;

	private static int deathCount;
	private static int whosTurn;
	private static String[] ranking;
	private static int rankCount;
	private static int acked;

	private static boolean[] voted;
	private static boolean voteResult;
	private static int needVote;

	/* constructor */
	public static void main(String[] args) throws IOException {
		server = new ServerSocket(PORT);
		System.out.println("[Server] 서버 오픈");
		init();
		try {
			while (true) {
				new GameThread(server.accept()).start();
			}
		} finally {
			server.close();
		}
	}

	/**
	 * Class "GameThread"
	 * @author Joe2357
	 * 
	 * @param name[String] : client nickname
	 * @param socket[Socket] : client connected socket
	 * @param in[InputStream] : client output stream
	 * @param out[OutputStream] : client input stream
	 */
	private static class GameThread extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		public GameThread(Socket sc) {
			this.socket = sc;
		}

		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				/* client try to join room */
				while (true) {
					if (!isGameStarted) { /* game is not playing */
						String line = in.readLine();
						if (line == null) {
							return;
						} else if (line.startsWith("SETNICK")) { /* try to set nickname */
							name = line.substring(8);
							synchronized (name) {
								if (!nicknameSet.contains(name)) { /* no duplication */
									break;
								} else { /* deny nickname */
									out.println("DECLINENICK");
								}
							}
						}
					} else {
						out.println("ALREADYSTARTED");
						return;
					}
				}
			} catch (Exception e) { /* client disconnected */
				return;
			}

			if (playerCount >= MAXPLAYER) { /* room is full */
				out.println("FULL");
				return;
			}

			this.enter(); /* player joined */

			/* before game starts */
			try {
				while (true) {
					String line = in.readLine();
					if (line == null) {
						this.quit();
						return;
					} else if (line.startsWith("CHATMESSAGE")) { /* chatting message */
						sendToAllClient("MESSAGE " + name + " : " + line.substring(12));
					} else if (line.startsWith("STARTGAME")) { /* game start ask */
						this.startGame();
					} else if (line.startsWith("GAMEACK")) { /* get acked, escape loop */
						break;
					}
				}
			} catch (Exception e) { /* client disconnected */
				this.quit();
				return;
			}

			/* after the game started */
			try {
				while (true) {
					String line = in.readLine(); /* read protocol message from client */
					if (line == null) {
						this.quit();
						return;
					} else if (line.startsWith("CHATMESSAGE")) { /* protocol : chat message */
						sendToAllClient("MESSAGE " + name + " : " + line.substring(12));
					} else if (line.startsWith("SUBMIT")) { /* protocol : submit a word */
						if (nickNameList[whosTurn].equals(name)) { /* can submit when the client has turn */
							sendToAllClient(
									"SUBMITTED " + whosTurn + " " + line.substring(7)); /* alert to all clients */
							checkWord(line.substring(7)); /* check whether submitted word is acceptable or not */
						}
					} else if (line.startsWith("VOTED")) { /* protocol : let all clients to vote */
						if (!voted[getCurrentPosition()]) { /* if the client has permission to vote */
							voteResult &= (line.charAt(6) == '1'); /* gather all voted result ( Unanimous ) */
							voted[getCurrentPosition()] = true; /* remove permission ( voted ) */
							writerList[getCurrentPosition()].println("MESSAGE [Vote] 투표가 완료되었습니다.");
							--needVote; /* this client voted ( if needVote is 0, it means all clients voted ) */
						}
					} else if (line.startsWith(
							"ENDACK")) { /* protocol : when server get game end acknowledgement from client */
						++acked;
						out.println(getRank());
						break;
					}
				}
			} catch (Exception e) { /* client disconnected */
				this.quit();
				return;
			}

			if (acked == playerCount) { /* when every client were disconnected */
				Dictionary.saveAllWords(); /* re-write all words ( origin + newer ) to file */
				init(); /* re-initialize game */
			}
		}

		/* return index of current player exists */
		public int getCurrentPosition() {
			int i;
			for (i = 0; i < playerCount; ++i) {
				if (nickNameList[i].equals(name)) {
					break;
				}
			}
			return i;
		}

		/* join player to room */
		public void enter() {
			out.println("ACCEPTNICK");

			nicknameSet.add(name);
			writerSet.add(out);
			nickNameList[playerCount] = name;
			writerList[playerCount] = out;

			for (int i = 0; i < playerCount; ++i) {
				out.println("JOIN " + i + " " + nickNameList[i]);
			}

			sendToAllClient("JOIN " + playerCount + " " + name);

			System.out.print("[Server] " + name + "님이 들어왔습니다.");
			System.out.println("  현재 참여인원 : " + ++playerCount);
			sendToAllClient("MESSAGE " + "[Server] " + name + "님이 게임에 입장하였습니다.");
			sendToAllClient("MESSAGE " + "[Server] 현재 접속인원 : " + playerCount);
		}

		/* quit player */
		public void quit() {
			int i = getCurrentPosition();
			--playerCount;
			for (int j = i; j < playerCount; ++j) {
				if (isGameStarted) {
					isDead[j] = isDead[j + 1];
					if (isDead[j]) {
						sendToAllClient("DEAD " + j);
					} else {
						sendToAllClient("LIVE " + j);
					}
				}
				nickNameList[j] = nickNameList[j + 1];
				writerList[j] = writerList[j + 1];
				sendToAllClient("JOIN " + j + " " + nickNameList[j]);
			}

			sendToAllClient("QUIT " + playerCount);
			sendToAllClient("LIVE " + playerCount);

			System.out.print("[Server] " + name + "님이 나갔습니다.");
			System.out.println("  현재 참여인원 : " + playerCount);
			sendToAllClient("MESSAGE " + "[Server] " + name + "님이 게임을 나갔습니다.");
			sendToAllClient("MESSAGE " + "[Server] 현재 접속인원 : " + playerCount);

			if (isGameStarted) {
				nickNameList[playerCount] = "";
				ranking[rankCount] = name;
				--rankCount;
				if (i == whosTurn) {
					if (i == playerCount) {
						whosTurn = 0;
					}
					giveTurn();
				}
			}

			nicknameSet.remove(name);
			writerSet.remove(out);

			if (playerCount == 0) {
				init();
			}
		}

		/* game start method */
		public void startGame() throws IOException {
			/* authorization check */
			if (!nickNameList[0].equals(this.name)) { /* not a owner */
				this.out.println("MESSAGE " + "[System] 권한이 없습니다.");
				return;
			} else if (isGameStarted) { /* game already started */
				return;
			} else if (playerCount < 3) {
				this.out.println("MESSAGE " + "[System] 인원이 부족하여 게임을 실행할 수 없습니다.");
				return;
			}

			/* game initialize */
			isGameStarted = true;
			for (int i = 0; i < MAXPLAYER; ++i) {
				isDead[i] = false;
			}
			rankCount = playerCount - 1;
			deathCount = 0;
			whosTurn = 0;

			/* select initial */
			wordsSelected = (int) ((Math.random() * 10000) % WORDSCOUNT);
			dictionary = new Dictionary(firstLetter[wordsSelected]);

			sendToAllClient("SETINFO " + firstLetter[wordsSelected]);

			System.out.println("게임을 실행합니다..");
			sendToAllClient("MESSAGE " + "[System] 게임을 시작합니다!");
			sendToAllClient("GAMESTART");

			giveTurn();
		}

		/* roll the turn */
		public void giveTurn() {
			if (playerCount - deathCount <= 1) { /* game finished */
				if (playerCount - deathCount == 0) {
					ranking[0] = name;
				} else {
					for (int i = 0; i < MAXPLAYER; ++i) {
						if (!isDead[i]) {
							ranking[0] = nickNameList[i];
							break;
						}
					}
				}
				sendToAllClient("GAMEEND");
				return;
			}

			/* find who isn't rejected yet */
			while (isDead[whosTurn]) {
				whosTurn = (whosTurn + 1) % playerCount;
			}

			sendToAllClient("HISTURN " + whosTurn);

			sendToAllClient("MESSAGE [System] " + nickNameList[whosTurn] + "의 차례입니다.");
			writerList[whosTurn].println("MESSAGE [System] 당신의 차례입니다.");
			writerList[whosTurn].println("YOURTURN");
		}

		/* check whether submitted word is available */
		public void checkWord(String word) {
			int ret = dictionary.chosungcompare(word);
			switch (ret) {
			case Dictionary.REJECT:
			case Dictionary.DUPLICATED: {
				failed();
			}
			case Dictionary.PASSED: {
				whosTurn = (whosTurn + 1) % playerCount;
				giveTurn();
				break;
			}
			case Dictionary.NEEDVOTE: {
				/* give vote change to all available player */
				voteInit();

				while (true) {
					if (needVote == 0) {
						break;
					}
					System.out.print("");
				}

				if (!voteResult) {
					failed();
				}

				Dictionary.templateWords.add(word);
				Dictionary.addToSet(word);

				whosTurn = (whosTurn + 1) % playerCount;
				giveTurn();
				break;
			}
			}
		}

		/* vote initialize */
		public void voteInit() {
			voted = new boolean[playerCount];
			for (int i = 0; i < playerCount; ++i) {
				voted[i] = (i == whosTurn || isDead[i]);
			}
			for (int i = 0; i < playerCount; ++i) {
				if (i != whosTurn && !isDead[i]) {
					writerList[i].println("MESSAGE [Vote] 투표하세요!");
					writerList[i].println("VOTE");
				} else {
					writerList[i].println("MESSAGE [Vote] 투표가 진행중입니다. 잠시만 기다려주세요..");
				}
			}
			voteResult = true;
			needVote = playerCount - deathCount - 1;
		}

		/* this player was failed */
		public void failed() {
			isDead[whosTurn] = true; /* check failed */
			++deathCount; /* add 1 to player failed count ( remained_player = player_count - death_count ) */
			sendToAllClient("DEAD " + whosTurn); /* alert this client was failed to all clients */

			/* set client name to ranking ( reverse of failed order ) */
			ranking[rankCount] = name;
			--rankCount;
		}

		public String getRank() {
			String retval = "RANKING ";
			for (int i = 0; i < 5; ++i) {
				retval += ("#" + ranking[i]);
			}
			return retval;
		}
	}

	/* game reset method */
	public static void init() {
		acked = 0;
		nicknameSet = new HashSet<String>();
		writerSet = new HashSet<PrintWriter>();
		nickNameList = new String[5];
		ranking = new String[5];
		for (int i = 0; i < 5; ++i) {
			nickNameList[i] = " ";
			ranking[i] = " ";
		}
		writerList = new PrintWriter[5];
		isDead = new boolean[5];

		playerCount = 0;
		isGameStarted = false;

		deathCount = 0;
		whosTurn = 0;
		rankCount = 0;

		System.out.println("게임을 리셋합니다");
	}

	/* broadcast protocol to all client */
	public static void sendToAllClient(String msg) {
		for (PrintWriter pw : writerSet) {
			pw.println(msg);
			pw.flush();
		}
	}
}