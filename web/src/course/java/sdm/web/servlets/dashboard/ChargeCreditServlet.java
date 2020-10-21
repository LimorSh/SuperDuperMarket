package course.java.sdm.web.servlets.dashboard;

import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@WebServlet(name = "ChargeCreditServlet", urlPatterns = {"/pages/dashboard/chargeCredit"})
public class ChargeCreditServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String usernameFromSession = SessionUtils.getUsername(request);
        AccountManager accountManager = ServletUtils.getAccountManager(getServletContext());

        if (usernameFromSession != null) {
            String creditStr = request.getParameter(Constants.CREDIT_PARAM_KEY);
            int credit = Integer.parseInt(creditStr);

            String dateFromParameter = request.getParameter(Constants.DATE_PARAM_KEY);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateFromParameter);
            accountManager.chargeCreditForUser(usernameFromSession, date, credit);
            String msg = String.format("$%d were added to your account successfully.", credit);
            response.getWriter().print(msg);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            processRequest(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            processRequest(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
