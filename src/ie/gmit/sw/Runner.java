package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Runner {

	public static void main(String[] args) throws Exception {
		
		//decrypting message TTFOHATGRNREEANOETYRCIMHHAKT
		
		String plainText;
		String gram = "4grams.txt";
		Scanner console = new Scanner(System.in);
		System.out.print("Enter Text: ");
		plainText = console.nextLine();
		
		RailFence railFence = new RailFence();
		String cypherText = railFence.encrypt(plainText, 5);
		
		FileParser fileParse = new FileParser();
		
		Map<String, Double> map = new ConcurrentHashMap<String, Double>();
		map = fileParse.parse(gram);
		
		TextScorer txtScorer = new TextScorer(map);
		
		CypherBreaker cb = new CypherBreaker(cypherText, txtScorer);
		
		
	}

}
