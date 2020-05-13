package User;
import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;

    public User(){

    }

    public User(long id, String newName, String newEmail, String newRole){

    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setId(long id) {
        this.id = id;
    }


}





