package robby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Strategy {

	private static final int TRIALS_TO_CALCULATE_FITNESS = 100;
	public ArrayList<String> situations;
	public String responses;
	private int[] trialScores;
	public int ultimateScore;
	
	
	public Strategy(){
		this.trialScores = new int[100];
		this.situations = new ArrayList<String>();
		this.responses = new String();
		char[] situationChars = new char[5];
		for (situationChars[0] = 'a' ; situationChars[0] <= 'c' ; situationChars[0]++)
		    for (situationChars[1] = 'a' ; situationChars[1] <= 'c' ; situationChars[1]++)
		        for (situationChars[2] = 'a' ; situationChars[2] <= 'c' ; situationChars[2]++)
		            for (situationChars[3] = 'a' ; situationChars[3] <= 'c' ; situationChars[3]++)
		            	for (situationChars[4] = 'a' ; situationChars[4] <= 'c' ; situationChars[4]++){	
		            		String situation = new String(situationChars);
		            		this.situations.add(situation);
		            		Random rand = new Random();
		            		int n = rand.nextInt(6);
		            		this.responses += n;
		            	}
		//System.out.println(responses);
	}
	
	public void calculateFitness(){
		
		for(int i = 0; i < TRIALS_TO_CALCULATE_FITNESS; i ++){
			RoomModelView room = new RoomModelView();
			Rob tester = new Rob(room.tiles, this);
			this.trialScores[i] = tester.go();
		}
		int sum = 0;
		for(int score : this.trialScores){
			sum += score;
		}
			
		this.ultimateScore = sum / TRIALS_TO_CALCULATE_FITNESS;
		//System.out.println(ultimateScore);

	}


}
