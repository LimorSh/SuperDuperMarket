package course.java.sdm.web.servlets.sellZone;

import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "AddOrderServlet", urlPatterns = {"/pages/sellZone/addOrder"})
public class AddOrderServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String zoneNameFromSession = SessionUtils.getZoneName(request);
        String usernameFromSession = SessionUtils.getUsername(request);

        String dateFromParameter = request.getParameter(Constants.DATE_PARAM_KEY);
        String locationXFromParameter = request.getParameter(Constants.LOCATION_X_PARAM_KEY);
        String locationYFromParameter = request.getParameter(Constants.LOCATION_Y_PARAM_KEY);
        String orderCategoryFromParameter = request.getParameter(Constants.ORDER_CATEGORY_PARAM_KEY);
        String res_str = "date: " + dateFromParameter + " Location: (" + locationXFromParameter + ","
                + locationYFromParameter + ") Order Category: " +  orderCategoryFromParameter;
        response.getWriter().print(res_str);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
