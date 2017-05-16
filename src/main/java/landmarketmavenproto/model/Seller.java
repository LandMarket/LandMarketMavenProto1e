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
    @Field("passport")
    private String confirm;
    //@Field("companyName")
  //  private String companyName;
    //@Field("phone")
   // private String phone;
    //@Field("address")
   // private String address;
    @Field("email")
    private String email;
    //@Field("managerName")
    //private String managerName;
    //@Field("skype")
    //private String skype;

    public Seller() {
    }

    public Seller(String login,String email, String password, String confirm) {
        this.login = login;
        this.password = password;
        this.confirm = confirm;
       // this.companyName = companyName;
          this.email = email;
//        this.managerName = managerName;
//        this.skype = skype;
//        this.password = password;
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

//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

//    public String getManagerName() {
//        return managerName;
//    }
//
//    public void setManagerName(String managerName) {
//        this.managerName = managerName;
//    }
//
//    public String getSkype() {
//        return skype;
//    }
//
//    public void setSkype(String skype) {
//        this.skype = skype;
//    }

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
}
