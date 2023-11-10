package net.holybee.rfcalculator;

import static java.lang.Math.round;


import android.util.Log;
import android.view.AttachedSurfaceControl;

import java.lang.Math;
import java.math.BigDecimal;
import java.math.MathContext;

import ch.obermuhlner.math.big.BigDecimalMath;
// import java.math.BigDecimal;

public class Power {

BigDecimal watt;
MathContext mContext = new MathContext(4);
	
public Power (String[] args) {

	
	
	try {
		watt = wattValue(args[0]);

		Log.e ("Power", String.valueOf(watt));

	for (int n=1;n < args.length;n=n+2) {
		Log.e("Arg",args[n]);
		Log.e("Arg+1",args[n+1]);

		if ( (args[n].equals("+") && (args[n+1].endsWith("dbm")) )) {		
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);

			BigDecimal f = new BigDecimal(s);
		addDbm(f);
		
			}
	
		else if ( (args[n].equals("-") && (args[n+1].endsWith("dbm")) )) { 
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);
			BigDecimal f = new BigDecimal(s);
		subDbm(f);
		
			}
		
		else if ( (args[n].equals("+") && (args[n+1].endsWith("dbW")) )) {		
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);
			Log.e("Power dbW+",s);
			BigDecimal f = new BigDecimal(s);
		addDbW(f);
		
			}
	
		else if ( (args[n].equals("-") && (args[n+1].endsWith("dbW")) )) { 
			int l = args[n+1].length();
			String s = args[n+1].substring(0,l-3);
			BigDecimal f = new BigDecimal(s);
		subDbW(f);
		
			}
		
		else if ( (args[n].equals("+") && !(args[n+1].endsWith("db")) )) { 
					addWatt(new BigDecimal(args[n+1]));
				}
		
		else if ( (args[n].equals("-") && !(args[n+1].endsWith("db")) )) { 
					subWatt(new BigDecimal(args[n+1]));
				}
	
		else if ( (args[n].equals("+") && (args[n+1].endsWith("db")) )) {		
				int l = args[n+1].length();
				String s = args[n+1].substring(0,l-2);
				BigDecimal f = new BigDecimal(s);
			addDb(f);
				}
		
		else if ( (args[n].equals("-") && (args[n+1].endsWith("db")) )) { 
				int l = args[n+1].length();
				String s = args[n+1].substring(0,l-2);
				BigDecimal f = new BigDecimal(s);
			subDb(f);
				}
			
			
	
		}
			

	} catch (Throwable e) {
		watt = BigDecimal.valueOf(-1);

	}

}
	public BigDecimal wattValue (String arg) {
		if (arg.endsWith("dbm")){		
			int l = arg.length();
			String s = arg.substring(0,l-3);
			BigDecimal dbm = new BigDecimal(s);
			return (BigDecimalMath.pow(BigDecimal.valueOf(10),dbm.divide(BigDecimal.valueOf(10),mContext),mContext)).divide(BigDecimal.valueOf(1000),mContext);
			// return (Math.pow(10,dbm/10))/1000;
		
		} else if (arg.endsWith("dbW")) {
			int l = arg.length();
			String s = arg.substring(0,l-3);
			BigDecimal dbw = new BigDecimal(s);
			return BigDecimalMath.pow(BigDecimal.valueOf(10),dbw.divide(BigDecimal.valueOf(10),mContext),mContext);
			// return (Math.pow(10,dbw/10));
		}
		Log.e("Power wattValue",arg);
		return new BigDecimal(arg);
		// return Float.parseFloat(arg);
	}
	

	private void addDb(BigDecimal db) {
		BigDecimal dbW = BigDecimal.valueOf(10).multiply(BigDecimalMath.log10(this.watt,mContext));
		// double dbW = 10 * (Math.log10(this.watt));
		dbW = dbW.add(db);
		// dbW+=db;
		this.watt = BigDecimalMath.pow(BigDecimal.valueOf(10),dbW.divide(BigDecimal.valueOf(10),mContext),mContext);
		// this.watt = Math.pow(10,dbW/10);
		}
	
	private void subDb(BigDecimal db) {
		BigDecimal dbW = BigDecimal.valueOf(10).multiply(BigDecimalMath.log10(this.watt,mContext));
		// double dbW = 10 * (Math.log10(this.watt));
		dbW=dbW.subtract(db);
		// this.watt = Math.pow(10,dbW/10);
		this.watt = BigDecimalMath.pow(BigDecimal.valueOf(10),dbW.divide(BigDecimal.valueOf(10),mContext),mContext);
		}
	
	private void addDbm(BigDecimal dbm) {
		BigDecimal aWatt = (BigDecimalMath.pow(BigDecimal.valueOf(10),dbm.divide(BigDecimal.valueOf(10),mContext),mContext)).divide(BigDecimal.valueOf(1000),mContext);
		//double aWatt =  (Math.pow(10,dbm/10))/1000.0;
		
		this.watt = this.watt.add(aWatt);
		}
	
	private void subDbm(BigDecimal dbm) {
		BigDecimal aWatt = (BigDecimalMath.pow(BigDecimal.valueOf(10),dbm.divide(BigDecimal.valueOf(10),mContext),mContext)).divide(BigDecimal.valueOf(1000),mContext);
	// double aWatt =  (Math.pow(10,dbm/10))/1000.0;
		
		this.watt = this.watt.subtract(aWatt);
	}
	
	private void addDbW(BigDecimal dbw) {
		BigDecimal aWatt = (BigDecimalMath.pow(BigDecimal.valueOf(10),dbw.divide(BigDecimal.valueOf(10),mContext),mContext));
		// double aWatt =  (Math.pow(10,dbw/10));
		
		this.watt = this.watt.add(aWatt);
	}
	
	private void subDbW(BigDecimal dbw) {
		BigDecimal aWatt = (BigDecimalMath.pow(BigDecimal.valueOf(10),dbw.divide(BigDecimal.valueOf(10),mContext),mContext));
		// double aWatt =  (Math.pow(10,dbw/10));
		
		this.watt = this.watt.subtract(aWatt);
		}
	
	
	private void addWatt(BigDecimal W) {
		this.watt = this.watt.add(W);

	}
	
	private void subWatt(BigDecimal W) {

		this.watt = this.watt.subtract(W);
	}
	
	public double dbW() {
		BigDecimal dbW = BigDecimal.valueOf(10).multiply(BigDecimalMath.log10(this.watt,mContext));
		return dbW.floatValue();
		// return 10 * (Math.log10(this.watt));

	}
	
	public double dbm() {
		BigDecimal dbm = BigDecimal.valueOf(10).multiply(BigDecimalMath.log10(this.watt.divide(BigDecimal.valueOf(0.001),mContext),mContext));
	    return dbm.floatValue();
		// return 10 * (Math.log10(this.watt/.001));

	}
	
	public double watt() {

		watt = watt.round(mContext);
		return watt.floatValue();
	}
	
		

}
