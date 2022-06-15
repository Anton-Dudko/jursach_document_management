package dataBase;

import constants.Constants;

import java.sql.*;
import java.util.LinkedList;

public class Database {
    private  Connection connection;
    private  Statement statement;

    public Database() {
        connectionToDB();
        Tables tables = new Tables(connection,statement);
        tables.createTablesInDataBase();
    }

    public void connectionToDB(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(Constants.HOST_DATABASE+Constants.NAME_DATABASE,
                    Constants.USER_DATABASE,
                    Constants.PASSWORD_DATABASE);
            statement= connection.createStatement();

            System.out.println("Database connection is done");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void appendNewUserInDatabase(String name,String lastName,String email,String password,String roll){

        try {
            String query = " insert into "+Constants.USERS_TABLE+ " (email, password,roll,name,lastName )"
                    + " values ( ?, ?,?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setString (2, password);
            preparedStmt.setString (3, roll);
            preparedStmt.setString (4, name);
            preparedStmt.setString (5, lastName);


            preparedStmt.executeUpdate();
            System.out.println("Новый пользователь был добавлен !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String verificationUserInDataBase(String email, String password){
        String user="";
        try {
            String query = "SELECT " + Constants.EMAIL + "," + Constants.PASSWORD+","+Constants.ID+","+Constants.ROLL + " FROM " + Constants.USERS_TABLE +
                    " WHERE " + Constants.EMAIL + " = " + "'" + email + "'" + " AND " + Constants.PASSWORD + " = " + "'" + password + "'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                    user+=rs.getString(Constants.EMAIL)+" ";
                    user+=rs.getString(Constants.PASSWORD)+" ";
                    user+=rs.getString(Constants.ROLL);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user.equals("")) {
            System.out.println("Пользователь не найден !");
            return "false";
        }
        else {
            return user;
        }
    }

    public LinkedList<String> conclusionAllUsers() {

        LinkedList<String> users = new LinkedList<>();
        String query = "SELECT "+Constants.ID+" , " + Constants.NAME_USER+" , "+Constants.LAST_NAME_USER+" , "
                +Constants.EMAIL+" , " + Constants.PASSWORD+" , " + Constants.ROLL+ " FROM " + Constants.USERS_TABLE ;
        ResultSet rs = null;
        String user="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {

                user+=rs.getString(Constants.ID)+" ";
                user+=rs.getString(Constants.NAME_USER)+" ";
                user+=rs.getString(Constants.LAST_NAME_USER)+" ";
                user+=rs.getString(Constants.EMAIL)+" ";
                user+=rs.getString(Constants.PASSWORD)+" ";
                user+=rs.getString(Constants.ROLL)+" ";
                users.add(user);
                user="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    public void deleteUserInDataBase(int id)  {
        System.out.println(id);
        String selectSQL = "DELETE FROM "+Constants.USERS_TABLE +  " WHERE id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public  void redactionUserDatabase(int id,String name ,String lastName,String email ,String password,String roll )  {

        String query = "UPDATE users SET name  = ?, lastName = ? ,email = ?,password = ? , roll = ?   WHERE id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, name);
            preparedStmt.setString(2, lastName);
            preparedStmt.setString(3, email);
            preparedStmt.setString(4, password);
            preparedStmt.setString(8, roll);
            preparedStmt.setInt(9, id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public LinkedList<String> showAllDOCUMENTS(){

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT "+Constants.DOCUMENT_ID +" , " + Constants.NAME_DOCUMENT +" , "+Constants.DATE_DOCUMENT +" , "
                +Constants.ABOUT_DOCUMENT +" ," +Constants.CHECK_DOCUMENT + " FROM " + Constants.DOCUMENTS_TABLE;
        ResultSet rs = null;
        String contribution="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {

                contribution+=rs.getString(Constants.DOCUMENT_ID)+" ";
                contribution+=rs.getString(Constants.NAME_DOCUMENT)+" ";
                contribution+=rs.getString(Constants.DATE_DOCUMENT)+" ";
                contribution+=rs.getString(Constants.ABOUT_DOCUMENT)+" ";
                contribution+=rs.getString(Constants.CHECK_DOCUMENT)+" ";

                list.add(contribution);

                contribution="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addDocumentInDataBase(String name, String date, String about, String check){

        try {
            String query = " insert into "+Constants.DOCUMENTS_TABLE +" (name, date,about,checkDb)"
                    + " values ( ?, ?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, date);
            preparedStmt.setString (3, about);
            preparedStmt.setString (4, check);


            preparedStmt.executeUpdate();
            System.out.println("Новый документ был добавлен !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteDocumentInDataBase(int id)  {
        System.out.println(id);
        String selectSQL = "DELETE FROM "+Constants.DOCUMENTS_TABLE +  " WHERE id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public  void redactionDocumentInDataBase(int id, String name, String date, String about, String check )  {

        String query = "UPDATE "+Constants.DOCUMENTS_TABLE +" SET name  = ?, date = ? ,about = ?,checkDb = ?   WHERE id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, name);
            preparedStmt.setString(2, date);
            preparedStmt.setString(3, about);
            preparedStmt.setString(4, check);
            preparedStmt.setInt(5, id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
