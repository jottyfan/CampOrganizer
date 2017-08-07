package de.jottyfan.camporganizer.db;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.SelectConditionStep;
import org.jooq.Table;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
public class ProfileGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(ProfileGateway.class);

	private enum T_PROFILE {
		// @formatter:off
		PASSWORD(DSL.field("PASSWORD", String.class)),
		USERNAME(DSL.field("USERNAME", String.class));
    // @formatter:on

		private final Field<String> field;

		T_PROFILE(Field<String> field) {
			this.field = field;
		}

		public static final Table<?> getTableName() {
			return DSL.table("T_PROFILE");
		}

		public Field<String> get() {
			return field;
		}
	}
	
	private enum V_PROFILE {
		// @formatter:off
		USERNAME(DSL.field("USERNAME", String.class)), 
		PASSWORD(DSL.field("PASSWORD", String.class)), 
		FORENAME(DSL.field("FORENAME", String.class)), 
		SURNAME(DSL.field("SURNAME", String.class));
    // @formatter:on

		private final Field<String> field;

		V_PROFILE(Field<String> field) {
			this.field = field;
		}

		public static final Table<?> getTableName() {
			return DSL.table("V_PROFILE");
		}

		public Field<String> get() {
			return field;
		}
	}

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
		SelectConditionStep<Record3<String, String, String>> sql = getJooq()
		// @formatter:off
			.select(V_PROFILE.FORENAME.get(),
					    V_PROFILE.SURNAME.get(),
					    V_PROFILE.PASSWORD.get())
			// TODO: add roles as jooq enum or such
			.from(V_PROFILE.getTableName())
			.where(V_PROFILE.USERNAME.get().eq(requested.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		Record3<String, String, String> r = sql.fetchOne();
		if (r == null) {
			throw new DataAccessException("login invalid, no such user " + requested.getUsername());
		}
		requested.setEncryptedPassword(r.get(V_PROFILE.PASSWORD.get()));
		requested.setForename(r.get(V_PROFILE.FORENAME.get()));
		requested.setSurname(r.get(V_PROFILE.SURNAME.get()));
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
			.update(T_PROFILE.getTableName())
			.set(T_PROFILE.PASSWORD.get(), bean.getEncryptedPassword())
			.where(T_PROFILE.USERNAME.get().eq(bean.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();
	}
}
