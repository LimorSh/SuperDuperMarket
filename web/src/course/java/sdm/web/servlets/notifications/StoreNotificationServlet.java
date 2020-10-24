package course.java.sdm.web.servlets.notifications;

import com.google.gson.Gson;
import course.java.sdm.engine.engine.notifications.NotificationManager;
import course.java.sdm.engine.engine.notifications.StoreNotification;
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

//@WebServlet(name = "StoreNotificationServlet", urlPatterns = {"/storeNotifications"})
public class StoreNotificationServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("application/json");
        NotificationManager notificationManager = ServletUtils.getNotificationManager(getServletContext());
        String username = SessionUtils.getUsername(request);

        int versionFromUserParameter = ServletUtils.getIntParameter(request, Constants.STORE_NOTIFICATION_VERSION_PARAMETER);
        if (versionFromUserParameter == Constants.INT_PARAMETER_ERROR) {
            return;
        }

        int storeNotificationsVersion;
        List<StoreNotification> storeNotifications;
        List<StoreNotification> storeNotificationsWithoutCurrentUser;
        synchronized (getServletContext()) {
            storeNotificationsVersion = notificationManager.getStoreNotificationsVersion();
            storeNotifications = notificationManager.getStoreNotifications(versionFromUserParameter);
            storeNotificationsWithoutCurrentUser = storeNotifications.stream()
                    .filter(storeNotification -> {
                        assert username != null;
                        return storeNotification.isUserZoneOwnerAndNotStoreOwner(username);
                    })
                    .collect(Collectors.toList());
        }

        NewStoreNotifications newStoreNotifications =
                new NewStoreNotifications(storeNotificationsWithoutCurrentUser, storeNotificationsVersion);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(newStoreNotifications);
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse);
            out.flush();
        }
    }

    private static class NewStoreNotifications {

        final private List<StoreNotification> entries;
        final private int version;

        public NewStoreNotifications(List<StoreNotification> entries, int version) {
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
