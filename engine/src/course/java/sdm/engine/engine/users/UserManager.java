package course.java.sdm.engine.engine.users;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
Adding and retrieving users is synchronized and in that manner - these actions are thread safe
Note that asking if a user exists (isUserExists) does not participate in the synchronization and it is the responsibility
of the user of this class to handle the synchronization of isUserExists with other methods here on it's own
 */
public class UserManager {


    private final Set<User> users;

    public UserManager() {
        users = new HashSet<>();
    }

    public synchronized void addUser(String username, String userType) {
        User user = new User(username, userType);
        users.add(user);
    }

    public synchronized void removeUser(String username) {
        users.removeIf(user -> user.getName().equalsIgnoreCase(username));
    }

    public synchronized Set<User> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    public boolean isUserExists(String username) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public String getCustomerUserTypeStr() {
        return User.getCustomerUserTypeStr();
    }

    public String getSellerUserTypeStr() {
        return User.getSellerUserTypeStr();
    }
}
