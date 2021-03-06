package pl.piotrjaniszewski.quenyatutorial.gui.views;

import pl.piotrjaniszewski.quenyatutorial.gui.components.AnswerTextField;
import pl.piotrjaniszewski.quenyatutorial.gui.components.CustomButton;
import pl.piotrjaniszewski.quenyatutorial.gui.components.MyFrame;
import pl.piotrjaniszewski.quenyatutorial.lesson.Exercise;
import pl.piotrjaniszewski.quenyatutorial.lesson.Lesson;
import pl.piotrjaniszewski.quenyatutorial.lesson.DatabaseLesson;

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
        lesson = new DatabaseLesson(1);
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
        for (i = 1; i <= new DatabaseLesson().getNumberOfLessons(); i++) {
            final CustomButton customButton = new CustomButton("Lekcja "+i);
            customButton.setAllSizes(new Dimension(80,20));
            customButton.addActionListener(new ActionListener() {
                private int lessonNumber=i;
                public void actionPerformed(ActionEvent e) {
                    lesson = new DatabaseLesson(lessonNumber);
                    chapterNumber=1;
                    createChapterButtonList();
                    createChapterButtonsPanel();
                    createChapterView();
                    for (int j = 0; j < lessonButtonList.size(); j++) {
                        lessonButtonList.get(j).setSelected(false);
                    }
                    lessonButtonList.get(lessonNumber-1).setSelected(true);
                }
            });
            lessonButtonList.add(customButton);
        }
        lessonButtonList.get(0).setSelected(true);
    }
    private void createChapterButtonList() {
        chapterButtonList.clear();
        for (i = 1; i <= lesson.getChapters().size(); i++) {
            final CustomButton customButton = new CustomButton("Rozdział "+i);
            customButton.setAllSizes(new Dimension(80,20));
            customButton.addActionListener(new ActionListener() {
                private final int k = i;
                public void actionPerformed(ActionEvent e) {
                    chapterNumber=k;
                    createChapterView();
                    for (int j = 0; j < chapterButtonList.size(); j++) {
                        chapterButtonList.get(j).setSelected(false);
                    }
                    chapterButtonList.get(chapterNumber-1).setSelected(true);
                }
            });
            chapterButtonList.add(customButton);
        }

        for ( i = 1; i <= lesson.getNumberOfExercises(); i++) {
            final CustomButton customButton = new CustomButton("Zadanie "+i);
            customButton.setAllSizes(new Dimension(80,20));
            customButton.addActionListener(new ActionListener() {
                private final int exerciseNumber=i;
                public void actionPerformed(ActionEvent e) {
                    chapterNumber=0;
                    createTranslateExerciseView(exerciseNumber);

                    for (int j = 0; j < chapterButtonList.size(); j++) {
                        chapterButtonList.get(j).setSelected(false);
                    }
                    chapterButtonList.get(lesson.getChapters().size() + exerciseNumber-1).setSelected(true);
                }
            });
            chapterButtonList.add(customButton);
        }
        chapterButtonList.get(0).setSelected(true);
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
        chapterButtonsPanel.add(Box.createVerticalGlue());

        CustomButton returnButton = new CustomButton("Menu główne");
        returnButton.setAllSizes(new Dimension(80,20));
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame =(JFrame) JFrame.getFrames()[0];
                frame.setContentPane(new MainMenuView());
                frame.setVisible(true);
            }
        });
        chapterButtonsPanel.add(returnButton);
        SwingUtilities.updateComponentTreeUI(chapterButtonsPanel);
    }

    private void createChapterView() {
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText(lesson.getChapters().get(chapterNumber-1).getContent());
        jTextPane.setEditable(false);
        jTextPane.setCaretPosition(0);
        createContentView(jTextPane,"Lekcja "+lesson.getLessonNumber()+": "+lesson.getChapters().get(chapterNumber-1).getTitle());
    }

    private void createTranslateExerciseView(int exerciseNumber) {
        final Exercise exercise = lesson.getExercises().get(exerciseNumber - 1);
        JPanel exercisePanel = new JPanel();
        exercisePanel.setLayout(new BoxLayout(exercisePanel, BoxLayout.PAGE_AXIS));

        final LinkedList<AnswerTextField> answerTextFields= new LinkedList<AnswerTextField>();

        for (int j = 0; j < exercise.getQuestions().size(); j++) {
            AnswerTextField answerTextField = new AnswerTextField();
            answerTextField.setPreferredSize(new Dimension(400, 20));
            JLabel jLabel = new JLabel(exercise.getQuestions().get(j));
            jLabel.setMinimumSize(new Dimension(400, 20));

            exercisePanel.add(jLabel);
            exercisePanel.add(answerTextField);
            answerTextFields.add(answerTextField);
            exercisePanel.add(Box.createRigidArea(new Dimension(10,10)));
        }
        CustomButton checkButton = new CustomButton("Sprawdź poprawność");
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int j = 0; j < answerTextFields.size(); j++) {
                    answerTextFields.get(j).setCorrect(exercise.check(answerTextFields.get(j).getText(),j));
                    MyFrame.refreshFrame();
                }
            }
        });

        CustomButton correctAnswersButton = new CustomButton("Pokaż poprawne odpowiedzi");
        correctAnswersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int j = 0; j < answerTextFields.size(); j++) {
                    answerTextFields.get(j).setText(exercise.getAnswers().get(j));
                    answerTextFields.get(j).resetCorrect();
                    MyFrame.refreshFrame();
                }
            }
        });
        exercisePanel.add(Box.createRigidArea(new Dimension(10,10)));
        exercisePanel.add(checkButton);
        exercisePanel.add(Box.createRigidArea(new Dimension(10,10)));
        exercisePanel.add(correctAnswersButton);

        JPanel jPanel = new JPanel();
        jPanel.add(exercisePanel);
        createContentView(jPanel,"Zadanie "+exerciseNumber+": "+exercise.getTitle());
    }
    private void createContentView(Component view, String title){
        JScrollPane jScrollPane = new JScrollPane(view);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTextPane jTextPane = new JTextPane();
        jTextPane.setText(title);
        jTextPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        jTextPane.setFont(new Font(jTextPane.getFont().getName(),Font.BOLD, (int) (jTextPane.getFont().getSize()*1.1)));
        jTextPane.setEditable(false);
        jScrollPane.setBorder(null);
        contentPanel.removeAll();
        contentPanel.add(jTextPane,BorderLayout.PAGE_START);
        contentPanel.add(jScrollPane,BorderLayout.CENTER);

        MyFrame.refreshFrame();
    }
}