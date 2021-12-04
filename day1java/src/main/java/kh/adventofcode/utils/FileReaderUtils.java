package kh.adventofcode.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {

	/**
	 * Reads input file and builds List of integers.
	 * 
	 * @return list of values
	 * @throws IOException
	 */
	public List<Integer> readIntsFromInputFile(String inputFilename) throws IOException {
		List<Integer> result = new ArrayList<>(); 

		InputStreamReader file = null;
		BufferedReader bufReader = null;
		try {
			file = new InputStreamReader(this.getClass().getResourceAsStream(inputFilename));

			bufReader = new BufferedReader(file);
			String line = null;
			
			while ((line = bufReader.readLine()) != null) {
				int currentReading = Integer.parseInt(line);
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

	/**
	 * Reads input file and builds List of integers.
	 * 
	 * @return list of values
	 * @throws IOException
	 */
	public List<String> readStringsFromInputFile(String inputFilename) throws IOException {
		List<String> result = new ArrayList<>(); 

		InputStreamReader file = null;
		BufferedReader bufReader = null;
		try {
			file = new InputStreamReader(this.getClass().getResourceAsStream(inputFilename));

			bufReader = new BufferedReader(file);
			String line = null;
			
			while ((line = bufReader.readLine()) != null) {
				result.add(line);
				
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
