package pl.piotrjaniszewski.quenyatutorial.lessons;

public interface Exercise {

    public String getTitle();
    public String getType();
    public String getQuestion();
    public String getCorrectAnswer();
    public int getLessonNumber();
    public int exerciseNumber();
}