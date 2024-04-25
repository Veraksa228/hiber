package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private final Util util = new Util();



    public void createUsersTable() throws SQLException {
        Connection connection = util.getConnection();
        Statement statement = connection.createStatement();
        String create = """
                CREATE TABLE user\s
                (
                    id INT,
                    name VARCHAR(255) NOT NULL,
                    lastname VARCHAR(30),
                    age int
                );""";
        statement.executeUpdate(create);
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = util.getConnection();
        Statement statement = connection.createStatement();
        String drop = "drop table user";
        statement.executeUpdate(drop);


    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = util.getConnection();
        Statement statement = connection.createStatement();
        String save = String.format("insert into user (name,lastname,age)"
        + "values ('%s','%s','%d');",name,lastName,age);

        statement.executeUpdate(save);
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = util.getConnection();

        return null;
    }

    public void cleanUsersTable() {

    }
}
