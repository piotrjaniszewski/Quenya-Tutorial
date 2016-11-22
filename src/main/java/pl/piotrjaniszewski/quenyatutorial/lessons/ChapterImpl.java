package pl.piotrjaniszewski.quenyatutorial.lessons;


public class ChapterImpl implements Chapter {

    private int id;
    private String title;
    private String content;
    private int lessonNumber;

    public ChapterImpl(int id, String title, String content, int lessonNumber) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.lessonNumber = lessonNumber;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }
}