package calculadora;

import calculadora.controlador.CalculadoraControlador;
import calculadora.modelo.CalculadoraModelo;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraControlador(new CalculadoraModelo());
        });
    }
}