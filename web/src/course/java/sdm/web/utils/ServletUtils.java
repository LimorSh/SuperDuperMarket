package course.java.sdm.web.utils;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.engine.engine.chatroom.ChatManager;
import course.java.sdm.engine.engine.notifications.NotificationManager;
import course.java.sdm.engine.engine.users.UserManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static course.java.sdm.web.constants.Constants.INT_PARAMETER_ERROR;

public class ServletUtils {

	private static final String USER_MANAGER_ATTRIBUTE_NAME = "userManager";
	private static final String ACCOUNT_MANAGER_ATTRIBUTE_NAME = "accountManager";
	private static final String BUSINESS_LOGIC_ATTRIBUTE_NAME = "businessLogic";
	private static final String NOTIFICATION_MANAGER_ATTRIBUTE_NAME = "notificationManager";
	private static final String CHAT_MANAGER_ATTRIBUTE_NAME = "chatManager";

	private static final Object userManagerLock = new Object();
	private static final Object accountManagerLock = new Object();
	private static final Object businessLogicLock = new Object();
	private static final Object NotificationManagerLock = new Object();
	private static final Object chatManagerLock = new Object();

	public static UserManager getUserManager(ServletContext servletContext) {

		synchronized (userManagerLock) {
			if (servletContext.getAttribute(USER_MANAGER_ATTRIBUTE_NAME) == null) {
				servletContext.setAttribute(USER_MANAGER_ATTRIBUTE_NAME, new UserManager());
			}
		}
		return (UserManager) servletContext.getAttribute(USER_MANAGER_ATTRIBUTE_NAME);
	}

	public static AccountManager getAccountManager(ServletContext servletContext) {

		synchronized (accountManagerLock) {
			if (servletContext.getAttribute(ACCOUNT_MANAGER_ATTRIBUTE_NAME) == null) {
				servletContext.setAttribute(ACCOUNT_MANAGER_ATTRIBUTE_NAME, new AccountManager());
			}
		}
		return (AccountManager) servletContext.getAttribute(ACCOUNT_MANAGER_ATTRIBUTE_NAME);
	}

	public static BusinessLogic getBusinessLogic(ServletContext servletContext) {

		synchronized (businessLogicLock) {
			if (servletContext.getAttribute(BUSINESS_LOGIC_ATTRIBUTE_NAME) == null) {
				servletContext.setAttribute(BUSINESS_LOGIC_ATTRIBUTE_NAME, new BusinessLogic());
			}
		}
		return (BusinessLogic) servletContext.getAttribute(BUSINESS_LOGIC_ATTRIBUTE_NAME);
	}

	public static NotificationManager getNotificationManager(ServletContext servletContext) {
		synchronized (NotificationManagerLock) {
			if (servletContext.getAttribute(NOTIFICATION_MANAGER_ATTRIBUTE_NAME) == null) {
				servletContext.setAttribute(NOTIFICATION_MANAGER_ATTRIBUTE_NAME, new NotificationManager());
			}
		}
		return (NotificationManager) servletContext.getAttribute(NOTIFICATION_MANAGER_ATTRIBUTE_NAME);
	}

	public static ChatManager getChatManager(ServletContext servletContext) {
		synchronized (chatManagerLock) {
			if (servletContext.getAttribute(CHAT_MANAGER_ATTRIBUTE_NAME) == null) {
				servletContext.setAttribute(CHAT_MANAGER_ATTRIBUTE_NAME, new ChatManager());
			}
		}
		return (ChatManager) servletContext.getAttribute(CHAT_MANAGER_ATTRIBUTE_NAME);
	}

	public static int getIntParameter(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException numberFormatException) {
			}
		}
		return INT_PARAMETER_ERROR;
	}

	public static float roundNumberWithTwoDigitsAfterPoint(float number) {
		try {
			return Float.parseFloat(String.format("%.2f", number));
		} catch (Exception ignore) {
		}
		return -1f;
	}

	public static double roundNumberWithTwoDigitsAfterPoint(double number) {
		try {
			return Double.parseDouble(String.format("%.2f", number));
		} catch (Exception ignore) {
		}
		return -1f;
	}
}
