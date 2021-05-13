package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

//        User perviy = new User("Первый","Пошёл",(byte) 127);
//        User zdarova = new User("Здарова","Отец",(byte) -128);
//        User prodam = new User("Продам","Гараж",(byte) 99);
//        User pokormi = new User("Покорми","Кота",(byte) 2);
//        userService.saveUser(perviy);
//        userService.saveUser(zdarova);
//        userService.saveUser(prodam);
//        userService.saveUser(pokormi);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
      //  userDaoHibernate.dropUsersTable();
       // userDaoHibernate.createUsersTable();
       // userDaoHibernate.createUsersTable();
       // userDaoHibernate.dropUsersTable();

        User my_user = new User("Имя","Фамилия", (byte) 99);
        User my_second_user = new User("Чудесный","День",(byte) 12);

        userDaoHibernate.saveUser(my_user);
        userDaoHibernate.saveUser(my_second_user);
//        userDaoHibernate.saveUser("asdasd","ssss",(byte)8);
      //  userDaoHibernate.removeUserById(2);

        userDaoHibernate.removeUserById(1);
        userDaoHibernate.getAllUsers();

     //   userDaoHibernate.cleanUsersTable();

    }
}
