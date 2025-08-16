package calculadora.controlador;

import calculadora.modelo.CalculadoraModelo;
import calculadora.vista.CalculadoraVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraControlador implements ActionListener {

    private CalculadoraVista vista;
    private CalculadoraModelo modelo;


    public CalculadoraControlador(CalculadoraModelo modelo) {
        this.modelo = modelo;
        this.vista = new CalculadoraVista(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        String displayActual = vista.getDisplayText();

        if (comando.equals("C")) {
            modelo.resetear();
            vista.setDisplayText("0");
        } else if (comando.matches("[0-9]")) {
            String nuevoTexto = modelo.manejarNumero(comando, displayActual);
            vista.setDisplayText(nuevoTexto);
        } else if (comando.equals(".")) {
            String nuevoTexto = modelo.manejarDecimal(displayActual);
            vista.setDisplayText(nuevoTexto);
        } else if (comando.equals("=")) {
            String resultado = modelo.manejarIgual(displayActual);
            vista.setDisplayText(resultado);
        } else {
        	String nuevoTexto = modelo.manejarOperador(comando, displayActual);
            vista.setDisplayText(nuevoTexto);
        }
    }
}
