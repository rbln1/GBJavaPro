package me.rubl.lesson_two;

import com.sun.istack.internal.NotNull;

import java.sql.*;
import java.util.ArrayList;

public class MainDB {

    /*
     Создать CRUD операции,
      	+ 1. метод создания таблицы
      	+ 2. метод для добавления записи
      	+ 3. метод для получения записи
      	+ 4. метод для удаления записи
      	+ 5. удаление таблицы
     */

    private static final String DB_URL = "jdbc:sqlite:main.db";
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try{
            connect();

            createTable("students",
                    "id INTEGER PRIMARY KEY AUTOINCREMENT",
                    "name TEXT",
                    "score INTEGER");

            ContentValues cv = new ContentValues();
            cv.put("name", "Andrey");
            cv.put("score", 999);
            insert("students", cv);

            ResultSet resultSet = selectById("students", 2);

            deleteByIds("students", 2, 3, 4);

            dropTable("students");

            disconnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed()) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        }
    }

    private static void disconnect() throws SQLException {
        connection.close();
        statement = null;
    }

    public static void createTable(@NotNull String tableName, String... columnNamesWithParams) throws SQLException, ClassNotFoundException {

        if (connection == null || connection.isClosed()) connect();

        if (tableName.isEmpty() || columnNamesWithParams.length == 0) return;

        StringBuilder paramsBuilder = new StringBuilder("(");
        for (int i = 0; i < columnNamesWithParams.length; i++) {

            if (i == columnNamesWithParams.length - 1) {
                paramsBuilder.append(columnNamesWithParams[i]).append(");");
            } else {
                paramsBuilder.append(columnNamesWithParams[i]).append(", ");
            }
        }
        String params = paramsBuilder.toString();

        statement.execute("create table if not exists " + tableName + " " + params);
    }

    public static int insert(String tableName, ContentValues contentValues) throws SQLException {
        if (!tableName.isEmpty() && !contentValues.isEmpty()) {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("insert into ").append(tableName).append(" (");

            for (int i = 0; i < contentValues.getColumnNames().size(); i++) {

                queryBuilder.append(contentValues.getColumnNames().get(i));
                if (i != contentValues.getColumnNames().size() - 1) {
                    queryBuilder.append(", ");
                } else {
                    queryBuilder.append(") ");
                }
            }

            queryBuilder.append("values (");
            for (int i = 0; i < contentValues.getColumnNames().size(); i++) {

                queryBuilder.append("'").append(contentValues.getValue(contentValues.getColumnNames().get(i)));

                if (i != contentValues.getColumnNames().size() - 1) {
                    queryBuilder.append("', ");
                } else {
                    queryBuilder.append("');");
                }
            }

            return statement.executeUpdate(queryBuilder.toString());
        }

        return 0;
    }

    public static ResultSet selectById(String tableName, int id) throws SQLException {

        return statement.executeQuery("select * from " + tableName + " where id = " + id + ";");
    }

    public static int deleteByIds(String tableName, int... ids) throws SQLException, ClassNotFoundException {

        if (connection == null || connection.isClosed()) connect();

        if (tableName.isEmpty()) return 0;

        preparedStatement = connection.prepareStatement("delete from " + tableName + " where id = ?");

        for (int id : ids) {
            preparedStatement.setInt(1, id);
            preparedStatement.addBatch();
        }

        return preparedStatement.executeBatch().length;
    }

    public static void dropTable(String tableName) throws SQLException {

        statement.execute("DROP TABLE IF EXISTS '" + tableName + "'");
    }
}
