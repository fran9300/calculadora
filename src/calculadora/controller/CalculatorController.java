package calculadora.controller;

import calculadora.model.CalculatorModel;
import calculadora.view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController implements ActionListener {

    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model) {
        this.model = model;
        this.view = new CalculatorView(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        String currentDisplay = view.getDisplayText();

        switch (command) {

            case "C":
                model.reset();
                view.setDisplayText("0");
                break;


            case ".":
                view.setDisplayText(
                        model.handleDecimal(currentDisplay)
                );
                break;


            case "=":
                view.setDisplayText(
                        model.handleEquals(currentDisplay)
                );
                break;


            default:

                if(command.matches("\\d")) {

                    view.setDisplayText(
                            model.handleNumber(command, currentDisplay)
                    );

                } else {

                    view.setDisplayText(
                            model.handleOperator(command, currentDisplay)
                    );
                }
        }
    }
}