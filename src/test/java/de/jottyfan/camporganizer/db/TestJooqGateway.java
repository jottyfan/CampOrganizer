package de.jottyfan.camporganizer.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author henkej
 *
 */
public class TestJooqGateway {

	@Test
	public void testConstructor() {
		JooqGateway gw;

		try {
			gw = new JooqGateway(null) {
			};
			assertTrue(false); // should not be reachable
		} catch (DataAccessException e) {
			assertEquals("facesContext must not be null", e.getMessage());
		}

		Map<String, Object> sessionMap = new HashMap<String, Object>();
		FacesContext mockFaces = Mockito.mock(FacesContext.class);
		ExternalContext ec = Mockito.mock(ExternalContext.class);
		Mockito.when(mockFaces.getExternalContext()).thenReturn(ec);
		Mockito.when(ec.getSessionMap()).thenReturn(sessionMap);

		gw = new JooqGateway(mockFaces) {
		};
		assertNotNull(gw.getJooq());
	}

}
