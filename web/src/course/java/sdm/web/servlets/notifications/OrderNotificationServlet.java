package course.java.sdm.web.servlets.notifications;

import com.google.gson.Gson;
import course.java.sdm.engine.engine.notifications.NotificationManager;
import course.java.sdm.engine.engine.notifications.OrderNotification;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

//@WebServlet(name = "OrderNotificationServlet", urlPatterns = {"/orderNotifications"})
public class OrderNotificationServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("application/json");
        NotificationManager notificationManager = ServletUtils.getNotificationManager(getServletContext());
        String username = SessionUtils.getUsername(request);

        int versionFromUserParameter = ServletUtils.getIntParameter(request, Constants.ORDER_NOTIFICATION_VERSION_PARAMETER);
        if (versionFromUserParameter == Constants.INT_PARAMETER_ERROR) {
            return;
        }

        int orderNotificationsVersion;
        List<OrderNotification> orderNotifications;
        List<OrderNotification> orderNotificationsFilteredByCurrentUser;
        synchronized (getServletContext()) {
            orderNotificationsVersion = notificationManager.getOrderNotificationsVersion();
            orderNotifications = notificationManager.getOrderNotifications(versionFromUserParameter);
            orderNotificationsFilteredByCurrentUser = orderNotifications.stream()
                    .filter(orderNotification ->
                            orderNotification.getStoreOwnerName().equalsIgnoreCase(username))
                    .collect(Collectors.toList());
        }

        NewOrderNotifications newOrderNotifications =
                new NewOrderNotifications(orderNotificationsFilteredByCurrentUser, orderNotificationsVersion);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(newOrderNotifications);
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse);
            out.flush();
        }
    }

    private static class NewOrderNotifications {

        final private List<OrderNotification> entries;
        final private int version;

        public NewOrderNotifications(List<OrderNotification> entries, int version) {
            this.entries = entries;
            this.version = version;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
