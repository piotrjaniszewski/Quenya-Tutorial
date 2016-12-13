package pl.piotrjaniszewski.quenyatutorial.gui.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton{
    //TODO poprawic wyglad
    public CustomButton(String text) {
        super(text);
        //setBackground(Color.WHITE);
        // setBorder(null);
        setBorder(BorderFactory.createLineBorder(Color.black,1));
        setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e){
                setBackground(Color.LIGHT_GRAY);
            }
        });
        setFocusPainted(false);
    }

    public void setAllSizes(Dimension dimension){
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }
}
