package calculadora.model;

public final class Operations {

	private Operations() {
   /* This utility class should not be instantiated */
 }

	public static double addition(double num1, double num2) {
		return (num1 + num2);
	}
	
	public static double subtraction(double num1, double num2) {
		return (num1 - num2);
	}
	
	public static double multiplication(double num1, double num2) {
		return (num1 * num2);
	}
	
	public static double division(double num1, double num2) {
		if (num2 == 0) {
			throw new IllegalArgumentException();
		}
		return (num1 / num2);
	}
}
