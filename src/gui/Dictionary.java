package gui;

import java.io.*;
import java.util.*;

/**
 * Class "Dictionary"
 * @author Joe2357
 * @author songsari
 * 
 * @param usedWords[HashSet] : words that were used from client
 * @param templateWords[HashSet] : words that stores to server
 * @param chosung[String] : initial that was using in game
 * 
 * @param flag[Integer] : result of comparing
 *     - PASSED (0) : that word is acceptable
 *     - REJECT (1) : that word is not acceptable
 *     - DUPLICATED (2) : that word is already used
 *     - NEEDVOTE (3) : that word is promising
 */
public class Dictionary {

	public static HashSet<String> usedWords;
	public static HashSet<String> templateWords;
	public static String chosung;

	/* flag */
	public static final int PASSED = 0;
	public static final int REJECT = 1;
	public static final int DUPLICATED = 2;
	public static final int NEEDVOTE = 3;

	/* constructor */
	public Dictionary(String s) {
		usedWords = new HashSet<String>();
		templateWords = new HashSet<String>();
		chosung = s;
		try {
			readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* get all words from file */
	private static void readFromFile() throws IOException {
		BufferedReader br = null;
		try {
			String fileName = "./src/gui/words/" + chosung + ".txt";
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			String l = "";
			while ((l = br.readLine()) != null) { /* while EOF */
				l = l.trim();
				String[] a = l.split("   ");
				for (String b : a) {
					templateWords.add(b);
				}
			}
		} catch (Exception e) {
			System.out.println("파일을 불러올 수 없습니다!");
		} finally {
			br.close();
		}
	}

	/* check whether the word is promising */
	public int chosungcompare(String word) {
		/* not promising */
		if (!convertName(word).equals(chosung)) {
			return REJECT;
		}

		/* already used */
		else if (usedWords.contains(word)) {
			return DUPLICATED;
		}

		/* consider to vote */
		else if (!templateWords.contains(word)) {
			return NEEDVOTE;
		}

		/* accept */
		else {
			addToSet(word);
			return PASSED;
		}
	}

	/* add word to used set */
	public static void addToSet(String word) {
		usedWords.add(word);
	}

	/* get initial from word */
	private static String convertName(String name) {
		String rtName = "";
		char epName;
		try {
			for (int i = 0; i < name.length(); i++) {
				epName = name.charAt(i);
				rtName = rtName + Direct(epName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return rtName;
	}

	/* get initial from a word */
	public static String Direct(char b) {
		String[] template = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ",
				"ㅎ" };
		int first = (b - 44032) / (21 * 28); /* get initial from a character */
		if (first >= 0 && first <= 18) {
			return template[first];
		} else {
			return String.valueOf(b);
		}
	}

	/* save all words to file */
	public static void saveAllWords() {
		PrintWriter pw = null;
		try {
			String fileName = "./src/gui/words/" + chosung + ".txt";
			pw = new PrintWriter(new File(fileName), "UTF-8");
			for (String s : templateWords) {
				pw.print(s + "   ");
			}
		} catch (Exception e) {
			System.out.println("파일을 불러올 수 없습니다!");
		} finally {
			pw.close();
		}
	}
}
