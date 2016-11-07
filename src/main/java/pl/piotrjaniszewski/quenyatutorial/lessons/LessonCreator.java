package pl.piotrjaniszewski.quenyatutorial.lessons;

import java.sql.SQLException;
import java.util.List;

public interface LessonCreator {
    public int getNumber();
    public List<Exercise> getExercises();
    public List<Chapter> getChapters();
}
