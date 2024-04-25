package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl   implements UserService {

    UserDaoJDBCImpl user = new UserDaoJDBCImpl();


    public void createUsersTable() throws SQLException {
user.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        user.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
user.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
