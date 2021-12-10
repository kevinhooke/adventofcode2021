package kh.adventofcode.day3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DiagnosticsTest {

	@Test
	public void testExampleValues() throws Exception {
		Diagnostics diags = new Diagnostics();;
		DiagsResult result = diags.checkDiagnostics("/day3-test.txt");
		assertEquals(22, result.getGammaValue());
		assertEquals(9, result.getEpsilonValue());
		assertEquals(198, result.getCalculatedValue());
		assertEquals(23, result.getO2reading());
	}

}
