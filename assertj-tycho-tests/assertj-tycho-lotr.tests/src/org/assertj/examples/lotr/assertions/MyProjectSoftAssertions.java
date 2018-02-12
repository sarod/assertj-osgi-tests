package org.assertj.examples.lotr.assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.examples.lotr.TolkienCharacter;

public class MyProjectSoftAssertions extends SoftAssertions {

  public TolkienCharacterAssert assertThat(TolkienCharacter actual) {
    return proxy(TolkienCharacterAssert.class, TolkienCharacter.class, actual);
  }
  
}