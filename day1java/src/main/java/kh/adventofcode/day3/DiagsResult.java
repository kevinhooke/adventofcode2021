package kh.adventofcode.day3;

public class DiagsResult {

	private String gammaStringRep;
	private String epsilonStringRep;
	private int gammaValue;
	private int epsilonValue;
	private int o2reading;
	
	public String getGammaStringRep() {
		return gammaStringRep;
	}
	public void setGammaStringRep(String gammaStringRep) {
		this.gammaStringRep = gammaStringRep;
	}
	public String getEpsilonStringRep() {
		return epsilonStringRep;
	}
	public void setEpsilonStringRep(String epsilonStringRep) {
		this.epsilonStringRep = epsilonStringRep;
	}
	public int getGammaValue() {
		return gammaValue;
	}
	public void setGammaValue(int gammaValue) {
		this.gammaValue = gammaValue;
	}
	public int getEpsilonValue() {
		return epsilonValue;
	}
	public void setEpsilonValue(int epsilonValue) {
		this.epsilonValue = epsilonValue;
	}
	
	public int getCalculatedValue() {
		return this.gammaValue * this.epsilonValue;
	}
	public int getO2reading() {
		return o2reading;
	}
	public void setO2reading(int o2reading) {
		this.o2reading = o2reading;
	}
}
