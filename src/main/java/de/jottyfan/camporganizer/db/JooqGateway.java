package de.jottyfan.camporganizer.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

/**
 * 
 * @author jotty
 *
 */
public abstract class JooqGateway {

	private final static Logger LOGGER = LogManager.getLogger(JooqGateway.class);
	private final DSLContext context;

	/**
	 * generate a new instance of the gateway
	 * 
	 * @param facesContext
	 *            to be used for messages
	 * @throws DataAccessException
	 */
	public JooqGateway(FacesContext facesContext) throws DataAccessException {
		if (facesContext == null) {
			throw new DataAccessException("facesContext must not be null");
		}
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream("/etc/camporganizer.properties"));
		} catch (FileNotFoundException e) {
			LOGGER.warn("no configuration file found: {}", e.getMessage());
		} catch (IOException e) {
			LOGGER.warn("error on loading configuration: {}", e.getMessage());
		}
		try {
			String url = properties.getProperty("url", "jdbc:postgresql:camp");
			context = DSL.using(DriverManager.getConnection(url), SQLDialect.POSTGRES_9_5);
		} catch (SQLException e) {
			facesContext.addMessage("no database connection", new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"could not establish database connection", e.getMessage()));
			LOGGER.fatal("error on creating database access: {}", e.getMessage());
			throw new DataAccessException(e.getMessage());
		}
	}

	/**
	 * get the jooq context
	 * 
	 * @return the jooq context
	 */
	public DSLContext getJooq() {
		return context;
	}
}
