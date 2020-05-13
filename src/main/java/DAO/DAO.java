package DAO;
import User.User;

import java.util.List;

public interface DAO {



     User getUserByName(String name);

     boolean isUserExist(User user);

     void addUser(User user);

     List<User> getAllUsers() ;

     void deleteUser(long id) ;

     User getUserById(long id) ;

     void editUser(User user) ;
}
