package model;

public class NoMagicSquareException extends Exception {

	public String toString() {
		return "The given data cannot create a MagicSquare!";
	}
}
