package kh.adventofcode.day3;

import java.util.List;

import kh.adventofcode.utils.FileReaderUtils;


public class Diagnostics {

	public static void main(String[] args) throws Exception {
		Diagnostics diags = new Diagnostics();
		FileReaderUtils utils = new FileReaderUtils();
		List<String> values = utils.readStringsFromInputFile("/day3-puzzleinput.txt");
		
		int result = diags.calculateGammaRate(values);
		System.out.println("Gamma rate: " + result);

		result = diags.calculateEpsilonRate(values);
		System.out.println("Epsilon rate: " + result);

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
	int calculateGammaRate(List<String> values) {
		int result = 0;
		int numberOfDiagValues = values.size();
		
		//read length of string - this is how many binary digits in each diag String
		int diagValueLength = values.get(0).length();
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
		
		//in each bit position, do we have more 1s or 0s?
		for(int i = 0; i < diagValueLength; i++ ) {
			if(bitCountsPerPosition[i] > numberOfDiagValues - bitCountsPerPosition[i]) {
				System.out.println("bit " + i + " has more 1s than 0s (" 
						+ bitCountsPerPosition[i] + ")");
				//TODO - store this result
			}
			else {
				System.out.println("bit " + i + " has more 0s than 1s (" 
						+ (numberOfDiagValues - bitCountsPerPosition[i]) + ")");
				//TODO store this result
			}
		}
		
		return result;
	}

	int calculateEpsilonRate(List<String> values) {
		int result = 0;
		
		return result;
	}

}
