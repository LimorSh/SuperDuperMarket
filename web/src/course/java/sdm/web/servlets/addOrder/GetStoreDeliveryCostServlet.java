package course.java.sdm.web.servlets.addOrder;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "GetStoreDeliveryCostServlet", urlPatterns = {"/setStoreDeliveryCost"})
public class GetStoreDeliveryCostServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        String zoneNameFromSession = SessionUtils.getZoneName(request);
        int storeId = Integer.parseInt(request.getParameter(Constants.STORE_ID_PARAM_KEY));
        int locationX = Integer.parseInt(request.getParameter(Constants.LOCATION_X_PARAM_KEY));
        int locationY = Integer.parseInt(request.getParameter(Constants.LOCATION_Y_PARAM_KEY));

        float storeDeliveryCost = businessLogic.getStoreDeliveryCost(
                zoneNameFromSession, storeId, locationX, locationY);

        response.getWriter().print(ServletUtils.roundNumberWithTwoDigitsAfterPoint(storeDeliveryCost));
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
