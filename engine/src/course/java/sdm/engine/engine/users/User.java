package course.java.sdm.engine.engine.users;

import course.java.sdm.engine.Constants;

import java.util.Objects;

public class User {

    public enum UserType {
        CUSTOMER(Constants.USER_TYPE_CUSTOMER_STR),
        SELLER(Constants.USER_TYPE_SELLER_STR),
        ;

        private final String userTypeStr;

        UserType(String userType) {
            this.userTypeStr = userType;
        }

        public String getUserTypeStr() {
            return userTypeStr;
        }
    }

    private static int numUsers = 1;
    private final int id;
    private final String name;
    private final UserType userType;

    public User(String name, String userType) {
        this.id = numUsers;
        this.name = name;
        this.userType = convertStringToUserType(userType);
        numUsers++;
    }

    public static User.UserType convertStringToUserType(String userType) {
        if (userType.toLowerCase().contains(Constants.USER_TYPE_CUSTOMER_STR)) {
            return UserType.CUSTOMER;
        }
        return UserType.SELLER;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
