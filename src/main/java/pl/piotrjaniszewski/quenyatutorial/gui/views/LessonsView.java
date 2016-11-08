package pl.piotrjaniszewski.quenyatutorial.gui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonsView extends JPanel {

    public LessonsView() {
        super(new BorderLayout());
        empty();
    }

    private void empty(){
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Lekcje"));
        JButton jButton = new JButton("Menu główne");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame =(JFrame) JFrame.getFrames()[0];
                frame.setContentPane(new MainMenuView());
                frame.setVisible(true);
            }
        });
        jPanel.add(jButton);
        add(jPanel);
    }

}
