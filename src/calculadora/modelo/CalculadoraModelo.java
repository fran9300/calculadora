package calculadora.modelo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public class CalculadoraModelo {

	    private double acumuladorResultado;
	    private double ultimoOperando;
	    private boolean esInicioDeNuevaEntrada;
	    private String operadorPendiente;
	    private String TEXTO_ERROR = "Error";
	    private Map<String, BiFunction<Double, Double, Double>> operaciones;
	    private static final DecimalFormat FORMATO_DECIMAL;
	    
	    static {
	        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	        symbols.setDecimalSeparator('.');
	        FORMATO_DECIMAL = new DecimalFormat("#.######", symbols);
	    }

	    public CalculadoraModelo() {
	        this.operaciones = new HashMap<>();
	        operaciones.put("+", Operaciones::suma);
	        operaciones.put("-", Operaciones::resta);
	        operaciones.put("*", Operaciones::multiplicacion);
	        operaciones.put("/", Operaciones::division);
	        resetear();
	    }

	    public void resetear() {
	        acumuladorResultado = 0;
	        ultimoOperando = 0;
	        operadorPendiente = "";
	        esInicioDeNuevaEntrada = true;
	    }

	    public String manejarNumero(String digito, String displayActual) {
	        if (esInicioDeNuevaEntrada || displayActual.equals("0") || displayActual.equals(TEXTO_ERROR)) {
	            esInicioDeNuevaEntrada = false;
	            return digito;
	        } else {
	            return displayActual + digito;
	        }
	    }

	    public String manejarDecimal(String displayActual) {
	        if (esInicioDeNuevaEntrada || displayActual.equals(TEXTO_ERROR)) {
	            esInicioDeNuevaEntrada = false;
	            return "0.";
	        } else if (!displayActual.contains(".")) {
	            return displayActual + ".";
	        }
	        return displayActual;
	    }

	    @SuppressWarnings("unused")
	    public String manejarOperador(String operador, String displayActual) {
	        
	        if (displayActual.isEmpty() || displayActual.equals(TEXTO_ERROR)) {
	            operadorPendiente = operador;
	            return "";
	        }

	        try {
	        	double numeroActual = Double.parseDouble(displayActual);

	        	if (!operadorPendiente.isEmpty() && !esInicioDeNuevaEntrada) { 
	        	    acumuladorResultado = operaciones.getOrDefault(operadorPendiente, (a, b) -> b).apply(acumuladorResultado, numeroActual);
	        	} else {
	        	    acumuladorResultado = numeroActual;
	        	}

	        	operadorPendiente = operador;
	        	esInicioDeNuevaEntrada = true;
	        	return FORMATO_DECIMAL.format(acumuladorResultado);
	            /*
	            if (acumuladorResultado == (long) acumuladorResultado) {
	                return String.valueOf((long) acumuladorResultado);
	            } else {
	                return String.valueOf(acumuladorResultado);
	            }*/

	        } catch (NumberFormatException ex) {
	            return "Error";
	        } catch (ArithmeticException | IllegalArgumentException ex) {
	            resetear();
	            return TEXTO_ERROR;
	        }
	    }

	    @SuppressWarnings("unused")
		public String manejarIgual(String displayActual) {
	        if (operadorPendiente.isEmpty()) {
	            return displayActual;
	        }

	        double segundoNumero;

	        if (!esInicioDeNuevaEntrada) {
	            try {
	                segundoNumero = Double.parseDouble(displayActual);
	            } catch (NumberFormatException ex) {
	                resetear();
	                return TEXTO_ERROR;
	            }
	            ultimoOperando = segundoNumero;
	        } else {
	  
	            if (ultimoOperando == 0) { 
	                 segundoNumero = acumuladorResultado;
	                 ultimoOperando = segundoNumero;
	            } else {
	                segundoNumero = ultimoOperando;
	            }
	        }

	        try {
	            acumuladorResultado = operaciones.getOrDefault(operadorPendiente, (a, b) -> b)
	                    .apply(acumuladorResultado, segundoNumero);

	            esInicioDeNuevaEntrada = true;
	            return FORMATO_DECIMAL.format(acumuladorResultado);
	            
	            /*
	            if (acumuladorResultado == (long) acumuladorResultado) {
	                return String.valueOf((long) acumuladorResultado);
	            } else {
	                return String.valueOf(acumuladorResultado);
	            }*/
	        } catch (ArithmeticException | IllegalArgumentException ex) {
	            resetear();
	            return TEXTO_ERROR;
	        }
	    }
}

