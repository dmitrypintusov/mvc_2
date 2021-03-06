package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.DaoConstants;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.IUserDao;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User Dao implementation
 * @author pintusov
 * @version 1.1
 */

public class UserDaoImpl extends AbstractDao <User> implements IUserDao {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	private static UserDaoImpl instance;
	private Class persistenceClass = User.class;

	private UserDaoImpl(){
		super(User.class);
	}

	public static synchronized UserDaoImpl getInstance(){
		if(instance == null){
			instance = new UserDaoImpl();
		}
		return instance;
	}

	@Override
	public User getUserByLogin(String login) throws DaoException {
		User user = null;
		try {
			Session session = util.getSession();
			Query query = session.createQuery(DaoConstants.HQL_GET_BY_LOGIN);
			query.setParameter(DaoConstants.PARAMETER_USER_LOGIN, login);
			user = (User) query.uniqueResult();
		} catch(HibernateException e){
			logger.error(DaoConstants.ERROR_USER_BY_LOGIN + e);
			throw new DaoException(DaoConstants.ERROR_USER_BY_LOGIN, e);
		}
		return user;
	}

	@Override
	public boolean isAuthorized(String login, String password) throws DaoException {
		boolean isLogIn = false;
		try {
			Session session = util.getSession();
			Query query = session.createQuery(DaoConstants.HQL_CHECK_AUTHORIZATION);
			query.setParameter(DaoConstants.PARAMETER_USER_LOGIN, login);
			query.setParameter(DaoConstants.PARAMETER_USER_PASSWORD, password);
			query.setCacheable(true);
			if(query.uniqueResult() != null){
				isLogIn = true;
			}
		}
		catch(HibernateException e){
			logger.error(DaoConstants.ERROR_USER_AUTHORIZATION + e);
			throw new DaoException(DaoConstants.ERROR_USER_AUTHORIZATION, e);
		}
		return isLogIn;
	}

	public List<User> getAll() throws DaoException {
		List<User> results;
		try {
			Session session = util.getSession();
			Query query = session.createQuery(DaoConstants.HQL_GET_ALL_USERS);
			query.setFirstResult(0);
			query.setMaxResults(2);
			results = query.list();
			logger.info(results);
		}
		catch(HibernateException e){
			logger.error(DaoConstants.ERROR_USERS_LIST + e);
			throw new DaoException(DaoConstants.ERROR_USERS_LIST, e);
		}
		return results;
	}

	//TODO: переделать
	public AccessLevelType checkUserAccessLevel (User user) throws DaoException {
		AccessLevelType access;
		AccessLevel accessLevel = null;
		Set<AccessLevel> accessLevels;
		try {
			Session session = util.getSession();
			Set <User> users = new HashSet<>();
			users.add(user);
			accessLevel.setUsers(users);
			Query query = session.createQuery(DaoConstants.HQL_CHECK_USER_ACCESS);
			query.setParameter(DaoConstants.PARAMETER_ACCESS_LEVEL_TYPE, users);
			access = (AccessLevelType) query.uniqueResult();
		} catch(HibernateException e){
			logger.error(DaoConstants.ERROR_ACCESS_LEVEL_TYPE + e);
			throw new DaoException(DaoConstants.ERROR_ACCESS_LEVEL_TYPE, e);
		}
		return access;
	}
}
