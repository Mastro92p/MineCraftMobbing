package me.mastro.TestUnimet;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import com.mongodb.MongoClient;
//import com.mongodb.DB;

public class EventRegister {
	
	public List<Event> eventList;
	private int eventCount;
	public SimpleDateFormat dateFormat;
	private String sessionUUID;
	private Test test;
	private UUID uuid;
	
	public EventRegister( ){
		
		eventList = new ArrayList<Event>();
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
	}
	

	public EventRegister(String _sessionUUID, Test _test) {
		
		test = _test;
		this.uuid = test.session;
		eventList = new ArrayList<Event>();
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		sessionUUID = _sessionUUID;
		
	}

	public String getSessionUUID() {
		return sessionUUID;
	}

	public void setSessionUUID(String sessionUUID) {
		this.sessionUUID = sessionUUID;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public void showEvents(){
		
		
		if(eventList.isEmpty()){
			
			System.out.println("List of event is empty");
			
		}else{
			
			for(Event event : eventList){
				
				System.out.println("info: "+ event.OriginID + " " + event.TargetID  
							+ " " + event.Type  +" "+event.Code + " " + event.Time );
				
			}
							
		}
		
			
	}
	
	public void writeToCsv(){
		
		System.out.println("Writing");
		try{
			
	        PrintWriter pw = new PrintWriter(new FileWriter(new File("result/TestResult_"+dateFormat.format(new Date())+"_"+sessionUUID +".csv"),true));
	        StringBuilder sb = new StringBuilder();
	        
	        for(Event e : this.eventList){
	        	
	        	sb.append(e.OriginID);
	        	sb.append(',');
	        	sb.append(e.TargetID);
	        	sb.append(',');
	        	sb.append(e.Type);
	        	sb.append(',');
	        	sb.append(e.Code);
	        	sb.append(',');
	        	sb.append(e.Time);
	        	sb.append("\n");
	        	
	        }
	        
	        pw.write(sb.toString());
	        pw.close();
			
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
		}
		
		this.eventList.clear();
		
	}
	
	public boolean eventListIsEmpty(){
		
		return this.eventList.isEmpty();
		
	}
	
	public void addEvent(Event event){
		
		eventCount++;
		
		if(this.eventCount < 10){
		
			this.eventList.add(event);
		
		}else{
			
			//write to file
			eventCount = 0;
			this.eventList.add(event);
			
			System.out.println("Writing to CSV");
			this.writeToCsv();
						
		}
		
		
	}

}
