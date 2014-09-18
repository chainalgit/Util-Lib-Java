package com.autonavi.newcollector.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Position {
	
	public int lon;
	
	public int lat;
	
	public int alt;
	
	public int radius;
	
	public int speed;
	
	public short direction;
	
	public byte satellitesNum;
	
	public int signals;
	
	public short hdop;
	
	public byte eaNum;
	
	public ArrayList<Ea> listEa = new ArrayList<Ea>();
	
	public byte isMock;
	
	public byte isDrift;
	
	public long timeSys;
	
	public long timeGps;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");

	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		result.append("Position=lon=" + lon + ";lat=" + lat + ";alt=" + alt
				+ ";radius=" + radius + ";speed=" + speed + ";direction="
				+ direction + ";satellitesNum=" + satellitesNum + ";signals="
				+ signals + ";hdop=" + hdop + ";eaNum=" + eaNum + ";listEa=");
		
		for(int i=0;i<listEa.size();i++){
			
			if(i != (listEa.size() -1)){
				
				result.append(listEa.get(i).toString()+"*");
			}else{
				
				result.append(listEa.get(i).toString());
			}
		}		
		
		return result.toString();	
		
	}

	public String toStringV4() {
		StringBuffer result = new StringBuffer();
		
		result.append("Position=lon=" + lon + ";lat=" + lat + ";alt=" + alt
				+ ";radius=" + radius + ";speed=" + speed + ";direction="
				+ direction + ";isMock=" + isMock + ";isDrift="
				+ isDrift + ";SysTime=" + timeSys + ";GpsTime=" + timeGps
				+ ";FormatTime=" +  sdf.format(timeSys));
		
		return result.toString();	
	}	
}
