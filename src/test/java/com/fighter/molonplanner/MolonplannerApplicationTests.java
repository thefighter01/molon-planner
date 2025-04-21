package com.fighter.molonplanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class MolonplannerApplicationTests {

	private Calculator underTest = new Calculator();

	@Test
	void contextLoads() {

		// given
		int numberOne = 10;
		int numberTwo = 30;

		// when
		int result = underTest.add(numberOne, numberTwo);

		// then
		assertThat(result).isEqualTo(40);
	}

	public class Calculator {
	    public int add(int a, int b) {
	        return a + b;
	    }
	}

}
