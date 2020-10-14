package course.java.sdm.web.servlets.dashboard;

import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//@WebServlet(name = "ChargeCreditServlet", urlPatterns = {"/pages/dashboard/chargeCredit"})
public class ChargeCreditServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usernameFromSession = SessionUtils.getUsername(request);
        AccountManager accountManager = ServletUtils.getAccountManager(getServletContext());

        if (usernameFromSession != null) {
            String creditStr = request.getParameter(Constants.CREDIT_PARAM_KEY);
            int credit = Integer.parseInt(creditStr);

            Date date = new Date();
            accountManager.chargeCreditForUser(usernameFromSession, date, credit);
            String msg = String.format("$%d were added to your account successfully.", credit);
            response.getWriter().print(msg);
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
