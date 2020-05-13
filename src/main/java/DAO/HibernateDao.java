package DAO;
import User.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class HibernateDao implements DAO {


    private SessionFactory sessionFactory;
    private static HibernateDao instance;

    private HibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static HibernateDao getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new HibernateDao(sessionFactory);
        }
        return instance;
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query <User> query = session.createQuery("FROM User where name =?", User.class);
        query.setParameter(0, name);
        User user = query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public boolean isUserExist(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query <User> query = session.createQuery("FROM User where name =? and email =?", User.class);
        query.setParameter(0, user.getName());
        query.setParameter(1, user.getEmail());
        User user1 = query.uniqueResult();
        transaction.commit();
        session.close();
        return user1 != null;
    }

    @Override
    public void addUser(User user)  {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User");
        List<User> list = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE User where id = :id").setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public User getUserById(long id)  {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User where id=?1")
                .setParameter(1, id);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void editUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set name =? , email =?, role =?  where id =?");
        query.setParameter(0, user.getName());
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getRole());
        query.setParameter(3, user.getId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
