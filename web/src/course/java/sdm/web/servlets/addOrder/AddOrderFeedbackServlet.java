package course.java.sdm.web.servlets.addOrder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

//@WebServlet(name = "AddOrderFeedbackServlet", urlPatterns = {"/addOrderFeedback"})
public class AddOrderFeedbackServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String zoneNameFromSession = SessionUtils.getZoneName(request);
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());

        String orderIdFromParameter = request.getParameter(Constants.ORDER_ID_PARAM_KEY);
        int orderId = Integer.parseInt(orderIdFromParameter);

        String storesAndRatesFromParameter = request.getParameter(Constants.ORDER_STORES_AND_RATES_PARAM_KEY);
        JsonObject storesAndRatesJson = new JsonParser().parse(storesAndRatesFromParameter).getAsJsonObject();
        Map<Integer, ArrayList<String>> storesAndRates = new HashMap<>();
        storesAndRatesJson.entrySet().forEach( storeRateAndFeedbackEntry -> {
            int storeId = Integer.parseInt(storeRateAndFeedbackEntry.getKey());
            JsonObject rateAndFeedback = storeRateAndFeedbackEntry.getValue().getAsJsonObject();
            ArrayList<String> storeRateDetails = new ArrayList<>();
            rateAndFeedback.entrySet().forEach( entry -> {
                String val =  entry.getValue().getAsString();
                storeRateDetails.add(val);
            });
            storesAndRates.put(storeId, storeRateDetails);
        });

        synchronized (this) {
            businessLogic.addOrderFeedback(zoneNameFromSession, orderId, storesAndRates);
        }
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
