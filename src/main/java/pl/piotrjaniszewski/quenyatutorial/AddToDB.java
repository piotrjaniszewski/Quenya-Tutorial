//package pl.piotrjaniszewski.quenyatutorial;
//
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.LinkedList;
//import java.util.List;
//
//public class AddToDB {
//    public Connection connection;
//    public Statement statement;
//    public static final String DB_ADDRESS = "jdbc:sqlite:src/main/resources/mydb.db";
//
//    public static void main(String[] args) throws IOException, SQLException {
//        AddToDB addToDB = new AddToDB();
//        addToDB.connect();
//        int licznik =0;
//
//        for (int i = 1; i <= 20; i++) {
//            FileReader fileReader = new FileReader("C:\\Users\\Piotr\\Downloads\\Quenya\\lekcja"+i+"\\lekcja\\tytuly.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            List<String> titles = new LinkedList<String>();
//
//            String text = bufferedReader.readLine();
//            while(text!=null){
//                titles.add(text);
//                text = bufferedReader.readLine();
//            }
//
//            bufferedReader.close();
//            fileReader.close();
//            System.out.println("Lekcja "+i);
//            for (int j = 1; j < titles.size(); j++) {
//            System.out.println(licznik +" "+titles.get(j));
//                fileReader = new FileReader("C:\\Users\\Piotr\\Downloads\\Quenya\\lekcja"+i+"\\lekcja\\"+titles.get(j)+".txt");
//
//                bufferedReader = new BufferedReader(fileReader);
//
//                text= bufferedReader.readLine();
//                String content="";
//                while(text!=null){
//                    content+="\n"+text;
//                    text= bufferedReader.readLine();
//                }
//                licznik++;
//               // content.replaceAll("\"","\"\"");
//                content = content.replaceAll("\'","\'\'");
//             //   System.out.println(content);
//                 addToDB.statement.execute("INSERT INTO `Chapters` (`ID`, `content`,`title`,`lessonNr`) VALUES ('"+licznik+"', '"+content+"','"+titles.get(j).replaceAll("\'","\'\'")+"','"+i+"')");
//                bufferedReader.close();
//
//                fileReader.close();
//            }
//        }
//
////        addToDB.statement.execute("INSERT INTO `Dictionary` (`quenya`, `polski`) VALUES ('"+q+"', '"+p+"')");
//        addToDB.disconnect();
//    }
//
//    private void connect(){
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection(DB_ADDRESS);
//            statement = connection.createStatement();
//            System.out.println("polączono");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void disconnect(){
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
