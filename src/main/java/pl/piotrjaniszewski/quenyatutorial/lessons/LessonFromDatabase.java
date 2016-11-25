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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Exercises WHERE lessonNr="+lessonNumber);
            Exercise exercise1 = new TranslationExercise(1);
            Exercise exercise2 = new TranslationExercise(2);

            while (resultSet.next()){
                if (resultSet.getInt("exerciseNr")==1){
                    exercise1.addTask(resultSet.getString("question"),resultSet.getString("answer"));
                }else if(resultSet.getInt("exerciseNr")==2){
                    exercise2.addTask(resultSet.getString("question"),resultSet.getString("answer"));
                }
            }

            if (exercise1.getQuestions().size()!=0) exercises.add(exercise1);
            if (exercise2.getQuestions().size()!=0) exercises.add(exercise2);
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