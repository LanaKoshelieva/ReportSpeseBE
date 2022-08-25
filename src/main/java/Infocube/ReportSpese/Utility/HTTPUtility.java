package Infocube.ReportSpese.Utility;

public class HTTPUtility
{
    public static final String CONTROLLER_ROOT = "/my_expenses_manager/api";

    public static final String CONTROLLER_HOME = "/home";
    public static final String CONTROLLER_LOGIN = "/login";
    public static final String CONTROLLER_CREATE_PROFILE = "/registration";

    public static final String CONTROLLER_UPDATE_PROFILE = "/user/update";
    public static final String CONTROLLER_DELETE_PROFILE = "/user/delete/{id}";
    public static final String CONTROLLER_USERS = "/users";
    public static final String CONTROLLER_GET_USER = "/user/{id}";

    public static final String CONTROLLER_ARCHIVE = "/archive";
    public static final String CONTROLLER_ALL_RECEIPTS = "/{id}/my_receipts";
    public static final String CONTROLLER_NEW_RECEIPT = "/receipt/create";
    public static final String CONTROLLER_DELETE_RECEIPT = "/receipt/delete/{id}";
    public static final String CONTROLLER_UPDATE_RECEIPT = "/receipt/update";
    public static final String CONTROLLER_GET_RECEIPT = "/receipt/{id}";

    public static final String CONTROLLER_ARCHIVE_HI_PR = "/receipt/archive/highest-price";
    public static final String CONTROLLER_ARCHIVE_LO_PR = "/receipt/archive/lowest-price";
    public static final String CONTROLLER_ARCHIVE_NEWEST = "/receipt/archive/newest";
    public static final String CONTROLLER_ARCHIVE_OLDEST = "/receipt/archive/oldest";

    //TODO:
    public static final String CONTROLLER_USER = "/user";
    public static final String CONTROLLER_RECEIPT = "/receipt";

    public static final String CONTROLLER_GET = "" ;
    public static final String CONTROLLER_DELETE = "/delete/{id}";
    public static final String CONTROLLER_UPDATE = "/update";
    public static final String CONTROLLER_LIST = "";



}
