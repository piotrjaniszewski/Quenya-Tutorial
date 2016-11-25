package pl.piotrjaniszewski.quenyatutorial.lessons;


import java.util.LinkedList;
import java.util.List;

public class TranslationExercise implements Exercise {
    private String title;
    private List<String> questions;
    private List<String> answers;
    private int exerciseNumber;

    public TranslationExercise(int exerciseNumber) {
        this.questions = new LinkedList<String>();
        this.answers = new LinkedList<String>();
        this.exerciseNumber = exerciseNumber;
        if(exerciseNumber==1){
            title="Przetłumacz na Quenye";
        } else{
            title="Przetłumacz na Polski";
        }

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
        return false;
    }

    public void addTask(String question,String answer){
        questions.add(question);
        answers.add(answer);
    }
}
