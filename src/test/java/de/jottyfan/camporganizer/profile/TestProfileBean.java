package de.jottyfan.camporganizer.profile;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author jotty
 *
 */
public class TestProfileBean {

	@Test
	public void testSetPassword() {
		ProfileBean bean = new ProfileBean();
		bean.setPassword("this is crap");
		assertTrue(bean.checkPassword("this is crap"));
	}
}
