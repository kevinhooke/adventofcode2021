package kh.adventofcode.day3;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DiagnosticsTest {

	@Test
	public void test() {
		Diagnostics diags = new Diagnostics();;
		List<String> values = Arrays.asList("00100", "11110", "10110");
		diags.calculateGammaRate(values);
	}

}
