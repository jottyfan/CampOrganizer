package de.jottyfan.camporganizer.db.converter;

import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.db.jooq.enums.EnumSex;

/**
 * 
 * @author jotty
 *
 */
public class EnumConverter {
	/**
	 * get enum of role
	 * 
	 * @param role
	 *          String containing the enum literal to look for
	 * @return found enum
	 * @throws DataAccessException
	 *           if no such literal was found
	 */
	public EnumRole getEnumRole(String role) throws DataAccessException {
		for (EnumRole enumRole : EnumRole.values()) {
			if (enumRole.getLiteral().equals(role)) {
				return enumRole;
			}
		}
		throw new DataAccessException("enum " + role + " not found. Maybe the jooq generated classes are not up to date?");
	}

	/**
	 * get enum of role
	 * 
	 * @param camprole
	 *          String containing the enum literal to look for
	 * @return found enum
	 * @throws DataAccessException
	 *           if no such literal was found
	 */
	public EnumCamprole getEnumCamprole(String camprole) throws DataAccessException {
		for (EnumCamprole enumCamprole : EnumCamprole.values()) {
			if (enumCamprole.getLiteral().equals(camprole)) {
				return enumCamprole;
			}
		}
		throw new DataAccessException(
				"enum " + camprole + " not found. Maybe the jooq generated classes are not up to date?");
	}

	/**
	 * get enum of role
	 * 
	 * @param sex
	 *          String containing the enum literal to look for
	 * @return found enum
	 * @throws DataAccessException
	 *           if no such literal was found
	 */
	public EnumSex getEnumSex(String sex) throws DataAccessException {
		for (EnumSex enumSex : EnumSex.values()) {
			if (enumSex.getLiteral().equals(sex)) {
				return enumSex;
			}
		}
		throw new DataAccessException("enum " + sex + " not found. Maybe the jooq generated classes are not up to date?");
	}

	/**
	 * get string of camprole
	 * 
	 * @param camprole
	 *          enum that contains the camprole
	 * @return german translation of camprole
	 */
	public String getCamproleGerman(EnumCamprole camprole) {
		if (EnumCamprole.boy.equals(camprole)) {
			return "Junge (veraltet)";
		} else if (EnumCamprole.girl.equals(camprole)) {
			return "Mädchen (veraltet)";
		} else if (EnumCamprole.helperboy.equals(camprole)) {
			return "Mitarbeiter (veraltet)";
		} else if (EnumCamprole.helpergirl.equals(camprole)) {
			return "Mitarbeiterin (veraltet)";
		} else if (EnumCamprole.kitchen.equals(camprole)) {
			return "Küche (veraltet)";
		} else if (EnumCamprole.student.equals(camprole)) {
			return "Teilnehmer";
		} else if (EnumCamprole.teacher.equals(camprole)) {
			return "Mitarbeiter";
		} else if (EnumCamprole.director.equals(camprole)) {
			return "Leiter";
		} else if (EnumCamprole.feeder.equals(camprole)) {
			return "Küche";
		} else {
			return null;
		}
	}

	/**
	 * get string of sex
	 * 
	 * @param sex
	 *          enum that contains the sex
	 * @return german translation of sex
	 */
	public String getSexGerman(EnumSex sex) {
		if (EnumSex.female.equals(sex)) {
			return "weiblich";
		} else if (EnumSex.male.equals(sex)) {
			return "männlich";
		} else {
			return null;
		}
	}
}
