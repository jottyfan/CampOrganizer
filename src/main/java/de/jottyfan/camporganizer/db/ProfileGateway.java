package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_DOCUMENT;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_LOCATION;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PROFILE;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PROFILEROLE;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_PROFILE;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_ROLE;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertFinalStep;
import org.jooq.InsertResultStep;
import org.jooq.InsertValuesStep1;
import org.jooq.InsertValuesStep2;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Record5;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.LambdaResultWrapper;
import de.jottyfan.camporganizer.db.converter.EnumConverter;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TProfileRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TProfileroleRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.modules.admin.DocumentBean;
import de.jottyfan.camporganizer.modules.admin.LocationBean;
import de.jottyfan.camporganizer.modules.admin.ProfileRoleBean;
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
		SelectConditionStep<Record5<Integer, String, String, String, EnumRole[]>> sql = getJooq()
		// @formatter:off
			.select(V_PROFILE.PK,
						  V_PROFILE.FORENAME,
					    V_PROFILE.SURNAME,
					    V_PROFILE.PASSWORD,
					    V_PROFILE.ROLES)
			.from(V_PROFILE)
			.where(V_PROFILE.USERNAME.eq(requested.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		Record5<Integer, String, String, String, EnumRole[]> r = sql.fetchOne();
		if (r == null) {
			throw new DataAccessException("login invalid, no such user " + requested.getUsername());
		}
		requested.setPk(r.get(V_PROFILE.PK));
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
	 * @param addSubscriber
	 *          if true, add an entry of users new pk and role subscriber to t_profilerole
	 * @return pk of new profile
	 * @throws DataAccessException
	 */
	public Integer register(ProfileBean bean, boolean addSubscriber) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			InsertResultStep<TProfileRecord> sql = DSL.using(t)
			// @formatter:off
				.insertInto(T_PROFILE,
						        T_PROFILE.FORENAME,
						        T_PROFILE.SURNAME,
						        T_PROFILE.USERNAME,
						        T_PROFILE.PASSWORD)
				.values(bean.getForename(), bean.getSurname(), bean.getUsername(), bean.getEncryptedPassword())
				.returning(T_PROFILE.PK);
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			lrw.setNumber(sql.fetchOne().get(T_PROFILE.PK));
			if (addSubscriber) {
				InsertValuesStep2<TProfileroleRecord, Integer, EnumRole> sql2 = DSL.using(t)
				// @formatter:off
					.insertInto(T_PROFILEROLE,
	                     T_PROFILEROLE.FK_PROFILE,
	                     T_PROFILEROLE.ROLE)
					.values(lrw.getNumber(), EnumRole.subscriber);
				// @formatter:on
				LOGGER.debug("{}", sql2.toString());
				sql2.execute();
			}
			InsertValuesStep1<TRssRecord, String> sql3 = DSL.using(t)
			// @formatter:off
				.insertInto(T_RSS,
						        T_RSS.MSG)
				.values(new StringBuilder(bean.getFullname()).append(" hat sich als Nutzer im camporganizer registriert.").toString());
			// @formatter:on
			LOGGER.debug("{}", sql3.toString());
			sql3.execute();
		});
		return lrw.getNumber();
	}

	/**
	 * remove login
	 * 
	 * @param bean
	 *          containing username of dataset to be removed
	 * @throws DataAccessExceptionF
	 */
	public void removeLogin(ProfileBean bean) throws DataAccessException {
		getJooq().transaction(t -> {
			UpdateConditionStep<TPersonRecord> sql = DSL.using(t)
			// @formatter:off
			  .update(T_PERSON)
			  .set(T_PERSON.FK_PROFILE, (Integer) null)
			  .where(T_PERSON.FK_PROFILE.eq(bean.getPk()));
			// @formatter:off
			LOGGER.debug("{}", sql.toString());
			sql.execute();
			
			DeleteConditionStep<?> sql1 = DSL.using(t)
			// @formatter:off
		    .deleteFrom(T_PROFILEROLE)
		    .where(T_PROFILEROLE.FK_PROFILE.in(
		    		getJooq()
		    		.select(T_PROFILE.PK)
		    		.from(T_PROFILE)
		      .where(T_PROFILE.USERNAME.eq(bean.getUsername())
		    )));
			// @formatter:on
			LOGGER.debug("{}", sql1.toString());
			sql1.execute();

			DeleteConditionStep<?> sql2 = DSL.using(t)
			// @formatter:off
		    .deleteFrom(T_PROFILE)
		    .where(T_PROFILE.USERNAME.eq(bean.getUsername()));
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
			
			InsertValuesStep1<TRssRecord, String> sql3 = DSL.using(t)
			// @formatter:off
				.insertInto(T_RSS,
						        T_RSS.MSG)
				.values(new StringBuilder(bean.getFullname()).append(" hat sich vom Portal abgemeldet.").toString());
			// @formatter:on
			LOGGER.debug("{}", sql3.toString());
			sql3.execute();
		});
	}

	/**
	 * get all from profile role table
	 * 
	 * @return list of found prifile roles
	 */
	public List<ProfileRoleBean> getAllProfileRoles() {
		SelectOnConditionStep<Record5<EnumRole, String, String, String, Integer>> sql = getJooq()
		// @formatter:off
			.select(T_PROFILEROLE.ROLE,
							T_PROFILE.FORENAME,
							T_PROFILE.SURNAME,
							T_PROFILE.USERNAME,
							T_PROFILE.PK)
			.from(T_PROFILEROLE)
			.leftJoin(T_PROFILE).on(T_PROFILE.PK.eq(T_PROFILEROLE.FK_PROFILE));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<ProfileRoleBean> list = new ArrayList<>();
		for (Record5<EnumRole, String, String, String, Integer> r : sql.fetch()) {
			ProfileBean user = new ProfileBean();
			user.setForename(r.get(T_PROFILE.FORENAME));
			user.setSurname(r.get(T_PROFILE.SURNAME));
			user.setUsername(r.get(T_PROFILE.USERNAME));
			user.setPk(r.get(T_PROFILE.PK));
			ProfileRoleBean bean = new ProfileRoleBean();
			bean.setRole(r.get(T_PROFILEROLE.ROLE).getLiteral());
			bean.setUser(user);
			list.add(bean);
		}
		return list;
	}

	/**
	 * get all roles from db
	 * 
	 * @return list of valid roles
	 */
	public List<String> getAllRoles() {
		SelectJoinStep<Record1<EnumRole>> sql = getJooq()
		// @formatter:off
			.select(V_ROLE.UNNEST)
			.from(V_ROLE);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<String> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			list.add(r.get(V_ROLE.UNNEST).getLiteral());
		}
		return list;
	}

	/**
	 * get all users from db (except password)
	 * 
	 * @return list of valid users
	 */
	public List<ProfileBean> getAllUsers() {
		SelectJoinStep<Record4<String, String, String, Integer>> sql = getJooq()
		// @formatter:off
			.select(T_PROFILE.FORENAME,
					    T_PROFILE.SURNAME,
					    T_PROFILE.USERNAME,
					    T_PROFILE.PK)
			.from(T_PROFILE);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<ProfileBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			ProfileBean bean = new ProfileBean();
			bean.setForename(r.get(T_PROFILE.FORENAME));
			bean.setSurname(r.get(T_PROFILE.SURNAME));
			bean.setUsername(r.get(T_PROFILE.USERNAME));
			bean.setPk(r.get(T_PROFILE.PK));
			list.add(bean);
		}
		return list;
	}

	/**
	 * delete entry from profile role table
	 * 
	 * @param bean
	 *          containing user's pk and role
	 * @return amount of affected lines
	 * @throws DataAccessException
	 */
	public Integer deleteProfileRole(ProfileRoleBean bean) throws DataAccessException {
		DeleteConditionStep<TProfileroleRecord> sql = getJooq()
		// @formatter:off
			.deleteFrom(T_PROFILEROLE)
			.where(T_PROFILEROLE.ROLE.eq(new EnumConverter().getEnumRole(bean.getRole()))
			.and(T_PROFILEROLE.FK_PROFILE.eq(bean.getUser().getPk())));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}

	/**
	 * add entry into profile role table
	 * 
	 * @param bean
	 *          containig user's pk and role
	 * @return amount of affected lines
	 * @throws DataAccessException
	 */
	public Integer insertProfileRole(ProfileRoleBean bean) throws DataAccessException {
		InsertValuesStep2<TProfileroleRecord, EnumRole, Integer> sql = getJooq()
		// @formatter:off
			.insertInto(T_PROFILEROLE,
					        T_PROFILEROLE.ROLE,
					        T_PROFILEROLE.FK_PROFILE)
			.values(new EnumConverter().getEnumRole(bean.getRole()), bean.getUser().getPk());
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}

	/**
	 * check if username exists in db
	 * 
	 * @param profileBean
	 * @return true if username exists, false otherwise
	 */
	public boolean checkUsernameExists(ProfileBean profileBean) throws DataAccessException {
		SelectConditionStep<Record1<Integer>> sql = getJooq()
		// @formatter:off
			.select(T_PROFILE.PK)
			.from(T_PROFILE)
			.where(T_PROFILE.USERNAME.eq(profileBean.getUsername()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.fetchOne() != null;
	}

	/**
	 * get all documents from db
	 * 
	 * @return list of found documents
	 * @throws DataAccessException
	 */
	public List<DocumentBean> getAllDocuments() throws DataAccessException {
		SelectJoinStep<Record5<Integer, EnumDocument, String, String, EnumFiletype>> sql = getJooq()
		// @formatter:off
			.select(T_DOCUMENT.PK,
					    T_DOCUMENT.DOCTYPE,
					    T_DOCUMENT.NAME,
					    T_DOCUMENT.DOCUMENT,
					    T_DOCUMENT.FILETYPE)
			.from(T_DOCUMENT);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<DocumentBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			DocumentBean bean = new DocumentBean(r.get(T_DOCUMENT.PK));
			bean.setDoctype(r.get(T_DOCUMENT.DOCTYPE));
			bean.setName(r.get(T_DOCUMENT.NAME));
			bean.setDocument(r.get(T_DOCUMENT.DOCUMENT));
			bean.setFiletype(r.get(T_DOCUMENT.FILETYPE));
			list.add(bean);
		}
		return list;
	}

	/**
	 * get all from t_location
	 * 
	 * @return list of found locations
	 * @throws DataAccessException
	 */
	public List<LocationBean> getAllLocations() throws DataAccessException {
		SelectJoinStep<Record4<Integer, String, String, Integer>> sql = getJooq()
		// @formatter:off
			.select(T_LOCATION.PK,
					    T_LOCATION.NAME,
					    T_LOCATION.URL,
					    T_LOCATION.FK_DOCUMENT)
			.from(T_LOCATION);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<LocationBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			LocationBean bean = new LocationBean(r.get(T_LOCATION.PK));
			bean.setName(r.get(T_LOCATION.NAME));
			bean.setUrl(r.get(T_LOCATION.URL));
			bean.setFkDocument(r.get(T_LOCATION.FK_DOCUMENT));
			list.add(bean);
		}
		return list;
	}

	/**
	 * delete from t_profile where pk = ?
	 * 
	 * @param bean
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer deleteProfile(ProfileBean bean) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			Integer affected = 0;

			DeleteConditionStep<TProfileroleRecord> sql = DSL.using(t)
			// @formatter:off
				.deleteFrom(T_PROFILEROLE)
				.where(T_PROFILEROLE.FK_PROFILE.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			affected += sql.execute();

			UpdateConditionStep<TPersonRecord> sql1 = DSL.using(t)
			// @formatter:off
				.update(T_PERSON)
				.set(T_PERSON.FK_PROFILE, (Integer) null)
				.where(T_PERSON.FK_PROFILE.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql1.toString());
			affected += sql1.execute();

			DeleteConditionStep<TProfileRecord> sql2 = DSL.using(t)
			// @formatter:off
				.deleteFrom(T_PROFILE)
				.where(T_PROFILE.PK.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			affected += sql2.execute();

			lrw.setNumber(affected);
		});
		return lrw.getNumber();
	}

	/**
	 * generate a new password and set it for the user
	 * 
	 * @param bean
	 *          to be used
	 * @return new password
	 * @throws DataAccessException
	 */
	public String resetPassword(ProfileBean bean) throws DataAccessException {
		String uuid = UUID.randomUUID().toString();
		String password = new StrongPasswordEncryptor().encryptPassword(uuid);
		bean.setPassword(password);
		UpdateConditionStep<TProfileRecord> sql = getJooq()
		// @formatter:off
			.update(T_PROFILE)
			.set(T_PROFILE.PASSWORD, bean.getEncryptedPassword())
			.where(T_PROFILE.PK.eq(bean.getPk()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();
		return password;
	}

	/**
	 * make sure that user has role subscriber
	 * 
	 * @param profileBean
	 * @throws DataAccessException
	 */
	public void ensureSubscriberRole(ProfileBean profileBean) throws DataAccessException {
		InsertFinalStep<TProfileroleRecord> sql = getJooq()
		// @formatter:off
			.insertInto(T_PROFILEROLE,
					        T_PROFILEROLE.FK_PROFILE,
					        T_PROFILEROLE.ROLE)
			.values(profileBean.getPk(), EnumRole.subscriber)
			.onConflict(T_PROFILEROLE.FK_PROFILE, T_PROFILEROLE.ROLE)
			.doNothing();
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();
	}
}
