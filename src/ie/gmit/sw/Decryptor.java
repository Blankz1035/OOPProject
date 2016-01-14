package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;

public class Decryptor implements Runnable{
    
    private BlockingQueue<Resultable> queue;
    private String cypherText;
    private int key;
    private TextScorer txtScorer;
    
    public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key, TextScorer txtScorer){
        super();
        this.queue = queue;
        this.cypherText = cypherText;
        this.key = key;
        this.txtScorer = txtScorer;
    }
    
    public void run(){
        RailFence railFence = new RailFence();
        String plainText = railFence.decrypt(cypherText, key);
        
        //get score
        double score = txtScorer.getScore(plainText);
        Resultable r = new Result(plainText, key, score);
        
        try {
        	System.out.println("Put in the score for the key " +key + " in the queue");
			queue.put(r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("Thread for " + key + " is done");
    }
}