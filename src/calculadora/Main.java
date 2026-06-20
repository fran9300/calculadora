package calculadora;

import calculadora.controller.CalculatorController;
import calculadora.model.CalculatorModel;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorController(new CalculatorModel());
        });
    }
}