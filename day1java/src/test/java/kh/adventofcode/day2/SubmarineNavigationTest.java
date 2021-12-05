package kh.adventofcode.day2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubmarineNavigationTest {

	@Test
	public void testCalculatePosition() throws Exception{
		SubmarineNavigation nav = new SubmarineNavigation();
		int result = nav.calculatePosition("/day2-test.txt");
		assertEquals(150, result);
	}

	@Test
	public void testCalculatePositionWithAim() throws Exception{
		SubmarineNavigation nav = new SubmarineNavigation();
		int result = nav.calculatePositionWithAim("/day2-test.txt");
		assertEquals(900, result);
	}

}
