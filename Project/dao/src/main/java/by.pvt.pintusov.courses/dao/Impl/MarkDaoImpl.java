package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.IMarkDao;
import by.pvt.pintusov.courses.pojos.Mark;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * Project: courses
 * Created by: USER
 * Date: 23.01.17.
 */
public class MarkDaoImpl extends AbstractDao<Mark> implements IMarkDao {
	private static Logger logger = Logger.getLogger(MarkDaoImpl.class);
	private static MarkDaoImpl instance;

	private MarkDaoImpl(SessionFactory sessionFactory){
		super(Mark.class, sessionFactory);
	}

	public static synchronized MarkDaoImpl getInstance(SessionFactory sessionFactory){
		if(instance == null){
			instance = new MarkDaoImpl(sessionFactory);
		}
		return instance;
	}
}
