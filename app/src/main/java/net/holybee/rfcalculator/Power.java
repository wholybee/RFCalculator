package net.holybee.rfcalculator;

import static java.lang.Math.round;
import java.lang.Math;
// import java.math.BigDecimal;

public class Power {

double watt;
	
public Power (String[] args) {

	
	
	try {
		watt = wattValue(args[0]);	
	
	for (int n=1;n < args.length;n=n+2) {
		
		if ( (args[n].equals("+") && (args[n+1].endsWith("dbm")) )) {		
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);
			float f = Float.parseFloat(s);
		addDbm(f);
		
			}
	
		else if ( (args[n].equals("-") && (args[n+1].endsWith("dbm")) )) { 
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);
			float f = Float.parseFloat(s);
		subDbm(f);
		
			}
		
		else if ( (args[n].equals("+") && (args[n+1].endsWith("dbW")) )) {		
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);
			float f = Float.parseFloat(s);
		addDbW(f);
		
			}
	
		else if ( (args[n].equals("-") && (args[n+1].endsWith("dbW")) )) { 
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);
			float f = Float.parseFloat(s);
		subDbW(f);
		
			}
		
		else if ( (args[n].equals("+") && !(args[n+1].endsWith("db")) )) { 
					addWatt(Float.parseFloat(args[n+1]));
				}
		
		else if ( (args[n].equals("-") && !(args[n+1].endsWith("db")) )) { 
					subWatt(Float.parseFloat(args[n+1]));
				}
	
		else if ( (args[n].equals("+") && (args[n+1].endsWith("db")) )) {		
				int l = args[n+1].length();
				String s = args[n+1].substring(0,l-2);
				float f = Float.parseFloat(s);
			addDb(f);
				}
		
		else if ( (args[n].equals("-") && (args[n+1].endsWith("db")) )) { 
				int l = args[n+1].length();
				String s = args[n+1].substring(0,l-2);
				float f = Float.parseFloat(s);
			subDb(f);
				}
			
			
	
		}
			

	} catch (Throwable e) {
		watt = -1;

	}

}
	public double wattValue (String arg) {
		if (arg.endsWith("dbm")){		
			int l = arg.length();
			String s = arg.substring(0,l-3);
			float dbm = Float.parseFloat(s);
			return (Math.pow(10,dbm/10))/1000;
		
		} else if (arg.endsWith("dbW")) {
			int l = arg.length();
			String s = arg.substring(0,l-3);
			float dbw = Float.parseFloat(s);
			return (Math.pow(10,dbw/10));
		}
		
		return Float.parseFloat(arg);
	}
	

	private void addDb(float db) {
		double dbW = 10 * (Math.log10(this.watt));
		dbW+=db;
		this.watt = round(Math.pow(10,dbW/10)*1000)/1000.0;
		}
	
	private void subDb(float db) {
		double dbW = 10 * (Math.log10(this.watt));
		dbW-=db;
		this.watt = round(Math.pow(10,dbW/10)*1000)/1000.0;
		}
	
	private void addDbm(float dbm) {
		double aWatt =  (Math.pow(10,dbm/10))/1000.0;
		
		this.watt += aWatt;
		}
	
	private void subDbm(float dbm) {
		double aWatt =  (Math.pow(10,dbm/10))/1000.0;
		
		this.watt -= aWatt;
	}
	
	private void addDbW(float dbw) {
		double aWatt =  (Math.pow(10,dbw/10));
		
		this.watt += aWatt;
	}
	
	private void subDbW(float dbw) {
		double aWatt =  (Math.pow(10,dbw/10));
		
		this.watt -= aWatt;
		}
	
	
	private void addWatt(double W) {
		this.watt+=W;

	}
	
	private void subWatt(double W) {
		this.watt-=W;
	}
	
	public double dbW() {
		return round(Math.log10(this.watt)*1000)/100.0;

	}
	
	public double dbm() {
	    return round(Math.log10(this.watt/.001)*1000)/100.0;

	}
	
	public double watt() {

	// return round(watt*1000+.5)/1000.0;
		return watt;
	}
	
		

}
