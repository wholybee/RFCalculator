package net.holybee.rfcalculator;

public class AtscCalc {
	
	double[] channelArray;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		AtscCalc calc = new AtscCalc();
		calc.createChannelArray();
		calc.print(2);
		calc.print(5);
		calc.print(19);

		
	}
	
	void print (int channel) {
	
		System.out.println("Channel:" + channel + "  Lower Edge:" + lowerEdge(channel) + "   Lower ATSC:" + lowerATSC(channel) + "   ATSC Pilot:" + pilotFreq (channel) + "   Center:" + centerFreq(channel) + "   Upper ATSC:" + upperATSC(channel) + "   Upper Edge:" + upperEdge(channel));
		System.out.println("Lower Shoulder: " + lowerShoulder(channel) + "Upper Shoulder: " +upperShoulder(channel));
	}
	
	
	public void createChannelArray () {
		channelArray = new double[100];
		
		for (int i=2;i<5;i++) {
			channelArray[i] = 42 + (i*6);
		}
		
		channelArray[5] = 76;
		channelArray[6] = 82;
		
		for (int i=7;i<14;i++) {
			channelArray[i] = 132 + (i*6);
		}
		
		for (int i=14; i < 84 ; i++ ) {
			channelArray[i] = 386 + (i*6);
		}
		
		
	}
	
	public double centerFreq (int channel) {
		return channelArray[channel]+3 ;
	}
	
	public double pilotFreq (int channel) {
		return channelArray[channel]+0.31;
	}
	
	public double lowerEdge (int channel) {
		return channelArray[channel];
	}
	
	public double upperEdge (int channel) {
		return channelArray[channel]+6;
	}
	
	public double lowerATSC (int channel) {
		return channelArray[channel]+0.31;
	}
	
	public double upperATSC (int channel) {
		return channelArray[channel]+5.69;
	}
	
	public double lowerShoulder (int channel) {
		return channelArray[channel]-0.25;
	}
	
	public double upperShoulder (int channel) {
		return channelArray[channel]+6.25;
	}
	
	
	
}
