package calculadora.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraVista {
	
    private JFrame frame;
    private JTextField display;
	
	public CalculadoraVista(ActionListener controlador) {

        frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 48));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(255, 255, 222));
        
        frame.add(display, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4, 5, 5));

        String[] textoBotones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "", "", "" 
        };

        for (String texto : textoBotones) {
            JButton boton;
            if (texto.isEmpty()) {
                boton = new JButton();
                boton.setEnabled(false); 
            } else {
                boton = new JButton(texto);
                boton.addActionListener(controlador);
            }
            boton.setFont(new Font("Arial", Font.BOLD, 18));
            
            
            if (texto.matches("[0-9]|\\.")) {
                boton.setBackground(new Color(240, 240, 222)); 
            } else if (texto.matches("[+\\-*/]")) {
                boton.setBackground(new Color(255, 153, 51)); 
                boton.setForeground(Color.WHITE);
            } else if (texto.equals("=")) {
                boton.setBackground(new Color(102, 153, 255)); 
                boton.setForeground(Color.WHITE);
            } else if (texto.equals("C")) {
                boton.setBackground(new Color(255, 51, 51)); 
                boton.setForeground(Color.WHITE);
            }
            
            panelBotones.add(boton);
        }

        frame.add(panelBotones, BorderLayout.CENTER);

        frame.setSize(400, 500);
        Dimension minSize = new Dimension(250, 300);
        frame.setMinimumSize(minSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
	
    public void setDisplayText(String text) {
        display.setText(text);
    }

    public String getDisplayText() {
        return display.getText();
    }

}
