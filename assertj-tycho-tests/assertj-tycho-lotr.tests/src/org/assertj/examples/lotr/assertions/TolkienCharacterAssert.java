package org.assertj.examples.lotr.assertions;
import java.util.Objects;

import org.assertj.core.api.AbstractAssert;
import org.assertj.examples.lotr.TolkienCharacter;
public class TolkienCharacterAssert extends AbstractAssert<TolkienCharacterAssert, TolkienCharacter> {

  // 2 - Write a constructor to build your assertion class with the object you want make assertions on.
  public TolkienCharacterAssert(TolkienCharacter actual) {
    super(actual, TolkienCharacterAssert.class);
  }

  // 3 - A fluent entry point to your specific assertion class, use it with static import.
  public static TolkienCharacterAssert assertThat(TolkienCharacter actual) {
    return new TolkienCharacterAssert(actual);
  }

  // 4 - a specific assertion !
  public TolkienCharacterAssert hasName(String name) {
    // check that actual TolkienCharacter we want to make assertions on is not null.
    isNotNull();

    // check condition
    if (!Objects.equals(actual.getName(), name)) {
      failWithMessage("Expected character's name to be <%s> but was <%s>", name, actual.getName());
    }

    // return the current assertion for method chaining
    return this;
  }

  // 4 - another specific assertion !
  public TolkienCharacterAssert hasAge(int age) {
    // check that actual TolkienCharacter we want to make assertions on is not null.
    isNotNull();

    // check condition
    if (actual.getAge() != age) {
      failWithMessage("Expected character's age to be <%s> but was <%s>", age, actual.getAge());
    }

    // return the current assertion for method chaining
    return this;
  }
}