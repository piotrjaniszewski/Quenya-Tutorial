package pl.piotrjaniszewski.quenyatutorial.gui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InfoView extends JPanel{
    public InfoView() {
        super(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Informacje"));
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