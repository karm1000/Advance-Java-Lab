package jdbc;

import java.sql.*;

public class TEMP {
    Connection connection;
    String url="jdbc:mysql://localhost:3301/temp?characterEncoding=UTF-8";
    String user="root";
    String password="";

    TEMP() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3301/temp?characterEncoding=UTF-8", "root", "");
        Statement statement = connection.createStatement();
//        statement.executeUpdate("create table student(rollno int,name varchar(30),marks1 int,marks2 int)");
//        statement.executeUpdate("CREATE PROCEDURE GetStudentCount()\n" +
//                "BEGIN\n" +
//                "    DECLARE totalStudents INT;\n" +
//                "    \n" +
//                "    SELECT COUNT(*) INTO totalStudents FROM Student;\n" +
//                "    \n" +
//                "    SELECT totalStudents;\n" +
//                "END");

//        statement.executeUpdate("CREATE PROCEDURE CALCP(IN r INT,OUT P FLOAT)\n" +
//                "BEGIN\n" +
//                "    DECLARE m1 FLOAT;\n" +
//                "    DECLARE m2 FLOAT;\n" +
//                "    \n" +
//                "    SELECT marks1,marks2 INTO m1,m2 FROM Student where rollno = r;\n" +
//                "    SET P = (m1+m2)/2;\n" +
//                "END");
        connection.close();
    }

    void insert(int rollno,String name,int m1,int m2) throws Exception{
        try {
            this.connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into student values(?,?,?,?)");

            preparedStatement.setInt(1,rollno);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,m1);
            preparedStatement.setInt(4,m2);

            int r = preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }


    }

    int noOfStudents() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url,user,password);
            CallableStatement callableStatement = connection.prepareCall("{call GetStudentCount()}");

            ResultSet r = callableStatement.executeQuery();
            if(r.next()){
                return r.getInt(1);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
        return 0;
    }


    float getPercentage(int rollno) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url,user,password);
            CallableStatement callableStatement = connection.prepareCall("{call CALCP(?,?)}");
            callableStatement.setInt(1,rollno);
            callableStatement.registerOutParameter(2,Types.FLOAT);
            callableStatement.execute();
            float p = callableStatement.getFloat(2);
            return p;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
        return 0.0f;
    }



    public static void main(String[] args) throws Exception {
        TEMP t = new TEMP();
//        t.insert(1,"karm",100,99);
//        t.insert(2,"khush",99,100);
//        t.insert(3,"zalak",40,60);
//        t.insert(4,"het",70,0);
        System.out.println(t.getPercentage(5));
    }
}
