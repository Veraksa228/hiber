package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Andrew", "Ivaniv", (byte) 2);
        System.out.println("User с именем Andrew добаблен в базу данных");
        service.saveUser("Kate", "iss", (byte) 5);
        System.out.println("User с именем Kate добаблен в базу данных");
        service.saveUser("Vasya", "asad", (byte) 8);
        System.out.println("User с именем Vasya добаблен в базу данных");
        service.saveUser("Sasha", "Kot", (byte) 24);
        System.out.println("User с именем Sasha добаблен в базу данных");
        List<User> users = service.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        service.cleanUsersTable();
        service.dropUsersTable();


    }
}
