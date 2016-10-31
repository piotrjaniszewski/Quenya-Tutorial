package pl.piotrjaniszewski.quenyatutorial.gui.components;


import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton{

    public CustomButton(String text) {
        super(text);
        //setBackground(Color.WHITE);
        // setBorder(null);
        setFocusPainted(false);
    }
}
