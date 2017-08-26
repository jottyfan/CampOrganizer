package de.jottyfan.camporganizer.book;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.BookGateway;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.ProfileGateway;
import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class BookController extends Controller {

	private final static Logger LOGGER = LogManager.getLogger(BookController.class);

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{bookBean}")
	private BookBean bean;

	@ManagedProperty(value = "#{profileBean}")
	private ProfileBean profileBean;

	public String toBook() {
		try {
			bean.setCamps(new CampGateway(facesContext).getAllCampsFromView(true));
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading camps", e.getMessage()));
			LOGGER.error("BookController.toBook: ", e);
		}
		return "/pages/book.jsf";
	}

	public String toBook(Integer campPk) {
		bean.setFkCamp(campPk);
		return toBook();
	}

	public String doBook() {
		try {
			if (profileBean.getUsername() != null && !profileBean.getUsername().isEmpty()) {
				profileBean.setForename(bean.getForename());
				profileBean.setSurname(bean.getSurname());
				if (new ProfileGateway(facesContext).checkUsernameExists(profileBean)) {
					throw new DataAccessException("Der Name ist leider schon vergeben, bitte wähle einen anderen.");
				} else if (profileBean.getPassword().equals(profileBean.getPasswordAgain())) {
					Integer pk = new ProfileGateway(facesContext).register(profileBean, true);
					bean.setFkProfile(pk);
				} else {
					throw new DataAccessException("Die eingegebenen Passwörter sind nicht gleich.");
				}
			}
			new BookGateway(facesContext).insert(bean);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "booking finished",
					"Die Anmeldung wurde übernommen. Sobald sie von uns bestätigt wurde, ist Dein Platz auf der Freizeit gesichert."));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on booking", e.getMessage()));
		}
		profileBean.setForename(null);
		profileBean.setSurname(null);
		profileBean.setPk(null);
		profileBean.setUsername(null);
		return toBook();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public BookBean getBean() {
		return bean;
	}

	public void setBean(BookBean bean) {
		this.bean = bean;
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}
}
