package pl.piotrjaniszewski.quenyatutorial.gui.components;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AnswerTextField extends JTextField {

    public AnswerTextField() {
        super();
        addListeners();
    }

    private void addListeners() {
        addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                setForeground(Color.black);
            }
            public void focusLost(FocusEvent e) {
                setForeground(Color.black);
            }
        });
    }

    public void setCorrect(boolean check) {
        if(check){
            setForeground(Color.green);
        }else{
            setForeground(Color.red);
        }
    }
    public void resetCorrect(){
        setForeground(Color.black);
    }
}
