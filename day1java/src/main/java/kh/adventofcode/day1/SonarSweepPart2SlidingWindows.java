package kh.adventofcode.day1;

import java.io.IOException;
import java.util.List;

import kh.adventofcode.utils.FileReaderUtils;

/**
 * Advent of code day 1: Sonar Sweep part 2
 * 
 * Changed approach to just work with a simple array.
 * 
 * @author kevinhooke
 *
 */
public class SonarSweepPart2SlidingWindows {

	public static void main(String[] args) throws Exception {
		SonarSweepPart2SlidingWindows sweep = new SonarSweepPart2SlidingWindows();
		FileReaderUtils utils = new FileReaderUtils();
		
		List<Integer> measurements = utils.readIntsFromInputFile("/day1-puzzleinput.txt");
		
		long result = sweep.countDepthMeasurementsInSlidingWindow(3, measurements);
		System.out.println("Measurement increases: " + result);
	}

	/**
	 * Compares and counts measurements in sliding window of 3, where sum of
	 * 3 values in next sliding window is greater than sum in previous window.
	 * @return
	 * @throws IOException
	 */
	long countDepthMeasurementsInSlidingWindow(int windowSize, List<Integer> measurements) throws IOException {
		
		int increasedCount = 0;
		
		for(int windowStartIndex = 0; windowStartIndex < measurements.size(); windowStartIndex++) {
			int currentWindowSum = 0;
			//iterate for window and get sum of window
			for(int windowIndex = windowStartIndex; windowIndex < windowStartIndex + windowSize; windowIndex++) {
				currentWindowSum += measurements.get(windowIndex);
			}
			
			System.out.println("First window sum: " + currentWindowSum);
			
			//get sum for next window if there are 3 more values
			int nextWindowSum = 0;
			int nextWindowStartIndex = windowStartIndex + 1;
			//only count the next window if there are at least 3 values remaining
			if( measurements.size() - nextWindowStartIndex >= windowSize) {
				System.out.println("Second window has at least 3 values remaining");
				for(int windowIndex = nextWindowStartIndex; windowIndex < windowStartIndex + windowSize + 1; windowIndex++) {
					nextWindowSum += measurements.get(windowIndex);
				}		
				System.out.println("Second window sum: " + nextWindowSum);

			}
			else {
				break;
			}
			
			//compare the two windows
			if(nextWindowSum > currentWindowSum) {
				increasedCount++;
			}
			
		}
		
		
		
		
		return increasedCount;
	}



}
