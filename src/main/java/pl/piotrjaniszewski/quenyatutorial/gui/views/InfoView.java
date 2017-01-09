package pl.piotrjaniszewski.quenyatutorial.gui.views;

import pl.piotrjaniszewski.quenyatutorial.gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class InfoView extends JPanel{
    public InfoView() {
        super(new BorderLayout());
        createCompontents();
    }

    private void createCompontents() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.PAGE_AXIS));
        JTextPane jTextPane = new JTextPane();
        jTextPane.setMaximumSize(new Dimension(600,500));
        jTextPane.setEditable(false);
        jTextPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        jTextPane.setBackground(new Color(238,238,238));
        jTextPane.setText("Program został stworzony na podstawie kursu napisanego przez Helge Kåre Fauskanger" +
                "\n" +
                "\nOryginalny kurs w języku angielskim: http://folk.uib.no/hnohf/qcourse.htm" +
                "\nKurs przetłumaczony na język polski: http://home.agh.edu.pl/~evermind/Lingwistyka/quenya.htm");


        CustomButton customButton= new CustomButton("Menu główne");
        customButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame =(JFrame) JFrame.getFrames()[0];
                frame.setContentPane(new MainMenuView());
                frame.setVisible(true);
            }
        });
        customButton.setAllSizes(new Dimension(80,20));
        customButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        jPanel.add(Box.createVerticalGlue());
        jPanel.add(jTextPane);
        jPanel.add(customButton);
        jPanel.add(Box.createVerticalGlue());

        add(jPanel,BorderLayout.CENTER);
    }

}
