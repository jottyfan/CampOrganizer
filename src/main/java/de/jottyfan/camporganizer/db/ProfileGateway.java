package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_PROFILE;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PROFILEROLE;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_PROFILE;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertValuesStep4;
import org.jooq.Record3;
import org.jooq.Record4;
import org.jooq.SelectConditionStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
public class ProfileGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(ProfileGateway.class);

	public ProfileGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * get profile of user if, and only, if the user password combination of requested exists
	 * 
	 * @param requested
	 *          bean with user and password
	 * @return filled profile bean for a valid login
	 * @throws DataAccessException
	 */
	public ProfileBean getProfile(ProfileBean requested) throws DataAccessException {
		SelectConditionStep<Record4<String, String, String, EnumRole[]>> sql = getJooq()
		// @formatter:off
			.select(V_PROFILE.FORENAME,
					    V_PROFILE.SURNAME,
					    V_PROFILE.PASSWORD,
					    V_PROFILE.ROLES)
			.from(V_PROFILE)
			.where(V_PROFILE.USERNAME.eq(requested.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		Record4<String, String, String, EnumRole[]> r = sql.fetchOne();
		if (r == null) {
			throw new DataAccessException("login invalid, no such user " + requested.getUsername());
		}
		requested.setEncryptedPassword(r.get(V_PROFILE.PASSWORD));
		requested.setForename(r.get(V_PROFILE.FORENAME));
		requested.setSurname(r.get(V_PROFILE.SURNAME));
		StringBuilder roles = new StringBuilder();
		for (EnumRole enumRole : r.get(V_PROFILE.ROLES)) {
			roles.append(enumRole != null ? enumRole.getLiteral() : "").append("|");
		}
		requested.setRoles(roles.toString());
		if (!requested.checkPasswordAndForgetPlainOne()) {
			throw new DataAccessException("login invalid for user " + requested.getUsername());
		}
		return requested;
	}

	/**
	 * change the users password
	 * 
	 * @param bean
	 *          containing the new password in an encrypted way
	 * @throws DataAccessException
	 */
	public void changePasswords(ProfileBean bean) throws DataAccessException {
		UpdateConditionStep<?> sql = getJooq()
		// @formatter:off
			.update(T_PROFILE)
			.set(T_PROFILE.PASSWORD, bean.getEncryptedPassword())
			.where(T_PROFILE.USERNAME.eq(bean.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();
	}

	/**
	 * register profile
	 * 
	 * @param bean
	 *          containing new password, username, forename and surname
	 * @throws DataAccessException
	 */
	public void register(ProfileBean bean) throws DataAccessException {
		InsertValuesStep4<?, String, String, String, String> sql = getJooq()
		// @formatter:off
			.insertInto(T_PROFILE,
					        T_PROFILE.FORENAME,
					        T_PROFILE.SURNAME,
					        T_PROFILE.USERNAME,
					        T_PROFILE.PASSWORD)
			.values(bean.getForename(), bean.getSurname(), bean.getUsername(), bean.getEncryptedPassword());
	  // @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();
	}

	/**
	 * remove login
	 * 
	 * @param bean
	 *          containing username of dataset to be removed
	 * @throws DataAccessExceptionF
	 */
	public void removeLogin(ProfileBean bean) throws DataAccessException {
		DeleteConditionStep<?> sql = getJooq()
		// @formatter:off
	    .deleteFrom(T_PROFILEROLE)
	    .where(T_PROFILEROLE.FK_PROFILE.in(
	    		getJooq()
	    		.select(T_PROFILE.PK)
	    		.from(T_PROFILE)
	      .where(T_PROFILE.USERNAME.eq(bean.getUsername())
	    )));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();

		DeleteConditionStep<?> sql2 = getJooq()
		// @formatter:off
	    .deleteFrom(T_PROFILE)
	    .where(T_PROFILE.USERNAME.eq(bean.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql2.toString());
		sql2.execute();
	}
}
