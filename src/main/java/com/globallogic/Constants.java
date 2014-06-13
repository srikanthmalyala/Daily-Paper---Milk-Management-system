package com.globallogic;

/**
 * Constant values used throughout the application.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class Constants {

    public static String USER_LIST = "userList";

    private Constants() {
        // hide me
    }
    //~ Static fields/initializers =============================================
    /**
     *
     *
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";
    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");
    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";
    /**
     * Session scope attribute that holds the locale set by the user. By setting
     * this key to the same one that Struts uses, we get synchronization in
     * Struts w/o having to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";
    /**
     * The request scope attribute that holds the leave list
     */
    public static final String BLOCK_LIST = "blockList";
    public static final String REPORT_LIST = "reportList";
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";
    /**
     * The name of the user's role list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";
    /**
     * The name of the available roles list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";
    /**
     * The name of the CSS Theme setting.
     */
    public static String CUSTOMER_LIST = "customerList";
    public static final String CSS_THEME = "csstheme";
    public static final float SUNDAY = 1.0f;
    public static final float MONDAY = 1.0f;
    public static final float TUESDAY = 1.0f;
    public static final float WENESDAY = 1.0f;
    public static final float THURSDAY = 1.0f;
    public static final float FRIDAY = 1.0f;
    public static final float SATURDAY = 1.0f;
}
