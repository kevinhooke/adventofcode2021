package kh.adventofcode.day2;

import java.util.List;

import kh.adventofcode.utils.FileReaderUtils;

public class SubmarineNavigation {

	
	public static void main(String[] args) throws Exception{
		SubmarineNavigation nav = new SubmarineNavigation();
		
		int result = nav.calculatePosition("/day2-puzzleinput.txt");
		System.out.println("Result: " + result);

		result = nav.calculatePositionWithAim("/day2-puzzleinput.txt");
		System.out.println("Result with aim: " + result);

		
	}

	public int calculatePosition(String inputFile) throws Exception {
		int result = 0;
		int horizontalPos = 0;
		int verticalPos = 0;
		FileReaderUtils utils = new FileReaderUtils();
		List<String> movements = utils.readStringsFromInputFile(inputFile);
		
		for(String movement : movements){
			DirectionChange change = this.parseDirectionChange(movement);
			
			if(change.getDirection() == Direction.forward) {
				horizontalPos += change.getUnit();
			}
			else if(change.getDirection() == Direction.down) {
				verticalPos += change.getUnit();
			}
			else if(change.getDirection() == Direction.up) {
				verticalPos -= change.getUnit();
			}
		}
		
		
		return horizontalPos * verticalPos;
	}

	public int calculatePositionWithAim(String inputFile) throws Exception {
		int result = 0;
		int horizontalPos = 0;
		int verticalPos = 0;
		int aim = 0;
		FileReaderUtils utils = new FileReaderUtils();
		List<String> movements = utils.readStringsFromInputFile(inputFile);
		
		for(String movement : movements){
			DirectionChange change = this.parseDirectionChange(movement);
			
			if(change.getDirection() == Direction.forward) {
				horizontalPos += change.getUnit();
				verticalPos += (aim * change.getUnit());
			}
			else if(change.getDirection() == Direction.down) {
				aim += change.getUnit();
			}
			else if(change.getDirection() == Direction.up) {
				aim -= change.getUnit();
			}
		}
		
		
		return horizontalPos * verticalPos;
	}

	
	private DirectionChange parseDirectionChange(String movement) {

		String[] parts = movement.split("\\s");
		DirectionChange change = new DirectionChange(parts[0], parts[1]);
		
		return change;
	}
}
