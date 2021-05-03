package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String USERNAME = "User с именем – %s добавлен в базу данных \n";
    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users_table (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(30), lastname VARCHAR(30), age INT, PRIMARY KEY(id) );";
    private static final String DROP = "DROP TABLE IF EXISTS users_table;";
    private static final String SAVE = "INSERT INTO users_table (name, lastname, age) VALUES (?, ?, ?);";
    private static final String SAVEUSER = "INSERT INTO users_table (name, lastname, age) VALUES (?, ?, ?);";
    private static final String REMOVE = "DELETE FROM users_table WHERE id = ?";
    private static final String CLEAN = "DELETE FROM users_table;";
    private static final String GET = "SELECT id, name, lastname, age FROM users_table;";
    private static final String GETLASTUSER = "SELECT name FROM users_table ORDER BY id DESC;";
    Util util;
    Connection connection;

    public UserDaoJDBCImpl() {
        util = new Util();
        connection = util.getConnection();
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(CREATE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DROP);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.printf(USERNAME, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVEUSER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.executeUpdate();
            System.out.printf(USERNAME, user.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE);
            preparedStatement.setLong(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                result.add(user);
            }
            System.out.println(result.toString().replaceAll("[\\[\\],]", ""));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public String getLastUser() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GETLASTUSER);
            resultSet.next();
            return resultSet.getNString("name");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CLEAN);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
