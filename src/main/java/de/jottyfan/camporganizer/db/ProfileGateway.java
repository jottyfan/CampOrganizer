package de.jottyfan.camporganizer.db;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.Field;
import org.jooq.InsertValuesStep4;
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
		PK(DSL.field("PK", Integer.class)),
		FORENAME(DSL.field("FORENAME", String.class)), 
		SURNAME(DSL.field("SURNAME", String.class)),
		PASSWORD(DSL.field("PASSWORD", String.class)),
		USERNAME(DSL.field("USERNAME", String.class));
    // @formatter:on

		private final Field<?> field;

		T_PROFILE(Field<?> field) {
			this.field = field;
		}

		public static final Table<?> getTableName() {
			return DSL.table("T_PROFILE");
		}

		public Field<?> get() {
			return field;
		}
		
		public Field<String> getString() {
			return (Field<String>) field;
		}
		
		public Field<Integer> getInteger() {
			return (Field<Integer>) field;
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

	private enum T_PROFILEROLE {
		// @formatter:off
		FK_PROFILE(DSL.field("FK_PROFILE", Integer.class)), 
		ROLE(DSL.field("ROLE", String.class));
    // @formatter:on

		private final Field<?> field;

		T_PROFILEROLE(Field<?> field) {
			this.field = field;
		}

		public static final Table<?> getTableName() {
			return DSL.table("T_PROFILEROLE");
		}

		public Field<?> get() {
			return field;
		}
		
		public Field<Integer> getInteger() {
			return (Field<Integer>) field;
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
		requested.setEncryptedPassword(r.get(V_PROFILE.PASSWORD.get(), String.class));
		requested.setForename(r.get(V_PROFILE.FORENAME.get(), String.class));
		requested.setSurname(r.get(V_PROFILE.SURNAME.get(), String.class));
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
			.set(T_PROFILE.PASSWORD.getString(), bean.getEncryptedPassword())
			.where(T_PROFILE.USERNAME.getString().eq(bean.getUsername()));
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
			.insertInto(T_PROFILE.getTableName(),
					        T_PROFILE.FORENAME.getString(),
					        T_PROFILE.SURNAME.getString(),
					        T_PROFILE.USERNAME.getString(),
					        T_PROFILE.PASSWORD.getString())
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
	    .deleteFrom(T_PROFILEROLE.getTableName())
	    .where(T_PROFILEROLE.FK_PROFILE.getInteger().in(
	    		getJooq()
	    		.select(T_PROFILE.PK.getInteger())
	    		.from(T_PROFILE.getTableName())
	      .where(T_PROFILE.USERNAME.getString().eq(bean.getUsername())
	    )));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();

		DeleteConditionStep<?> sql2 = getJooq()
		// @formatter:off
	    .deleteFrom(T_PROFILE.getTableName())
	    .where(T_PROFILE.USERNAME.getString().eq(bean.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql2.toString());
		sql2.execute();
	}
}
