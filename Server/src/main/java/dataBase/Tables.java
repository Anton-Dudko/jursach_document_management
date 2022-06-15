package dataBase;

import constants.Constants;

import java.sql.*;

public class Tables {
    private final Connection connection;
    private final Statement statement;

    public Tables(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;

    }

    public void createTablesInDataBase(){
        addTableUserInDateBase();
        addTableDOCUMENT();
    }

    private void addTableUserInDateBase(){
        if(tableExists(Constants.USERS_TABLE)) {
            try {
                String SQL = "CREATE TABLE "+Constants.USERS_TABLE +
                        "( " +
                        " id  SERIAL PRIMARY KEY," +
                        " email VARCHAR (50), " +
                        " name VARCHAR (50), " +
                        " lastName VARCHAR (50), " +
                        " password VARCHAR (50), " +
                        " roll VARCHAR (50)"+
                        ")";
                System.out.println("Tables created !");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTableDOCUMENT(){
        if(tableExists(Constants.DOCUMENTS_TABLE)) {
            try {
                String SQL = "CREATE TABLE "+Constants.DOCUMENTS_TABLE +
                        "( " +
                        " id  SERIAL PRIMARY KEY," +
                        " name VARCHAR (50), " +
                        " date VARCHAR (50), " +
                        " about VARCHAR (50), " +
                        " checkDb VARCHAR (50)" +
                        ")";
                System.out.println("Tables created !");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean tableExists(String nameTable){

        try{
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, nameTable, null);
            rs.last();
            return rs.getRow() <= 0;
        }catch(SQLException ignored){

        }
        return true;
    }
}
