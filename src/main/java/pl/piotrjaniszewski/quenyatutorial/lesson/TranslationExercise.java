package pl.piotrjaniszewski.quenyatutorial.lesson;


import java.util.LinkedList;
import java.util.List;

public class TranslationExercise implements Exercise {
    private String title;
    private List<String> questions;
    private List<String> answers;
    private int exerciseNumber;

    public TranslationExercise(int exerciseNumber, String title) {
        this.questions = new LinkedList<String>();
        this.answers = new LinkedList<String>();
        this.exerciseNumber = exerciseNumber;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int exerciseNumber() {
        return exerciseNumber;
    }

    public boolean check(String userAnswer, int number) {
        return userAnswer.contentEquals(answers.get(number));
    }

    public void addTask(String question,String answer){
        questions.add(question);
        answers.add(answer);
    }
}
