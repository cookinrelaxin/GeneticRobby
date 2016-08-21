package robby;

public class Timeline {
	static final int GENERATION_COUNT = 1000;
	int currentGeneration = 1;
	Population currentPop;
	
	public Timeline(){
		this.currentPop = new Population();
		while(currentGeneration <= GENERATION_COUNT){
			currentPop.findAlphas();
			System.out.println(currentPop.alphaMale.ultimateScore + "    " + currentGeneration);
			currentPop.generateNextPopulation();
			currentPop.evaluatePopulation();
			currentGeneration ++;
		}
	}
	
	public static void main(String[] args){
		Timeline currentCycle = new Timeline();
		//System.out.println(currentCycle.currentPop.alphaMale.ultimateScore);
		StrategyDisplayPanel display = new StrategyDisplayPanel(currentCycle.currentPop.alphaMale);
	}
	
}
