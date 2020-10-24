package course.java.sdm.web.servlets.notifications;

import com.google.gson.Gson;
import course.java.sdm.engine.engine.notifications.NotificationManager;
import course.java.sdm.engine.engine.notifications.StoreFeedbackNotification;
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

//@WebServlet(name = "StoreFeedbackNotificationServlet", urlPatterns = {"/storeFeedbackNotifications"})
public class StoreFeedbackNotificationServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("application/json");
        NotificationManager notificationManager = ServletUtils.getNotificationManager(getServletContext());
        String username = SessionUtils.getUsername(request);

        int versionFromUserParameter = ServletUtils.getIntParameter(request, Constants.STORE_FEEDBACK_NOTIFICATION_VERSION_PARAMETER);
        if (versionFromUserParameter == Constants.INT_PARAMETER_ERROR) {
            return;
        }

        int storeFeedbackNotificationsVersion;
        List<StoreFeedbackNotification> storeFeedbackNotifications;
        List<StoreFeedbackNotification> storeFeedbackNotificationsWithoutCurrentUser;
        synchronized (getServletContext()) {
            storeFeedbackNotificationsVersion = notificationManager.getStoreFeedbackNotificationsVersion();
            storeFeedbackNotifications = notificationManager.getStoreFeedbackNotifications(versionFromUserParameter);
            storeFeedbackNotificationsWithoutCurrentUser = storeFeedbackNotifications.stream()
                    .filter(storeFeedbackNotification -> storeFeedbackNotification.getStoreOwnerName().equalsIgnoreCase(username))
                    .collect(Collectors.toList());
        }

        NewStoreFeedbackNotifications newStoreNotifications =
                new NewStoreFeedbackNotifications(storeFeedbackNotificationsWithoutCurrentUser, storeFeedbackNotificationsVersion);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(newStoreNotifications);
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse);
            out.flush();
        }
    }

    private static class NewStoreFeedbackNotifications {

        final private List<StoreFeedbackNotification> entries;
        final private int version;

        public NewStoreFeedbackNotifications(List<StoreFeedbackNotification> entries, int version) {
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
