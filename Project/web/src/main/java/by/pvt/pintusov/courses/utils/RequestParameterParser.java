package by.pvt.pintusov.courses.utils;

import by.pvt.pintusov.courses.commands.factory.CommandType;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Request parameter parser
 * @author pintusov
 * @version 1.0
 */

public class RequestParameterParser {

	private RequestParameterParser() {}

	/**
	 * Get command type
	 * @param request - used for getting attribute
	 * @return command type
	 */
	public static CommandType getCommandType(HttpServletRequest request) {
		String commandName = request.getParameter(Parameters.COMMAND);
		CommandType commandType = CommandType.LOGIN;
		if (commandName != null) {
			commandType = CommandType.valueOf(commandName.toUpperCase());
		}
		return commandType;
	}

	/**
	 * Get User
	 * @param request - used for creating instance
	 * @return user instance
	 */
	public static User getUser(HttpServletRequest request) {
		int accessType = 0;
		if (request.getParameter(Parameters.USER_ACCESS_TYPE) != null) {
			accessType = Integer.valueOf(request.getParameter(Parameters.USER_ACCESS_TYPE));
		}
		String firstName = request.getParameter(Parameters.USER_FIRST_NAME);
		String lastName = request.getParameter(Parameters.USER_LAST_NAME);
		String login = request.getParameter(Parameters.USER_LOGIN);
		String password = request.getParameter(Parameters.USER_PASSWORD);
		User user = EntityBuilder.buildUser(firstName, lastName, login, password, accessType);
		return user;
	}

	/**
	 * Get Course
	 * @param request - used for getting attribute
	 * @return course instance
	 */
	public static Course getCourse(HttpServletRequest request) {
		String name = request.getParameter(Parameters.COURSE_NAME);
		int hours = 0;
		if (request.getParameter(Parameters.COURSE_HOURS) != null) {
			hours = Integer.valueOf(request.getParameter(Parameters.COURSE_HOURS));
		}

		int status = 0;
		if (request.getParameter(Parameters.COURSE_STATUS) != null) {
			status = Integer.valueOf(request.getParameter(Parameters.COURSE_STATUS));
		}

		Course course = EntityBuilder.buildCourse(name, hours, status);
		return course;
	}

	/**
	 * Get User type
	 * @param request - used for getting attribute
	 * @return course instance
	 */
	public static UserType getUserType(HttpServletRequest request) {
		return (UserType) request.getSession().getAttribute(Parameters.USER_TYPE);
	}

	/**
	 * Get Courses list
	 * @param request - used for getting attribute
	 * @return course instance
	 */
	public static List<Course> getCoursesList(HttpServletRequest request) {
		return (List<Course>) request.getSession().getAttribute(Parameters.COURSES_LIST);
	}

	/**
	 * Get record users
	 * @param request - used for getting attribute
	 * @return course instance
	 */
	public static User getRecordUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(Parameters.USER);
	}
}
