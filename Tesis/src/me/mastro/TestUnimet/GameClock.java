package me.mastro.TestUnimet;

import java.util.Date;

import org.bukkit.Difficulty;
import org.bukkit.scheduler.BukkitRunnable;

public class GameClock  extends BukkitRunnable{

	private Test test;
	private int CycleCount = 0;
	private int time = 0;
	
	public GameClock(){
		
	}
	
	public GameClock(Test _test){
		
		this.test = _test;
	}
	
	@Override
	public void run(){
		
		if(time==600){
			
			//test.eventRegister.addEvent(new Event());
			CycleCount++;
			time = 0;
		}
		
		
		if(CycleCount == 1 && time == 300){
			
			test.eventRegister.addEvent(new Event("11","11","DifficultyChangeEvent","11",new Date( )));
			this.test.setDifficulty(Difficulty.NORMAL);
		}
		
		if(CycleCount >= 2){
			
			test.eventRegister.addEvent(new Event("12","12","TimeOutEvent","12",new Date( )));
			this.cancel();
		}
		time++;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public int getCycleCount() {
		return CycleCount;
	}

	public void setCycleCount(int cycleCount) {
		CycleCount = cycleCount;
	}
	
	
}
