package pl.piotrjaniszewski.quenyatutorial.gui.components;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AnswerTextField extends JTextField {
    private String correctAnswer;

    public AnswerTextField(String correctAnswer) {
        super();
        this.correctAnswer=correctAnswer;
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

    public boolean check(){
        if (this.getText().contentEquals(correctAnswer)){
            setForeground(Color.GREEN);
            return true;
        }
        else{
            setForeground(Color.RED);
            return false;
        }
    }
}
