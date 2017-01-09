package pl.piotrjaniszewski.quenyatutorial.gui.components;


import com.sun.javafx.font.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton{
    private boolean selected=false;
    public CustomButton(String text) {
        super(text);
        setBorder(BorderFactory.createLineBorder(Color.black,1));
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!selected) {
                    setBackground(Color.GRAY);
                }
            }

            @Override
            public void mouseExited(MouseEvent e){
             if(!selected){
                 setBackground(Color.LIGHT_GRAY);
             }
            }
        });
        setFocusPainted(false);
    }

    public void setAllSizes(Dimension dimension){
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }
    public void setSelected(boolean selected){
        this.selected=selected;
        if(selected){
         setBackground(Color.DARK_GRAY);
        }
        else {
            setBackground(Color.LIGHT_GRAY);
        }
    }
}
