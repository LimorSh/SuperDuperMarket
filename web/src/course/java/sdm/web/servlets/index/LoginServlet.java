package course.java.sdm.web.servlets.index;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.engine.engine.users.UserManager;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usernameFromSession = SessionUtils.getUsername(request);
        UserManager userManager = ServletUtils.getUserManager(getServletContext());
        AccountManager accountManager = ServletUtils.getAccountManager(getServletContext());
        if (usernameFromSession == null) {
            String usernameFromParameter = request.getParameter(Constants.USERNAME_PARAM_KEY);
            String userTypeFromParameter = request.getParameter(Constants.USERTYPE_PARAM_KEY);
            if (usernameFromParameter == null || usernameFromParameter.isEmpty()) {
                String errorMessage = "Username must contains at least one letter.";
                response.getWriter().print(errorMessage);
            } else {
                usernameFromParameter = usernameFromParameter.trim();

                synchronized (this) {
                    if (userManager.isUserExists(usernameFromParameter)) {
                        String errorMessage = "Username " + usernameFromParameter + " already exists. Please enter a different username.";
                        response.getWriter().print(errorMessage);
                    } else {
                        userManager.addUser(usernameFromParameter, userTypeFromParameter);
                        request.getSession(true).setAttribute(Constants.USERNAME_PARAM_KEY, usernameFromParameter);
                        accountManager.addAccount(usernameFromParameter);
                        response.getWriter().print("");
                    }
                }
            }
        } else {
            // user already logged in
            response.getWriter().print("");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
}
