package pl.piotrjaniszewski.quenyatutorial.gui.views;

import pl.piotrjaniszewski.quenyatutorial.gui.components.CustomButton;
import pl.piotrjaniszewski.quenyatutorial.gui.components.MyFrame;
import pl.piotrjaniszewski.quenyatutorial.lessons.Lesson;
import pl.piotrjaniszewski.quenyatutorial.lessons.LessonFromDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class LessonsView extends JPanel {

    private List<CustomButton> lessonButtonList = new LinkedList<CustomButton>();
    private List<CustomButton> chapterButtonList = new LinkedList<CustomButton>();
    private Lesson lesson;

    private int i;
    private int chapterNumber=1;

    private JPanel lessonButtonsPanel= new JPanel();
    private JPanel contentPanel= new JPanel(new BorderLayout());
    private JPanel chapterButtonsPanel=new JPanel();

    public LessonsView() {
        super(new BorderLayout());
        lesson = new LessonFromDatabase(1);
        createLessonButtonList();
        createChapterButtonList();
        createComponents();
        layoutComponents();
    }

    private void createComponents() {
        createLessonButtonsPanel();
        createChapterView();
        createChapterButtonsPanel();
    }
    private void layoutComponents() {
        add(lessonButtonsPanel,BorderLayout.LINE_START);
        add(chapterButtonsPanel,BorderLayout.LINE_END);
        add(contentPanel,BorderLayout.CENTER);
    }

    private void createLessonButtonList(){
        for (i = 1; i <= new LessonFromDatabase().getNumberOfLessons(); i++) {
            CustomButton customButton = new CustomButton("Lekcja "+i);
            customButton.addActionListener(new ActionListener() {
                private int lessonNumber=i;
                public void actionPerformed(ActionEvent e) {
                    lesson = new LessonFromDatabase(lessonNumber);
                    chapterNumber=1;
                    createChapterButtonList();
                    createChapterButtonsPanel();
                    createChapterView();
                }
            });
            lessonButtonList.add(customButton);
        }
    }
    private void createChapterButtonList() {
        chapterButtonList.clear();
        for (i = 1; i <= lesson.getChapters().size(); i++) {
            final CustomButton customButton = new CustomButton("Rozdział "+i);
            customButton.addActionListener(new ActionListener() {
                private final int k = i;
                public void actionPerformed(ActionEvent e) {
                    chapterNumber=k;
                    createChapterView();
                }
            });
            chapterButtonList.add(customButton);
        }
    }

    private void createLessonButtonsPanel() {
        lessonButtonsPanel.setLayout(new BoxLayout(lessonButtonsPanel,BoxLayout.PAGE_AXIS));
        for (int j = 0; j < lessonButtonList.size(); j++) {
            lessonButtonsPanel.add(lessonButtonList.get(j));
        }
    }
    private void createChapterButtonsPanel() {
        chapterButtonsPanel.removeAll();
        chapterButtonsPanel.setLayout(new BoxLayout(chapterButtonsPanel,BoxLayout.PAGE_AXIS));
        for (int j = 0; j < chapterButtonList.size(); j++) {
            chapterButtonsPanel.add(chapterButtonList.get(j));
        }

        //TODO dodać przyciski cwiczen

        MyFrame.refreshFrame();

        chapterButtonsPanel.add(Box.createVerticalGlue());

        CustomButton returnButton = new CustomButton("Menu główne");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame =(JFrame) JFrame.getFrames()[0];
                frame.setContentPane(new MainMenuView());
                frame.setVisible(true);
            }
        });
        chapterButtonsPanel.add(returnButton);
        MyFrame.refreshFrame();
    }

    private void createChapterView() {
        contentPanel.removeAll();
        JLabel jLabel = new JLabel("Lekcja "+lesson.getLessonNumber()+": "+lesson.getChapters().get(chapterNumber-1).getTitle());
        jLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText(lesson.getChapters().get(chapterNumber-1).getContent());

        JScrollPane jScrollPane = new JScrollPane(jTextPane);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPanel.add(jLabel,BorderLayout.PAGE_START);
        contentPanel.add(jScrollPane,BorderLayout.CENTER);

        add(contentPanel,BorderLayout.CENTER);
        MyFrame.refreshFrame();
    }
    private void createTranslateExerciseView(){
        //TODO wygląd zadania
    }
}