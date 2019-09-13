package me.mastro.TestUnimet;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable{
	
	private int time;
	private Test test;
	
	public Countdown(){
		
		time = 30;
	}
	
	public Countdown(Test _test){
		
		this.test = _test;
		time = 30;
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if(time <= 0){
			
			test.setFlag();
			test.eventRegister.addEvent(new Event("01","01","GameStartEvent","01",new Date()));
			Bukkit.getServer().broadcastMessage("Test Started. GO GO GO!");
			this.cancel();
		
		}else{
			
			if(time == 30 || time == 15 || time == 10){
				
				Bukkit.getServer().broadcastMessage("Starting test in: "+time);
			}
			
			if(time < 10){
				
				
				Bukkit.getServer().broadcastMessage(Integer.toString(time));
				
			}
			
			Test.coutAdd();
			time--;
			
		}
		
	}

}
