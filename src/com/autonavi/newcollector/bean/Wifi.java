package com.autonavi.newcollector.bean;
import java.util.ArrayList;


public class Wifi {
	
	public byte wifiNum;
	
	public ArrayList<Ap> listAp = new ArrayList<Ap>();

	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		result.append("wifiNum=" + wifiNum+";listAP=");
		
		for(int i=0;i< listAp.size();i++){
			if(i != (listAp.size() -1)){
				
				result.append(listAp.get(i).toString()+"*");

			}else{
				
				result.append(listAp.get(i).toString());
			}
		}
		
		return  result.toString();
	}
	
	
	
	
}
