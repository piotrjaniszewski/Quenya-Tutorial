package pl.piotrjaniszewski.quenyatutorial.gui.views;

import javafx.application.Application;
import pl.piotrjaniszewski.quenyatutorial.gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel{

    private JLabel titleLabel;
    private CustomButton lessonsButton;
    private CustomButton dictionaryButton;
    private CustomButton fiszkiButton;
    private CustomButton infoButton;
    private CustomButton exitButton;

    public MainMenuView() {
        super(new BorderLayout());

        createComponents();
        layoutComponents();
    }

    private void createComponents() {

        lessonsButton = createMenuButton(new LessonsView(),"Lekcje");
        dictionaryButton = createMenuButton(new DictionaryView(),"Słownik");
        fiszkiButton = createMenuButton(new FiszkiView(),"Fiszki");
        infoButton = createMenuButton(new InfoView(),"O programie");
        exitButton = createExitButton();
        titleLabel = createTitleLabel();

        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dictionaryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        fiszkiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.PAGE_AXIS));

        jPanel.add(Box.createVerticalGlue());
        jPanel.add(titleLabel);
        jPanel.add(lessonsButton);
        jPanel.add(dictionaryButton);
        jPanel.add(fiszkiButton);
        jPanel.add(infoButton);
        jPanel.add(exitButton);

        jPanel.add(Box.createVerticalGlue());

        add(jPanel,BorderLayout.CENTER);
    }
    private JLabel createTitleLabel(){
        JLabel jLabel = new JLabel("Quenya Tutorial",SwingConstants.CENTER);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel.setFont(new Font(jLabel.getFont().getName(),Font.PLAIN,jLabel.getFont().getSize()*4));
        jLabel.setBorder(BorderFactory.createEmptyBorder(0,60,60,60));
        return jLabel;
    }

    private CustomButton createMenuButton(final JPanel panel,String nazwa){
        CustomButton customButton = new CustomButton(nazwa);
        customButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customButton.setPreferredSize(new Dimension(300,60));
        customButton.setMaximumSize(new Dimension(300,60));
        customButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame =(JFrame) JFrame.getFrames()[0];
                frame.setContentPane(panel);
                frame.setVisible(true);
            }
        });
        return customButton;
    }

    private CustomButton createExitButton(){
        CustomButton customButton = new CustomButton("Wyjście");
        customButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customButton.setPreferredSize(new Dimension(300,60));
        customButton.setMaximumSize(new Dimension(300,60));
        customButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame =(JFrame) JFrame.getFrames()[0];
                frame.dispose();
            }
        });
        return customButton;
    }
}
