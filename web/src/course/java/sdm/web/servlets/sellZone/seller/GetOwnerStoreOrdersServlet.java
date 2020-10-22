package course.java.sdm.web.servlets.sellZone.seller;

import com.google.gson.Gson;
import course.java.sdm.engine.dto.OrderDto;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

//@WebServlet(name = "GetOwnerStoreOrdersServlet", urlPatterns = {"/getOwnerStoreOrders"})
public class GetOwnerStoreOrdersServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        int storeId = Integer.parseInt(request.getParameter(Constants.STORE_ID_PARAM_KEY));
        String zoneNameFromSession = SessionUtils.getZoneName(request);
        String usernameFromSession = SessionUtils.getUsername(request);

//        try (PrintWriter out = response.getWriter()) {
//            Gson gson = new Gson();
//            Collection<OrderDto> orders =
//                    businessLogic.getOrderHistory(zoneNameFromSession, usernameFromSession);
//            if (orders.isEmpty()) {
//                out.write(Constants.EMPTY_JSON_RESPONSE);
//            }
//            else {
//                Collection<OrderDto> ordersSortedById
//                        = orders.stream().sorted
//                        (Comparator.comparing(OrderDto::getId))
//                        .collect(Collectors.toList());
//                String json = gson.toJson(ordersSortedById);
//                System.out.println(json);
//                out.println(json);
//            }
//            out.flush();
//        }
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
