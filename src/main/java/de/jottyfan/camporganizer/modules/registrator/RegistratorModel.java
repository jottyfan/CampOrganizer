package de.jottyfan.camporganizer.modules.registrator;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.RegistratorGateway;
import de.jottyfan.camporganizer.modules.book.PersonBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class RegistratorModel {

	private List<RegistratorBean> list;
	private RegistratorBean bean;
	private Integer campPk;
	private List<CampBean> camps;

	/**
	 * prepare everything for the page registrator
	 */
	public boolean loadRegistratorPageContent(FacesContext facesContext) {
		try {
			list = new RegistratorGateway(facesContext).loadUsers();
			camps = new CampGateway(facesContext).getAllCampsFromView(false);
			return true;
		} catch (DataAccessException e) {
			list = new ArrayList<>();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading users", e.getMessage()));
			return false;
		}
	}

	/**
	 * accept user
	 * 
	 * @param facesContext
	 * @param pk
	 */
	public void acceptRegistration(FacesContext facesContext, RegistratorBean bean) {
		try {
			new RegistratorGateway(facesContext).acceptUser(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on accepting user", e.getMessage()));
		}
	}

	/**
	 * reject user
	 * 
	 * @param facesContext
	 * @param bean2
	 */
	public void rejectRegistration(FacesContext facesContext, RegistratorBean bean) {
		try {
			new RegistratorGateway(facesContext).rejectUser(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on rejecting user", e.getMessage()));
		}
	}

	/**
	 * delete registration from database
	 * 
	 * @param facesContext
	 * @param pk
	 */
	public void deleteRegistration(FacesContext facesContext, Integer pk) {
		try {
			new RegistratorGateway(facesContext).deleteUser(pk);
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on deleting user", e.getMessage()));
		}
	}

	/**
	 * prepare edit page of current entry referenced by bean
	 * 
	 * @param bean
	 *          to be edited
	 */
	public void prepareEdit(RegistratorBean bean) {
		this.bean = bean;
	}

	/**
	 * update current bean in database
	 * 
	 * @param facesContext
	 *          to be used
	 */
	public void update(FacesContext facesContext) {
		try {
			Integer affectedRows = new RegistratorGateway(facesContext).update(bean);
			if (affectedRows != 1) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "mysterious behavior",
								"Es wurde eine seltsame Anzahl an Datensätzen geändert: " + affectedRows
										+ " statt üblicherweise 1 Eintrag"));
			}
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on updating registration", e.getMessage()));
		}
	}

	/**
	 * download all registrations from t_person for the camp referenced by campPk
	 * 
	 * @param facesContext
	 */
	public void doDownloadCsv(FacesContext facesContext) {
		try {
			ExternalContext ec = facesContext.getExternalContext();
			ec.responseReset();
			ec.setResponseContentType("csv");
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"camp" + campPk + ".csv\"");
			List<PersonBean> list = new RegistratorGateway(facesContext).getAllPersonsOfCamp(campPk);
			// hack to generate a head line
			PersonBean bean = new PersonBean();
			bean.setForename("Vorname");
			bean.setSurname("Nachname");
			bean.setStreet("Straße");
			bean.setZip("PLZ");
			bean.setCity("Ort");
			bean.setPhone("Telefon");
			bean.setEmail("E-Mail");
			bean.setCamprole("Rolle");
			Writer writer = ec.getResponseOutputWriter();
			StatefulBeanToCsvBuilder<PersonBean> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<PersonBean> b2c = builder.build();
			b2c.write(bean);
			b2c.write(list);
			writer.close();
			facesContext.responseComplete();
		} catch (DataAccessException | IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on downloading", e.getMessage()));
		}
	}

	public List<RegistratorBean> getList() {
		return list;
	}

	public RegistratorBean getBean() {
		return bean;
	}

	public void setBean(RegistratorBean bean) {
		this.bean = bean;
	}

	public Integer getCampPk() {
		return campPk;
	}

	public void setCampPk(Integer campPk) {
		this.campPk = campPk;
	}

	public List<CampBean> getCamps() {
		return camps;
	}
}
