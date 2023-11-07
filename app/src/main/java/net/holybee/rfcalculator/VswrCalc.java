/**
 * 
 */
package net.holybee.rfcalculator;

import static java.lang.Math.sqrt;

/**
 * @author warren
 *
 */
class VswrCalc {
	private static VswrCalc instance = null;
	double VSWR;
	double returnLoss;
	double reflectionCo;
	double missmatch;

	/**
	 * 
	 */
	private VswrCalc() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized VswrCalc getInstance(){
		if (instance == null)
			instance = new VswrCalc();
		return instance;
	}

	// create vswr from return loss
	public void vswrCalcFromRL (double rl) {
		double y = -rl/20;
		reflectionCo = Math.pow(10,y);
		VSWR = (1+reflectionCo)/(1-reflectionCo);
		returnLoss = rl;
		
		
	}
	//create vswr from reflection coefficient 
	public void vswrCalcFromRC (double rc) {
		VSWR = (1+rc)/(1-rc);
		reflectionCo = rc;
		returnLoss = -(20 * (Math.log10(rc)));
	
	}
	
	// create vswr from vswr
	public void vswrCalcFromVswr (double vswr) {
		reflectionCo = (vswr-1)/(vswr+1);
		returnLoss = -(20 * (Math.log10(reflectionCo)));
		VSWR = vswr;
	}

	public void vswrCalcFromPower (double fwd, double rev) {
		VSWR =    (1 + sqrt(rev/fwd)) /  (1 - sqrt(rev/fwd));
		reflectionCo = (VSWR-1)/(VSWR+1);
		returnLoss = -(20 * (Math.log10(reflectionCo)));
	}



    public double getVSWR() {
        return VSWR;
    }

    public double getReturnLoss() {
        return returnLoss;
    }

    public double getReflectionCo() {
        return reflectionCo;

    }


}
