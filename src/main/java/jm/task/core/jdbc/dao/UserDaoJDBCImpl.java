package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }



    private boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});

        return resultSet.next();
    }

    public void createUsersTable() throws SQLException {

        Connection connection = Util.getConnection();
        if (!tableExists(Util.getConnection(), "user")) {
            Statement statement = connection.createStatement();
            String create = """
                    CREATE TABLE user\s
                    (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        lastname VARCHAR(30),
                        age int
                    );""";
            statement.executeUpdate(create);
        }
    }

    public void dropUsersTable() throws SQLException {
        Statement statement = Util.getConnection().createStatement();
        if (tableExists(Util.getConnection(), "user")) {
            String drop = "drop table user";
            statement.executeUpdate(drop);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            Statement statement = Util.getConnection().createStatement();
            String save = "insert into user (name,lastname,age)"
                    + "values ('" + name + "','" + lastName + "','" + age + "')";
            statement.executeUpdate(save);
        } catch (SQLException e) {
            createUsersTable();
            saveUser(name, lastName, age);
        }

    }

    public void removeUserById(long id) throws SQLException {
        Statement statement = Util.getConnection().createStatement();
        String clean = "delete from user where id = '" + id + "'";
        statement.executeUpdate(clean);
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = Util.getConnection().createStatement();
        String getUsers = "select * from user";
        List<User> users = new ArrayList<>();
        ResultSet listUsers = statement.executeQuery(getUsers);
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
        Statement statement = Util.getConnection().createStatement();
        String clean = "TRUNCATE TABLE user";
        statement.executeUpdate(clean);

    }
}
