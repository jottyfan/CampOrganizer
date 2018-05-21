package de.jottyfan.camporganizer.db.converter;

import java.util.HashMap;
import java.util.Map;

import de.jottyfan.camporganizer.db.jooq.enums.EnumModule;

/**
 * 
 * @author jotty
 *
 */
public class ModuleTranslation {
	private Map<String, String> map;

	public ModuleTranslation() {
		this.map = new HashMap<>();
		map.put("business", "Abrechnung");
		map.put("registration", "Bestätigung");
	}

	public String get(EnumModule enumModule) {
		return get(enumModule.getLiteral());
	}

	public String get(String key) {
		return map.get(key);
	}

	public Map<String, String> getAll() {
		return map;
	}
}
