package calculadora.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public class CalculatorModel {

	private double result;
	private double lastOperand;

	private boolean newInput;

	private String pendingOperator;

	private static final String ERROR_TEXT = "Error";

	private final Map<String, BiFunction<Double, Double, Double>> operations;

	private static final DecimalFormat FORMAT;

	static {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();

		symbols.setDecimalSeparator('.');

		FORMAT = new DecimalFormat("#.######", symbols);
	}

	public CalculatorModel() {


		operations = new HashMap<>();

		operations.put("+", Operations::addition);
		operations.put("-", Operations::subtraction);
		operations.put("*", Operations::multiplication);
		operations.put("/", Operations::division);


		reset();
	}

	public void reset(){

		result = 0;
		lastOperand = 0;

		pendingOperator = "";

		newInput = true;
	}

	public String handleNumber(String number, String display){


		if(newInput || display.equals("0") || display.equals(ERROR_TEXT)){


			newInput = false;

			return number;
		}


		return display + number;
	}

	public String handleDecimal(String display){


		if(newInput || display.equals(ERROR_TEXT)){


			newInput = false;

			return "0.";
		}


		if(!display.contains(".")){

			return display + ".";
		}


		return display;
	}

	public String handleOperator(String operator, String display){


		if(display.isEmpty() || display.equals(ERROR_TEXT)){

			pendingOperator = operator;

			return "";
		}



		try{

			double currentNumber =
					Double.parseDouble(display);


			if(!pendingOperator.isEmpty() && !newInput){


				result = operations
						.get(pendingOperator)
						.apply(result, currentNumber);

			}

			else{

				result = currentNumber;
			}


			pendingOperator = operator;

			newInput = true;


			return FORMAT.format(result);


		}catch(Exception e){

			reset();

			return ERROR_TEXT;
		}
	}

	public String handleEquals(String display){


		if(pendingOperator.isEmpty()){

			return display;
		}



		try{

			double currentNumber;


			if(!newInput){

				currentNumber =
						Double.parseDouble(display);


				lastOperand = currentNumber;

			}

			else{

				currentNumber = lastOperand;
			}


			result =
					operations
							.get(pendingOperator)
							.apply(result,currentNumber);


			newInput = true;


			return FORMAT.format(result);



		}catch(Exception e){


			reset();

			return ERROR_TEXT;
		}

	}

}