package org.assertj.examples.lotr.assertions;

import org.assertj.examples.lotr.TolkienCharacter;

public class MyProjectAssertions {

	// give access to TolkienCharacter assertion
	public static TolkienCharacterAssert assertThat(TolkienCharacter actual) {
		return new TolkienCharacterAssert(actual);
	}

}