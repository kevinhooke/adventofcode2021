package kh.adventofcode.day1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class countDepthMeasurementsInSlidingWindowTest {

	@Test
	public void test3values() throws Exception{

		List<Integer> values = Arrays.asList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
		
		SonarSweepPart2SlidingWindows sweep = new SonarSweepPart2SlidingWindows();
		long result = sweep.countDepthMeasurementsInSlidingWindow(3, values);
		assertEquals(0, result);
	}

	@Test
	public void test4values_1increase() throws Exception{

		List<Integer> values = Arrays.asList(
				Integer.valueOf(199),
				Integer.valueOf(200),
				Integer.valueOf(208),
				Integer.valueOf(210)
				);
		
		SonarSweepPart2SlidingWindows sweep = new SonarSweepPart2SlidingWindows();
		long result = sweep.countDepthMeasurementsInSlidingWindow(3, values);
		assertEquals(1, result);
	}

}
