package pl.piotrjaniszewski.quenyatutorial.lessons;

import java.util.List;

public interface Lesson {
    public int getNumber();
    public List<Exercise> getExercises();
    public List<Chapter> getChapters();
}