package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        User perviy = new User("Первый","Пошёл",(byte) 127);
        User zdarova = new User("Здарова","Отец",(byte) -128);
        User prodam = new User("Продам","Гараж",(byte) 99);
        User pokormi = new User("Покорми","Кота",(byte) 2);
        userService.saveUser(perviy);
        userService.saveUser(zdarova);
        userService.saveUser(prodam);
        userService.saveUser(pokormi);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
