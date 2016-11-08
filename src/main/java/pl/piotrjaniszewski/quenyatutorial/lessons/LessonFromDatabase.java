package pl.piotrjaniszewski.quenyatutorial.lessons;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class LessonFromDatabase implements Lesson{

    private int lessonNumber;
    private List<Exercise> exercises;
    private List<Chapter> chapters;

    private Connection connection;
    private Statement  statement;
    private static final String DB_ADDRESS = "jdbc:sqlite:src/main/resources/mydb.db";

    public LessonFromDatabase(){}

    public LessonFromDatabase(int lessonNumber){
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
            ResultSet resultSet1 = statement.executeQuery("SELECT lessonNr FROM Chapters ORDER BY lessonNr DESC");
            ResultSet resultSet2 = statement.executeQuery("SELECT lessonNr FROM Chapters ORDER BY lessonNr DESC");

            if(resultSet1.getInt("lessonNr")> resultSet2.getInt("lessonNr")){
                max=resultSet1.getInt("lessonNr");
            }
            else{
                max=resultSet2.getInt("lessonNr");
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Exercises WHERE lessonNr="+lessonNumber);
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                if (type == "quenya-polski" || type == "polski-quenya") {
                    Exercise exercise = new TranslationExercise(type, resultSet.getString("title"), resultSet.getString("question"), resultSet.getString("answer"), lessonNumber);
                    exercises.add(exercise);
                } else {

                }
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
