package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MagicSquareTest {
	MagicSquareTestClass square;
	void setupScenary1()  {
		String order="0";
		String stRow="0";
		String stCol="0";
		char dir=MagicSquareTestClass.NO;
		square=new MagicSquareTestClass(order,stRow,stCol,dir);
	}

	//First 3 tests are for checking generateMatrix(method)
	//tests 4-6 --> check validate()
	//test 7-9 --> check generateMagicNumber();
	@Test
	void testGenerateMatrix1() {
		setupScenary1();
		square.setOrder("5");
		square.setStartingRow("0");
		square.setStartingColumn("2");
		square.setDir(MagicSquareTestClass.SE);
		try {
			int[][] testSquare = square.generateMatrix();
			assertTrue(testSquare!=null, "Creates matrix successfully.");
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			fail("Expected matrix");
		} catch (NegativeArraySizeException e) {
			// TODO Auto-generated catch block
			fail("Expected matrix");
		} catch (PairException e) {
			// TODO Auto-generated catch block
			fail("Expected matrix");
		}
	}
	//fail PairException
	
	@Test
	void testGenerateMatrix2() {
		setupScenary1();
		square.setOrder("4");
		square.setStartingRow("0");
		square.setStartingColumn("2");
		square.setDir(MagicSquareTestClass.SE);
		PairException exception = assertThrows(PairException.class, ()->square.generateMatrix());
		assertEquals("The Magic Square has to be impair.",exception.toString());
	}
	@Test
	void testGenerateMatrix3() {
		setupScenary1();
		square.setOrder("-5");
		square.setStartingRow("0");
		square.setStartingColumn("2");
		square.setDir(MagicSquareTestClass.SE);
		NegativeArraySizeException exception = assertThrows(NegativeArraySizeException.class, ()->square.generateMatrix());
	}
	@Test
	void testGenerateMatrix4() {
		setupScenary1();
		square.setOrder("5");
		square.setStartingRow("6");
		square.setStartingColumn("2");
		square.setDir(MagicSquareTestClass.SE);
		ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, ()->square.generateMatrix());
	}
	@Test
	void testValidate1() {
		setupScenary1();
		square.setOrder("5");
		square.setStartingRow("0");
		square.setStartingColumn("2");
		square.setDir(MagicSquareTestClass.SE);
		try {
			int[][] testSquare = square.generateMatrix();
			assertTrue(testSquare!=null, "Creates matrix successfully.");
			try {
				square.validate(testSquare);
				assertEquals(square.validate(testSquare),"Magic Square is valid! Magic Number:65");
			} catch (ArithmeticException e) {
				// TODO Auto-generated catch block
				fail("Unknown Error");
				e.printStackTrace();
			} catch (NoMagicSquareException e) {
				// TODO Auto-generated catch block
				fail("Expected OK");
				e.printStackTrace();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			fail("Expected OK");
		} catch (NegativeArraySizeException e) {
			// TODO Auto-generated catch block
			fail("Expected OK");
		} catch (PairException e) {
			// TODO Auto-generated catch block
			fail("Expected OK");
		}
		
	}
	@Test
	void testValidate2() {
		setupScenary1();
		square.setOrder("5");
		square.setStartingRow("0");
		square.setStartingColumn("2");
		square.setDir(MagicSquareTestClass.NO);
		
		
		try {
			int[][] testSquare = square.generateMatrix();
			assertTrue(testSquare!=null, "Creates matrix successfully.");
			try {
				NoMagicSquareException ex = assertThrows(NoMagicSquareException.class,()->square.validate(testSquare));
			} catch (ArithmeticException e) {
				// TODO Auto-generated catch block
				fail("Unknown Error");
				e.printStackTrace();
			} 
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			fail("Expected NoMagicSquareException");
		} catch (NegativeArraySizeException e) {
			// TODO Auto-generated catch block
			fail("Expected NoMagicSquareException");
		} catch (PairException e) {
			// TODO Auto-generated catch block
			fail("Expected NoMagicSquareException");
		}
		
	}

}
