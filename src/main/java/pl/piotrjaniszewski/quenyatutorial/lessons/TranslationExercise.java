package pl.piotrjaniszewski.quenyatutorial.lessons;


public class TranslationExercise implements Exercise {

    private String type;
    private String title;
    private String question;
    private String correctAnswer;
    private int lessonNumber;

    public TranslationExercise(String type, String title, String question, String correctAnswer, int lessonNumber) {
        this.type = type;
        this.title = title;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.lessonNumber = lessonNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }
}
