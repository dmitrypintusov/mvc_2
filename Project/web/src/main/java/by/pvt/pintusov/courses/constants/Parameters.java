package by.pvt.pintusov.courses.constants;

/**
 * Parameters
 * @author pintusov
 * @version 1.0
 */

public class Parameters {
	//user parameters
	public static final String USER = "user";
	public static final String USER_FIRST_NAME = "firstname";
	public static final String USER_LAST_NAME = "lastname";
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_ACCESS_LEVEL = "accessLevel";
	public static final String USERS_LIST = "userList";
	//course parameters
	public static final String COURSES_LIST = "coursesList";
	//operation parameters
	public static final String OPERATION_MESSAGE = "operationMessage";

	//Command and errors parameters
	public static final String COMMAND = "command";
	public static final String ERROR_DATABASE = "errorDatabase";
	public static final String ERROR_LOGIN_OR_PASSWORD = "errorLoginOrPassword";
	public static final String ERROR_USER_EXISTS = "errorUserExists";

	public static final String NUMBER_OF_PAGES = "numberOfPages";
	public static final String CURRENT_PAGE = "currentPage";
	public static final String RECORDS_PER_PAGE = "recordsPerPage";
	public static final String ORDERING = "ordering";
	public static final String ORDER_BY_ID = "ORDER BY id";
	public static final String ORDER_BY_COURSE_NAME = "ORDER BY courseName";
	public static final String ORDER_BY_COURSE_STATUS = "ORDER BY courseStatus";
	public static final String ORDER_BY_HOURS = "ORDER BY hours";
	public static final String ORDER_BY_START_DATE = "ORDER BY startDate";
	public static final String ORDER_BY_UPDATE_DATE = "ORDER BY updateDate";

	private Parameters () {}
}
