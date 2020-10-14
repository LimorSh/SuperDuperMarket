package course.java.sdm.web.servlets.addOrder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import course.java.sdm.web.constants.Constants;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "AddOrderServlet", urlPatterns = {"/pages/sellZone/addOrder/createNewOrder"})
public class AddOrderServlet extends HttpServlet {

    public static final String STATIC_ORDER_CATEGORY_STR = "static";

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
//        String zoneNameFromSession = SessionUtils.getZoneName(request);
//        String usernameFromSession = SessionUtils.getUsername(request);
//
//        String dateFromParameter = request.getParameter(Constants.DATE_PARAM_KEY);
//        String locationXFromParameter = request.getParameter(Constants.LOCATION_X_PARAM_KEY);
//        String locationYFromParameter = request.getParameter(Constants.LOCATION_Y_PARAM_KEY);
        String itemsAndQuantitiesFromParameter = request.getParameter(Constants.ITEMS_AND_QUANTITIES_PARAM_KEY);
        JsonObject itemsAndQuantitiesJson = new JsonParser().parse(itemsAndQuantitiesFromParameter).getAsJsonObject();
        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();
        JsonArray itemsAndQuantitiesJsonArr = itemsAndQuantitiesJson.getAsJsonArray();
//        for (int i = 0; i < itemsAndQuantitiesJsonArr.size(); i++) {
//            String str = itemsAndQuantitiesJsonArr.getJ[i];
//        }


        //        String orderCategoryFromParameter = request.getParameter(Constants.CHOSEN_ORDER_CATEGORY_PARAM_KEY);
//        if (orderCategoryFromParameter.equals(STATIC_ORDER_CATEGORY_STR)) {
////            String storeIdFromParameter = request.getParameter(Constants.STORE_ID_PARAM_KEY);
//        }
//
//        String storeIdFromParameter = request.getParameter(Constants.STORE_ID_PARAM_KEY);
//        String res_str = "storeId: " + storeIdFromParameter + " date: " + dateFromParameter + " Location: (" + locationXFromParameter + ","
//                + locationYFromParameter + ") Order Category: " +  orderCategoryFromParameter;
////                " itemsAndQuantities: " + itemsAndQuantitiesFromParameter;
//        response.getWriter().print(res_str);
        response.getWriter().print("success: " + itemsAndQuantitiesFromParameter);
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
