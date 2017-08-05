package de.jottyfan.camporganizer.db;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.Table;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author henkej
 *
 */
public class ProfileGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(ProfileGateway.class);

	private enum V_PROFILE {
		// @formatter:off
		USERNAME(DSL.field("V_PROFILE.USERNAME", String.class)), 
		PASSWORD(DSL.field("V_PROFILE.PASSWORD", String.class)), 
		FORENAME(DSL.field("V_PROFILE.FORENAME", String.class)), 
		SURNAME(DSL.field("V_PROFILE.SURNAME", String.class));
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
		SelectConditionStep<?> sql = getJooq()
		// @formatter:off
			.select(V_PROFILE.FORENAME.get(),
					    V_PROFILE.SURNAME.get())
			// TODO: add roles as jooq enum or such
			.from(V_PROFILE.getTableName())
			.where(V_PROFILE.USERNAME.get().eq(requested.getUsername()))
			.and(V_PROFILE.PASSWORD.get().eq(requested.getPassword()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		Record r = sql.fetchOne();
		if (r == null) {
			throw new DataAccessException("login invalid");
		}
		requested.setForename(r.get(V_PROFILE.FORENAME.get()));
		requested.setSurname(r.get(V_PROFILE.SURNAME.get()));
		return requested;
	}
}
