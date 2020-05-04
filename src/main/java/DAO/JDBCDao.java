package DAO;
import User.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDao implements DAO {
    private Connection connection;
    private static JDBCDao instance;

    public static JDBCDao getInstance(Connection connection) {
        if (instance == null) {
            instance = new JDBCDao(connection);
        }
        return instance;
    }

    private JDBCDao(Connection connection) {
        this.connection = connection;
    }

    private final String SQL_CREATE_USER = "INSERT INTO users (name, email) VALUES (?, ?)";
    private final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private final String SQL_UPDATE_USER = "UPDATE users SET name=?, email=?  WHERE id=?";
    private final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";


    @Override
    public void addUser(User user)  {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        ResultSet result;
        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL_GET_ALL_USERS);
            result = statement.getResultSet();
            while (result.next()){
                Long id = result.getLong("id");
                String name = result.getString("name");
                String email = result.getString("email");
                User user = new User(id, name, email) ;
                list.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void deleteUser(long id)  {

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)){
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public User getUserById(long id)  {
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            String name = result.getString("name");
            String email = result.getString("email");
            user = new User(id, name, email);
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void editUser(User user) {
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
