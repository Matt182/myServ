package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 26.12.2015.
 */
public class AccountService {
    private final Map<String,UserProfile> loginToProfile;
    private final Map<String,UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile usr){

        loginToProfile.put(usr.GetLogin(), usr);
        sessionIdToProfile.put(usr.GetLogin(), usr);
    }

    public UserProfile getUser(String login){
        return loginToProfile.get(login);
    }

    public boolean userExist(String login){
        return this.loginToProfile.containsKey(login);
    }

}
