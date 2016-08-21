package robby;

import java.util.ArrayList;
import java.util.Random;

public class Population extends ArrayList<Strategy> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4023300780989730038L;
	
	static final int POPULATION_SIZE = 200;
	static final int MUTATION_RATE = 10000;
	public Strategy alphaMale;
	public Strategy alphaFemale;
	//public int[] scores;

	public Population(){
	//	this.scores = new int[200];
		for(int i = 0; i < POPULATION_SIZE; i ++){
			Strategy strat = new Strategy();
			strat.calculateFitness();
			this.add(strat);
		}
	}
	
	public void findAlphas(){
		Strategy alphaMale = this.get(0);
		Strategy alphaFemale = this.get(1);
		//System.out.println(alphaMale);
		for(Strategy strat : this){
			if (strat.ultimateScore > alphaMale.ultimateScore){
				alphaMale = strat;
				continue;
			}
			if (strat.ultimateScore > alphaFemale.ultimateScore){
				alphaFemale = strat;
				continue;
			}
		}
		this.alphaFemale = alphaFemale;
		this.alphaMale = alphaMale;
	}
	public void evaluatePopulation(){
		for(Strategy strat : this){
			strat.calculateFitness();
		}
	}
	public void generateNextPopulation(){
		for(Strategy strat : this){
			Random rand = new Random();
			int splitIndex = rand.nextInt(242);
			String maleSubstring = alphaMale.responses.substring(0, splitIndex);
			String femaleSubstring = alphaFemale.responses.substring(splitIndex);
			strat.responses = maleSubstring + femaleSubstring;
			
			int mutationChance = rand.nextInt(MUTATION_RATE);
			if (mutationChance == 0){
				int randomMove = rand.nextInt(6);
				String mutatedResponses = new String();
				mutatedResponses = alphaMale.responses.substring(0, splitIndex) + String.valueOf(randomMove) + alphaFemale.responses.substring(splitIndex + 1);
				strat.responses = mutatedResponses;
			}
		}
	}

}
