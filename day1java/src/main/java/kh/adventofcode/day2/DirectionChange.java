package kh.adventofcode.day2;

public class DirectionChange {

	private Direction direction;
	private int unit;

	public DirectionChange(String direction, String unit) {
		this.direction = Direction.valueOf(direction.toLowerCase());
		this.unit = Integer.parseInt(unit);
	}
	
	public DirectionChange(Direction direction, int unit) {
		this.direction = direction;
		this.unit = unit;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	
}
