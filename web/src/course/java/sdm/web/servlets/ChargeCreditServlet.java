package course.java.sdm.web.servlets;

import course.java.sdm.engine.engine.users.UserManager;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "ChargeCreditServlet", urlPatterns = {"/pages/dashboard/chargeCredit"})
public class ChargeCreditServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usernameFromSession = SessionUtils.getUsername(request);
        UserManager userManager = ServletUtils.getUserManager(getServletContext());

        String creditStr = request.getParameter(Constants.CREDIT);
        float credit = Float.parseFloat(creditStr);

        synchronized (this) {

        }

//        synchronized (this) {
//            if (userManager.isUserExists(usernameFromParameter)) {
//                String errorMessage = "Username " + usernameFromParameter + " already exists. Please enter a different username.";
//                response.getWriter().print(errorMessage);
//            } else {
//                userManager.addUser(usernameFromParameter, userTypeFromParameter);
//                request.getSession(true).setAttribute(Constants.USERNAME, usernameFromParameter);
//                response.getWriter().print("");
//            }
//        }
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
