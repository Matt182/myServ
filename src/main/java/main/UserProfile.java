package main;

/**
 * Created by matt on 26.12.2015.
 */
public class UserProfile {
    private String login;
    private String password;

    public UserProfile(String login, String password){
        this.login = login;
        this.password = password;
    }

    public UserProfile(String login){
        this.login = login;
        this.password = login;
    }

    public String GetLogin(){
        return login;
    }
    public String GetPassword(){
        return password;
    }
}
