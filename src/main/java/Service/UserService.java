package Service;
import DAO.*;
import User.User;

import java.io.IOException;
import java.util.List;

public class UserService  {

   private static UserService instance;
   private  DAO dao = getDao();
   private UserService(){

    }
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private DAO getDao(){
        try{
            dao = AbstractDaoFactory.getInstance().getUserDao();
        }catch (IOException | IllegalArgumentException e){
            e.printStackTrace();
        }
        return dao;
    }


    public void addUser(User user)  {
        dao.addUser(user);
    }


    public List<User> getAllUsers()  {
        return dao.getAllUsers();
}


    public void deleteUser(long id)  {
        dao.deleteUser(id);
    }


    public User getUserById(Long id) {
        User user = null;
        user = dao.getUserById(id);
        return user;
    }


    public void editUser(User user)  {
        dao.editUser(user);
    }
    public  boolean isUserExist(User user){
       return  dao.isUserExist(user);

    }
    public User getUserByName(String name){
       return dao.getUserByName(name);

    }

}
