package me.mastro.TestUnimet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	
	public String OriginID;
	public String TargetID;
	public String Type;
	public String Time;
	public String Code;
	
	public Event(){
		
	}
	
	public Event(String originID, String targetID, String type,String _Code, Date time) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		OriginID = originID;
		TargetID = targetID;
		Type = type;
		Time = dateFormat.format(time);
		Code = _Code;
	}
	
	public String getOriginID() {
		return OriginID;
	}
	
	public void setOriginID(String originID) {
		OriginID = originID;
	}
	
	public String getTargetID() {
		return TargetID;
	}
	
	public void setTargetID(String targetID) {
		TargetID = targetID;
	}
	
	public String getType() {
		return Type;
	}
	
	public void setType(String type) {
		Type = type;
	}
	
	public String getTime() {
		return Time;
	}
	
	public void setTime(String time) {
		Time = time;
	}
	
	

}
