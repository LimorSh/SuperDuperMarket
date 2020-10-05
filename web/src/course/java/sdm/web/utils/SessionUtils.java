package course.java.sdm.web.utils;


import course.java.sdm.web.constants.Constants;


public class SessionUtils {

    public static String getUsername (HttpServletRequest request) {
            HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute(Constants.USERNAME) : null;
        return sessionAttribute != null ? sessionAttribute.toString() : null;
    }

//    public static void clearSession (HttpServletRequest request) {
//        request.getSession().invalidate();
//    }
}
