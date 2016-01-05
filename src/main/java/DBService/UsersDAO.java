package DBService;

import main.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by matt on 03.01.2016.
 */
public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserProfile get(long id) {

        return (UserProfile) session.get(UserProfile.class, id);
    }
    public UserProfile get(String name) {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return (UserProfile) criteria.add(Restrictions.eq("login", name)).uniqueResult();
    }

    public long insertUser(String login) {
        return (Long) session.save(new UserProfile(login));
    }
    public long insertUser(String login, String pass) {
        return (Long) session.save(new UserProfile(login, pass));
    }
}
