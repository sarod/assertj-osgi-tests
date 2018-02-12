package org.assertj.examples.lotr;

import org.assertj.examples.lotr.assertions.MyProjectSoftAssertions;
import org.junit.Test;


public class CustomSoftAssertionsTest {

	@Test
	public void customAssertionsShouldWorkInOsgiRuntime() {
		
		TolkienCharacter frodo = new TolkienCharacter("Frodo", 33);
		MyProjectSoftAssertions softly = new MyProjectSoftAssertions();

		softly.assertThat(frodo).hasName("Frodo");
		softly.assertThat(frodo).hasAge(33);


		softly.assertAll();
	}
}
