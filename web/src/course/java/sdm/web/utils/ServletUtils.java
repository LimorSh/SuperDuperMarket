package course.java.sdm.web.utils;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.engine.engine.users.UserManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static course.java.sdm.web.constants.Constants.INT_PARAMETER_ERROR;

public class ServletUtils {

	private static final String USER_MANAGER_ATTRIBUTE_NAME = "userManager";
	private static final String ACCOUNT_MANAGER_ATTRIBUTE_NAME = "accountManager";
	private static final String BUSINESS_LOGIC_ATTRIBUTE_NAME = "businessLogic";

	private static final Object userManagerLock = new Object();
	private static final Object accountManagerLock = new Object();
	private static final Object businessLogicLock = new Object();

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
}
