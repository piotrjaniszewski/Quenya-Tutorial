package pl.piotrjaniszewski.quenyatutorial.lessons;

import java.util.List;


public class StandardLesson implements Lesson{

    private int lessonNumber;
    private List<Exercise> exercises;
    private List<Chapter> chapters;

    public StandardLesson(LessonCreator lessonCreator){
        this.lessonNumber=lessonCreator.getNumber();
        this.exercises=lessonCreator.getExercises();
        this.chapters=lessonCreator.getChapters();
    }

    public int getNumber() {
        return 0;
    }

    public List<Exercise> getExercises() {
        return null;
    }

    public List<Chapter> getChapters() {
        return null;
    }
}
