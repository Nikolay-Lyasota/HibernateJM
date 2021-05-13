package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String PRINTF_USERNAME = "User с именем – %s добавлен в базу данных \n";
    private static final String PRINTF_REMOVE = "User c id %s удалён из БД\n";
    private static final String PRINTF_CANT_REMOVE = "В БД нет User с id %s\n";
    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users_table (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastname VARCHAR(30), age INT);";
    private static final String DROP = "DROP TABLE IF EXISTS users_table;";
    private static final String HQL_CLEAN = "DELETE FROM User";
    private static final String HQL_GET = "FROM User";
    Session session;
    SessionFactory sessionFactory;


    public UserDaoHibernateImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(CREATE);
        query.executeUpdate();
        try {
            transaction.commit();
        } catch (RollbackException e) {
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(DROP);
        query.executeUpdate();
        try {
            transaction.commit();
        } catch (RollbackException e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        try {
            transaction.commit();
            System.out.printf(PRINTF_USERNAME, name);
        } catch (RollbackException e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        try {
            transaction.commit();
            System.out.printf(PRINTF_USERNAME, user.getName());
        } catch (RollbackException e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = session.beginTransaction();
        User user;
        user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
            System.out.printf(PRINTF_REMOVE, id);
        } else {
            System.out.printf(PRINTF_CANT_REMOVE, id);
        }
        try {
            transaction.commit();
        } catch (RollbackException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list;
        list = session.createQuery(HQL_GET).list();
        System.out.println(list.toString().replaceAll("[\\[\\],]", ""));
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(HQL_CLEAN);
        query.executeUpdate();
        try {
            transaction.commit();
            System.out.println("Таблица очищена\n");
        } catch (RollbackException e) {
            transaction.rollback();
        }
    }
}
