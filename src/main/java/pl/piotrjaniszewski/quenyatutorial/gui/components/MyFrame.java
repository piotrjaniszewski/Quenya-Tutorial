package pl.piotrjaniszewski.quenyatutorial.gui.components;

import pl.piotrjaniszewski.quenyatutorial.gui.views.MainMenu;
import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{
    public MyFrame(String title) throws HeadlessException {
        super(title);
        createFrame();
    }

    private void createFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(new MainMenu());
        pack();
        setVisible(true);
    }
}
