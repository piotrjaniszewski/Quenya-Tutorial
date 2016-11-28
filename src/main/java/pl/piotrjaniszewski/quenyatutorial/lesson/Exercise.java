package pl.piotrjaniszewski.quenyatutorial.lesson;

import java.util.List;

public interface Exercise {

    public String getTitle();
    public List<String> getQuestions();
    public List<String> getAnswers();
    public int exerciseNumber();
    public boolean check(String userAnswer, int number);
    public void addTask(String question,String answer);
}