package course.java.sdm.web.servlets.orderHistory;

import com.google.gson.Gson;
import course.java.sdm.engine.dto.OrderDto;
import course.java.sdm.engine.engine.BusinessLogic;
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

//@WebServlet(name = "GetOrderHistoryServlet", urlPatterns = {"/getOrderHistory"})
public class GetOrderHistoryServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        String zoneNameFromSession = SessionUtils.getZoneName(request);
        String usernameFromSession = SessionUtils.getUsername(request);

        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Collection<OrderDto> orders =
                    businessLogic.getOrderHistory(zoneNameFromSession, usernameFromSession);
            Collection<OrderDto> ordersSortedById
                    = orders.stream().sorted
                    (Comparator.comparing(OrderDto::getId))
                    .collect(Collectors.toList());
            String json = gson.toJson(ordersSortedById);
            System.out.println(json);
            out.println(json);
            out.flush();
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
