package pl.piotrjaniszewski.quenyatutorial.gui.views;

import pl.piotrjaniszewski.quenyatutorial.gui.components.AnswerTextField;
import pl.piotrjaniszewski.quenyatutorial.gui.components.CustomButton;
import pl.piotrjaniszewski.quenyatutorial.gui.components.MyFrame;
import pl.piotrjaniszewski.quenyatutorial.lessons.Exercise;
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

        for ( i = 1; i <= lesson.getNumberOfExercises(); i++) {
            final CustomButton customButton = new CustomButton("Zadanie "+i);
            customButton.addActionListener(new ActionListener() {
                private final int exerciseNumber=i;
                public void actionPerformed(ActionEvent e) {
                    chapterNumber=0;
                    createTranslateExerciseView(exerciseNumber);
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

        MyFrame.refreshFrame();
    }

    private void createTranslateExerciseView(int exerciseNumber) {
        Exercise exercise = lesson.getExercises().get(exerciseNumber - 1);
        JPanel exercisePanel = new JPanel();
        exercisePanel.setLayout(new BoxLayout(exercisePanel, BoxLayout.PAGE_AXIS));

        final LinkedList<AnswerTextField> answerTextFields= new LinkedList<AnswerTextField>();

        for (int j = 0; j < exercise.getQuestions().size(); j++) {
            AnswerTextField answerTextField = new AnswerTextField(exercise.getAnswers().get(j));
            answerTextField.setPreferredSize(new Dimension(400, 20));
            JLabel jLabel = new JLabel(exercise.getQuestions().get(j));
            jLabel.setMinimumSize(new Dimension(400, 20));
            //TODO poprawić wizualnie

            exercisePanel.add(jLabel);
            exercisePanel.add(answerTextField);
            answerTextFields.add(answerTextField);
        }

        CustomButton checkButton = new CustomButton("Sprawdź poprawność");

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int j = 0; j < answerTextFields.size(); j++) {
                    answerTextFields.get(j).check();
                }
            }
        });
        exercisePanel.add(checkButton);

        contentPanel.removeAll();
        JLabel jLabel = new JLabel("Zadanie "+exerciseNumber+": "+exercise.getTitle());
        jLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel jPanel = new JPanel();
        jPanel.add(exercisePanel);
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPanel.add(jLabel,BorderLayout.PAGE_START);
        contentPanel.add(jScrollPane,BorderLayout.CENTER);

        MyFrame.refreshFrame();

        //TODO wygląd zadania
    }
}