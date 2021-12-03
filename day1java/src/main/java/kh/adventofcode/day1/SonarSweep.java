package kh.adventofcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Advent of code day 1: Sonar Sweep
 * 
 * @author kevinhooke
 *
 */
public class SonarSweep {

	public static void main(String[] args) throws Exception {
		SonarSweep sweep = new SonarSweep();
		long result = sweep.countDepthMeasurements();
		System.out.println("Measurement increases: " + result);
	}

	/**
	 * Compares and counts measurements where the current measurement in the list is >
	 * than the previous measurement.
	 * @return
	 * @throws IOException
	 */
	private long countDepthMeasurements() throws IOException {
		List<SonarMeaurement> measurements = this.readInputFile();
		long result = measurements.stream()
				.filter( reading -> 
					reading.getPreviousReading() != null 
						&& reading.getReading() > reading.getPreviousReading().getReading() ).count();
		return result;
	}

	/**
	 * Reads input file and builds a list of SonarMeasurements where each measurement
	 * has a reference to the previous measurement for comparison.
	 * 
	 * @return list of measurements
	 * @throws IOException
	 */
	private List<SonarMeaurement> readInputFile() throws IOException {
		List<SonarMeaurement> result = new ArrayList<>();

		InputStreamReader file = null;
		BufferedReader bufReader = null;
		try {
			file = new InputStreamReader(this.getClass().getResourceAsStream("/day1-puzzleinput.txt"));

			bufReader = new BufferedReader(file);
			String line = null;
			SonarMeaurement previous = null;
			
			while ((line = bufReader.readLine()) != null) {
				SonarMeaurement currentReading = new SonarMeaurement();
				currentReading.setReading(line != null ? Integer.parseInt(line) : 0);
				if(previous != null) {
					currentReading.setPreviousReading(previous);
				}
				previous = currentReading;
				result.add(currentReading);
				
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		} finally {
			bufReader.close();
			file.close();
		}

		return result;
	}

}
