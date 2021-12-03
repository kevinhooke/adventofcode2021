package kh.adventofcode.day1;

public class SonarMeaurement {

	private SonarMeaurement previousReading;
	private int reading;
	
	public SonarMeaurement getPreviousReading() {
		return previousReading;
	}
	public void setPreviousReading(SonarMeaurement previousReading) {
		this.previousReading = previousReading;
	}
	public int getReading() {
		return reading;
	}
	public void setReading(int reading) {
		this.reading = reading;
	}

	
}
