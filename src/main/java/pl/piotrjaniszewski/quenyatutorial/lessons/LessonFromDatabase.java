package pl.piotrjaniszewski.quenyatutorial.lessons;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LessonFromDatabase implements LessonCreator {

    private Connection connection;
    private Statement  statement;
    private static final String DB_ADDRESS = "jdbc:sqlite:src/main/resources/mydb.db";
    private int lessonNumber;
    private ResultSet chapterTable;
    private ResultSet exercisesTable;

    public LessonFromDatabase(int lessonNumber){
        this.lessonNumber=lessonNumber;
        connect();
        chapterTable = getChapterTable();
        exercisesTable = getExercisesTable();
        disconnect();
    }

    public int getNumber() {
        return lessonNumber;
    }

    public List<Exercise> getExercises() {
        LinkedList<Exercise> exercises = new LinkedList<Exercise>();

        try {
            while (exercisesTable.next()) {
                String type = exercisesTable.getString("type");

                if (type == "quenya-polski" || type == "polski-quenya") {
                    Exercise exercise = new TranslationExercise(type, exercisesTable.getString("title"), exercisesTable.getString("question"), exercisesTable.getString("answer"), lessonNumber);
                    exercises.add(exercise);
                } else {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    public List<Chapter> getChapters() {
        LinkedList<Chapter> chapters = new LinkedList<Chapter>();

        try {
            while (chapterTable.next()) {
                Chapter chapter = new ChapterImpl(exercisesTable.getInt("ID"), exercisesTable.getString("title"),exercisesTable.getString("content"),exercisesTable.getInt("lessonNr"));
                chapters.add(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapters;
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
    private ResultSet getChapterTable(){
        ResultSet resultSet=null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Chapters WHERE lessonNr="+lessonNumber+"ORDER BY ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    private ResultSet getExercisesTable() {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Exercises WHERE lesson="+lessonNumber+"ORDER BY ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
