package landmarketmavenproto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by Nik_NB on 18.03.2017.
 */
public class Seller {
    @Id
    private String id;


    @Field("login")
    @Indexed(unique = true)
    private String login;
    @Field("password")
    private String password;
    @Field("confirm")
    private String confirm;
    @Field("email")
    private String email;


    public Seller() {
    }

    public Seller(String login, String email, String password, String confirm) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
