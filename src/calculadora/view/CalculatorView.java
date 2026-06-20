package calculadora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.WindowConstants;
import javax.swing.SwingConstants;

public class CalculatorView {

    private static final String FONT_NAME = "Arial";

    private final JTextField display;

    public CalculatorView(ActionListener controller){

        JFrame frame = new JFrame("Calculator");


        frame.setDefaultCloseOperation(
                WindowConstants.EXIT_ON_CLOSE
        );


        frame.setLayout(new BorderLayout());



        display = new JTextField("0");


        display.setFont(
                new Font(FONT_NAME, Font.BOLD, 48)
        );


        display.setHorizontalAlignment(
                SwingConstants.RIGHT
        );


        display.setEditable(false);

        display.setBackground(
                new Color(255,255,222)
        );


        frame.add(
                display,
                BorderLayout.NORTH
        );



        JPanel buttonPanel = new JPanel(
                new GridLayout(5,4,5,5)
        );



        String[] buttons = {

                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+",
                "C","","",""
        };



        for(String text : buttons){


            JButton button =
                    new JButton(text);

            if(!text.isEmpty()){

                button.addActionListener(controller);

            }else{

                button.setEnabled(false);
            }

            configureButtonStyle(button, text);

            button.setFont(
                    new Font(FONT_NAME,Font.BOLD,18)
            );



            buttonPanel.add(button);
        }



        frame.add(
                buttonPanel,
                BorderLayout.CENTER
        );



        frame.setSize(400,500);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

    public void setDisplayText(String text){

        display.setText(text);
    }

    public String getDisplayText(){

        return display.getText();
    }

    private void configureButtonStyle(JButton button, String text) {


        button.setFont(
                new Font(FONT_NAME, Font.BOLD, 18)
        );


        if (text.matches("[\\d.]")) {

            button.setBackground(
                    new Color(240, 240, 222)
            );


        } else if (text.matches("[+\\-*/]")) {


            button.setBackground(
                    new Color(255, 153, 51)
            );

            button.setForeground(Color.WHITE);



        } else if (text.equals("=")) {


            button.setBackground(
                    new Color(102,153,255)
            );

            button.setForeground(Color.WHITE);



        } else if (text.equals("C")) {


            button.setBackground(
                    new Color(255,51,51)
            );

            button.setForeground(Color.WHITE);

        }

    }

}