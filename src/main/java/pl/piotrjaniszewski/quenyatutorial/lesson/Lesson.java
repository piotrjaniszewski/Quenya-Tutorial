package pl.piotrjaniszewski.quenyatutorial.lesson;

import java.util.List;

public interface Lesson {
    public int getLessonNumber();
    public List<Exercise> getExercises();
    public List<Chapter> getChapters();
    public int getNumberOfLessons();
    public int getNumberOfExercises();
}