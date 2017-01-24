package pl.piotrjaniszewski.quenyatutorial.lesson;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseLesson implements Lesson{

    private int lessonNumber;
    private List<Exercise> exercises;
    private List<Chapter> chapters;

    private Connection connection;
    private Statement  statement;
//    private static final String DB_ADDRESS = "jdbc:sqlite:src/main/resources/mydb.db";
    private static final String DB_ADDRESS ="jdbc:sqlite::resource:mydb.db";

    public DatabaseLesson(){}

    public DatabaseLesson(int lessonNumber){
        connect();
        this.lessonNumber=lessonNumber;
        this.exercises=createExercises();
        this.chapters=createChapters();
        disconnect();
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
    public List<Chapter> getChapters() {
        return chapters;
    }

    public int getNumberOfLessons() {
        connect();
        int max=0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT lessonNr FROM Chapters ORDER BY lessonNr DESC");
            max=resultSet.getInt("lessonNr");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return max;
    }
    public int getNumberOfExercises() {
        connect();
        int max=0;
        try {
            if (exercises.size()!=0){
                ResultSet resultSet1 = statement.executeQuery("SELECT * FROM Exercises WHERE lessonNr="+lessonNumber+" ORDER BY exerciseNr DESC");
                max=resultSet1.getInt("exerciseNr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return max;
    }

    private void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_ADDRESS);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Exercise> createExercises() {
        LinkedList<Exercise> exercises = new LinkedList<Exercise>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Exercises WHERE lessonNr="+lessonNumber+" ORDER BY exerciseNr DESC");
            int numerOfExercises=resultSet.getInt("exerciseNr");
            for (int i = 1; i <= numerOfExercises; i++) {
                resultSet = statement.executeQuery("SELECT * FROM Exercises WHERE lessonNr="+lessonNumber+" AND exerciseNr="+i+" ORDER BY ID ASC");
                Exercise exercise = new TranslationExercise(i,resultSet.getString("title"));
                while (resultSet.next()) {
                    exercise.addTask(resultSet.getString("question"), resultSet.getString("answer"));
                }
                exercises.add(exercise);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }
    private List<Chapter> createChapters() {
        LinkedList<Chapter> chapters = new LinkedList<Chapter>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Chapters WHERE lessonNr="+lessonNumber);
            while (resultSet.next()) {
                Chapter chapter = new ChapterImpl(resultSet.getInt("ID"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getInt("lessonNr"));
                chapters.add(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapters;
    }
}