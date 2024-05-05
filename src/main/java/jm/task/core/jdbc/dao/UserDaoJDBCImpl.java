package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private final Connection connection;


    public UserDaoJDBCImpl() throws SQLException {
        this.connection = new Util().getConnection();
    }


    private boolean tableExists(Connection connection) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, "user", new String[]{"TABLE"});

        return resultSet.next();
    }

    public void createUsersTable() throws SQLException {
        if (!tableExists(connection)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("""
                    CREATE TABLE user\s
                    (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        lastname VARCHAR(30),
                        age int
                    );""");
        }
    }

    public void dropUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        if (tableExists(connection)) {
            statement.executeUpdate("drop table user");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("insert into user (name,lastname,age)"
                        + "values (?,?,?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            createUsersTable();
            saveUser(name, lastName, age);
        }

    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?;");
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        List<User> users = new ArrayList<>();
        ResultSet listUsers = statement.executeQuery("SELECT * FROM user");
        while (listUsers.next()) {
            User user = new User();
            user.setId(listUsers.getLong("id"));
            user.setName(listUsers.getString("name"));
            user.setLastName(listUsers.getString("lastname"));
            user.setAge(listUsers.getByte("age"));
            users.add(user);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("TRUNCATE TABLE user");

    }
}
