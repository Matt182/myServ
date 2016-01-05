package main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by matt on 26.12.2015.
 */
@Entity
@Table(name = "users")
public class UserProfile implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    public UserProfile(String login, String password){
        this.setId(-1);
        this.login = login;
        this.password = password;
    }

    public UserProfile(String login){
        this.setId(-1);
        this.login = login;
        this.password = login;
    }
    public UserProfile(){

    }

    public void setId(long id){
        this.id = id;
    }

    public String GetLogin(){
        return login;
    }
    public String GetPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "UserDataSet{ id=" + id + ", login=" + login + " }";
    }
}
