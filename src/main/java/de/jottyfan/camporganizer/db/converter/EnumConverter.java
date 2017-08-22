package de.jottyfan.camporganizer.db.converter;

import javax.faces.convert.ConverterException;

import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;

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
}
