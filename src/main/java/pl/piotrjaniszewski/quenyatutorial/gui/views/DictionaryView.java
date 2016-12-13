package pl.piotrjaniszewski.quenyatutorial.gui.views;

import pl.piotrjaniszewski.quenyatutorial.dictionary.DatabaseDictionary;
import pl.piotrjaniszewski.quenyatutorial.dictionary.Dictionary;
import pl.piotrjaniszewski.quenyatutorial.gui.components.CustomButton;
import pl.piotrjaniszewski.quenyatutorial.gui.components.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;


public class DictionaryView extends JPanel {

    private Dictionary dictionary;
    private boolean toQuenya=true;

    private JLabel leftLabel;
    private JLabel rightLabel ;
    private JTextField questionField;
    private JTextField answerField;
    private JButton changeSiteButton;
    private JButton translateButton;

    private List<CustomButton> previousButtonsList = new LinkedList<CustomButton>();
    private JPanel previousPanel = new JPanel();
    public DictionaryView() {
        super(new BorderLayout());
        dictionary = new DatabaseDictionary();
        createComponents();
        layoutComponents();
        addReturnButton();
        createPreviousView();
        add(previousPanel,BorderLayout.LINE_START);
    }

    private void createComponents(){
        leftLabel = new JLabel("Polski");
        rightLabel = new JLabel("Quenya");

        questionField  = new JTextField();
        questionField.setPreferredSize(new Dimension(200,30));

        answerField = new JTextField();
        answerField.setPreferredSize(new Dimension(200,30));
        answerField.setEnabled(false);


        translateButton = new CustomButton("Tłumacz");
        translateButton.setPreferredSize(new Dimension(300,30));
        translateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toQuenya) {
                    answerField.setText(dictionary.translateToQuenya(questionField.getText()));
                } else {
                    answerField.setText(dictionary.translateToPolish(questionField.getText()));
                }

                CustomButton customButton = new CustomButton(questionField.getText());
                customButton.addActionListener(new ActionListener() {
                    private boolean toquenya = toQuenya;
                    private String wordToTranslate = questionField.getText();
                    private String translatedWord = answerField.getText();

                    public void actionPerformed(ActionEvent e) {
                        if (toquenya != toQuenya) {
                            String tmp = leftLabel.getText();
                            leftLabel.setText(rightLabel.getText());
                            rightLabel.setText(tmp);
                            toQuenya = toquenya;
                        }
                        questionField.setText(wordToTranslate);
                        answerField.setText(translatedWord);
                    }
                });
                previousButtonsList.add(customButton);
                customButton.setAllSizes(new Dimension(80, 20));

                createPreviousView();
            }
        });

        changeSiteButton = new CustomButton("⇄");
        changeSiteButton.setPreferredSize(new Dimension(20,20));
        changeSiteButton.setMargin(new Insets(0, 0, 0, 0));
        changeSiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tmp = leftLabel.getText();
                leftLabel.setText(rightLabel.getText());
                rightLabel.setText(tmp);
                toQuenya=!toQuenya;
                answerField.setText("");
            }
        });
    }

    private void addReturnButton(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.PAGE_AXIS));
        jPanel.add(Box.createVerticalGlue());
        CustomButton returnButton = new CustomButton("Menu główne");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame =(JFrame) JFrame.getFrames()[0];
                frame.setContentPane(new MainMenuView());
                frame.setVisible(true);
            }
        });
        returnButton.setAllSizes(new Dimension(80,20));
        jPanel.add(returnButton);
        add(jPanel,BorderLayout.LINE_END);
    }

    private void createPreviousView(){

        previousPanel.removeAll();
        previousPanel.setLayout(new BoxLayout(previousPanel,BoxLayout.PAGE_AXIS));
        JLabel jLabel = new JLabel("Poprzednie");
        jLabel.setPreferredSize(new Dimension(80,20));
        previousPanel.add(jLabel);
        for (int i = previousButtonsList.size()-1; i >= 0;i--) {
            previousPanel.add(previousButtonsList.get(i));
        }
        MyFrame.refreshFrame();
    }

    private void layoutComponents(){
        JPanel jPanel  = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridheight=1;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx=0;
        c.insets = new Insets(5,5,5,5);

        jPanel.add(leftLabel,c);

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;

        jPanel.add(changeSiteButton,c);

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 3;
        jPanel.add(rightLabel,c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        jPanel.add(questionField,c);

        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 3;
        jPanel.add(answerField,c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 6;
        jPanel.add(translateButton,c);

        add(jPanel,BorderLayout.CENTER);
    }

}
