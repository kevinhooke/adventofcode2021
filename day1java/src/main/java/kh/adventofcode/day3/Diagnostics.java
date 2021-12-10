package kh.adventofcode.day3;

import java.util.List;
import java.util.stream.Collectors;

import kh.adventofcode.utils.FileReaderUtils;


public class Diagnostics {

	public static void main(String[] args) throws Exception {
		Diagnostics diags = new Diagnostics();
		DiagsResult result = diags.checkDiagnostics("/day3-puzzleinput.txt");
		System.out.println("Gamma rate: " + result.getGammaValue());
		System.out.println("Epsilon rate: " + result.getEpsilonValue());
		System.out.println("Calculated result: " + result.getCalculatedValue());
		System.out.println("O2 diag rating: " + result.getO2reading());
		System.out.println("CO2 diag rating: " + result.getCo2ScrubberRating());
		System.out.println("Life support rating: " + result.calculateLifeSupportRating());
	}

	DiagsResult checkDiagnostics(String filename) throws Exception{
		FileReaderUtils utils = new FileReaderUtils();
		List<String> values = utils.readStringsFromInputFile(filename);
		
		DiagsResult result = this.calculateRates(values);
		
		return result;
	}
	
	/**
	 * From puzzle description:
	 * 
	 * "Considering only the first bit of each number, there are five 0 bits and seven 1
	 * bits. Since the most common bit is 1, the first bit of the gamma rate is 1. 
	 * 
	 * The most common second bit of the numbers in the diagnostic report is 0, so
	 * the second bit of the gamma rate is 0.
	 * 
	 * The most common value of the third, fourth, and fifth bits are 1, 1, and 0,
	 * respectively, and so the final three bits of the gamma rate are 110. 
	 * So, the gamma rate is the binary number 10110, or 22 in decimal."
	 * 
	 * @return
	 */
	DiagsResult calculateRates(List<String> values) {
		DiagsResult result = new DiagsResult();;
		int numberOfDiagValues = values.size();
		
		//read length of string - this is how many binary digits in each diag String
		int diagValueLength = values.get(0).length();
		int[] bitCountsPerPosition = countCurrentDiagValues(values, diagValueLength);
		
		StringBuilder gammaBits = getGammaBits(numberOfDiagValues, diagValueLength, bitCountsPerPosition);
		
		System.out.println("Gamma string rep of diag bits: " + gammaBits.toString());
		result.setGammaStringRep(gammaBits.toString());
		result.setGammaValue(Integer.parseInt(gammaBits.toString(), 2));
		
		// "The epsilon rate is calculated in a similar way; rather than use the 
		// most common bit, the least common bit from each position is used."
		StringBuilder epsilonBits = this.getEpsilonBits(numberOfDiagValues, diagValueLength, bitCountsPerPosition);
		System.out.println("Epsilon string rep of diag bits: " + epsilonBits.toString());

		result.setEpsilonStringRep(epsilonBits.toString());
		result.setEpsilonValue(Integer.parseInt(epsilonBits.toString(), 2));
		
		
		List<String> o2ratingFilteredValue = this.findOxygenGeneratorRating(values, numberOfDiagValues, diagValueLength, 0);
		
		//check for 1 remaining value, this is the filtered o2 reading
		result.setO2reading(Integer.parseInt(o2ratingFilteredValue.get(0), 2));

		List<String> co2ScrubberRatingValues = this.findCO2ScrubberRating(values, numberOfDiagValues, diagValueLength, 0);
		
		//check for 1 remaining value, this is the filtered o2 reading
		result.setCo2ScrubberRating(Integer.parseInt(co2ScrubberRatingValues.get(0), 2));

		
		return result;
	}

	private int[] countCurrentDiagValues(List<String> values, int diagValueLength) {
		int[] bitCountsPerPosition = new int[diagValueLength];
		
		for(String value : values) {
			int intValue = Integer.parseInt(value, 2);
			int currentBit = diagValueLength-1;

			while(intValue != 0) {
				
				//check if current least significant bit is a 1 by AND'ing with 1
				if((intValue & 1) == 1) {
					//current bit is a 1, increment the count for this bit position
					bitCountsPerPosition[currentBit]++;
				}
				
				//shift bits right ready to check next least significant bit
				intValue = intValue >> 1;
				
				//keep track of what bit we're testing
				currentBit--;
			}
			
		}
		return bitCountsPerPosition;
	}

	/**
	 * O2 requirement:
	 * 
	 * "...filtering out values until only one remains. Before searching for either
	 * rating value, start with the full list of binary numbers from your diagnostic
	 * report and consider just the first bit of those numbers. Then:
	 *  
	 * Keep only numbers selected by the bit criteria for the type of
	 * rating value for which you are searching. Discard numbers which do not
	 * match the bit criteria
	 *  
	 * If you only have one number left, stop; this is the rating value for
	 * which you are searching.
	 * 
	 * Otherwise, repeat the process, considering the next bit to the right.
	 * 
	 * The bit criteria depends on which type of rating value you want to find:
	 * 
	 *  - To find oxygen generator rating, determine the most common value (0 or 1)
	 *  in the current bit position, and keep only numbers with that bit in that
	 *  position. If 0 and 1 are equally common, keep values with a 1 in the
	 *  position being considered."
	 *  
	 * @param numberOfDiagValues
	 * @param diagValueLength
	 * @param bitCountsPerPosition
	 * @return
	 */
	private List<String> findOxygenGeneratorRating(List<String> values, int numberOfDiagValues, int diagValueLength,
			int startingDiagBitPosition) {
		
		int[] bitCountsPerPosition = countCurrentDiagValues(values, diagValueLength);
		
		//in each bit position, do we have more 1s or 0s?
		List<String> matchingValues = null;
		
		int mostCommonBit = this.getMostCommonBitInCurrentBitPosition(bitCountsPerPosition, numberOfDiagValues, 
				startingDiagBitPosition, true);
		
		//keep only values with most common bit in current bit position
		final int currentDiagBitPosition = startingDiagBitPosition; 
		matchingValues = values.stream()
				.filter(value -> value.substring(currentDiagBitPosition, currentDiagBitPosition + 1)
						.equals(Integer.toBinaryString(mostCommonBit)))
				.collect(Collectors.toList());
		
		numberOfDiagValues = matchingValues.size(); 
		startingDiagBitPosition++;
		
		if(matchingValues.size() > 1) {
			matchingValues = this.findOxygenGeneratorRating(matchingValues, numberOfDiagValues, diagValueLength,
					startingDiagBitPosition);
		}

		return matchingValues;
	}

	
	int getMostCommonBitInCurrentBitPosition(int[] bitCountsPerPosition, int numberOfDiagValues, int bitPositionFromLeft,
			boolean o2ReadingEqualValuesCondition) {
		int result = 0;
		
		// For O2 reading: "If 0 and 1 are equally common, keep values with a 1 in the position being considered."
		if(o2ReadingEqualValuesCondition &&
				bitCountsPerPosition[bitPositionFromLeft] == numberOfDiagValues - bitCountsPerPosition[bitPositionFromLeft]) {
			result = 1;
		}
		else if(bitCountsPerPosition[bitPositionFromLeft] > numberOfDiagValues - bitCountsPerPosition[bitPositionFromLeft]) {
			//System.out.println("position " + i + " has more 1s than 0s (" 
			//		+ bitCountsPerPosition[i] + ")");
			result = 1;
		}
		else {
			//System.out.println("position " + i + " has more 0s than 1s (" 
			//		+ (numberOfDiagValues - bitCountsPerPosition[i]) + ")");
			result = 0;
		}
		return result;
	}

	
	private List<String> findCO2ScrubberRating(List<String> values, int numberOfDiagValues, int diagValueLength,
			int startingDiagBitPosition) {
		
		int[] bitCountsPerPosition = countCurrentDiagValues(values, diagValueLength);
		
		//in each bit position, find least common value, 1s or 0s
		List<String> matchingValues = null;
		
		int leastCommonBit = this.getLeastCommonBitInCurrentBitPosition(bitCountsPerPosition, numberOfDiagValues, 
				startingDiagBitPosition, true);
		
		//keep only values with least common bit in current bit position
		final int currentDiagBitPosition = startingDiagBitPosition; 
		matchingValues = values.stream()
				.filter(value -> value.substring(currentDiagBitPosition, currentDiagBitPosition + 1)
						.equals(Integer.toBinaryString(leastCommonBit)))
				.collect(Collectors.toList());
		
		numberOfDiagValues = matchingValues.size(); 
		startingDiagBitPosition++;
		
		if(matchingValues.size() > 1) {
			matchingValues = this.findCO2ScrubberRating(matchingValues, numberOfDiagValues, diagValueLength,
					startingDiagBitPosition);
		}

		return matchingValues;
	}

	int getLeastCommonBitInCurrentBitPosition(int[] bitCountsPerPosition, int numberOfDiagValues, int bitPositionFromLeft,
			boolean co2ReadingEqualValuesCondition) {
		int result = 0;
		
		// For CO2 reading: "If 0 and 1 are equally common, keep values with a 0 in the position being considered."
		if(co2ReadingEqualValuesCondition &&
				bitCountsPerPosition[bitPositionFromLeft] == numberOfDiagValues - bitCountsPerPosition[bitPositionFromLeft]) {
			result = 0;
		}
		else if(bitCountsPerPosition[bitPositionFromLeft] > numberOfDiagValues - bitCountsPerPosition[bitPositionFromLeft]) {
			//System.out.println("position " + i + " has more 1s than 0s (" 
			//		+ bitCountsPerPosition[i] + ")");
			result = 0;
		}
		else {
			//System.out.println("position " + i + " has more 0s than 1s (" 
			//		+ (numberOfDiagValues - bitCountsPerPosition[i]) + ")");
			result = 1;
		}
		return result;
	}

	
	private StringBuilder getGammaBits(int numberOfDiagValues, int diagValueLength, int[] bitCountsPerPosition) {
		StringBuilder gammaBits = new StringBuilder(); 
		//in each bit position, do we have more 1s or 0s?
		for(int i = 0; i < diagValueLength; i++ ) {
			if(bitCountsPerPosition[i] > numberOfDiagValues - bitCountsPerPosition[i]) {
				//System.out.println("position " + i + " has more 1s than 0s (" 
				//		+ bitCountsPerPosition[i] + ")");
				gammaBits.append("1");
			}
			else {
				//System.out.println("position " + i + " has more 0s than 1s (" 
				//		+ (numberOfDiagValues - bitCountsPerPosition[i]) + ")");
				gammaBits.append("0");
			}
		}
		return gammaBits;
	}
	
	/**
	 * Reversed logic from getGammaBiys()
	 * 
	 * @param numberOfDiagValues
	 * @param diagValueLength
	 * @param bitCountsPerPosition
	 * @return
	 */
	StringBuilder getEpsilonBits(int numberOfDiagValues, int diagValueLength, int[] bitCountsPerPosition) {
		StringBuilder gammaBits = new StringBuilder(); 
		//in each bit position, do we have more 1s or 0s?
		for(int i = 0; i < diagValueLength; i++ ) {
			if(bitCountsPerPosition[i] > numberOfDiagValues - bitCountsPerPosition[i]) {
				System.out.println("position " + i + " has more 1s than 0s (" 
						+ bitCountsPerPosition[i] + ")");
				gammaBits.append("0");
			}
			else {
				System.out.println("position " + i + " has more 0s than 1s (" 
						+ (numberOfDiagValues - bitCountsPerPosition[i]) + ")");
				gammaBits.append("1");
			}
		}
		return gammaBits;
	}


}
