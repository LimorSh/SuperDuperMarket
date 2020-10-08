package course.java.sdm.engine.engine.users;

import course.java.sdm.engine.Constants;

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

    public String getUserType() {
        return userType.userTypeStr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
