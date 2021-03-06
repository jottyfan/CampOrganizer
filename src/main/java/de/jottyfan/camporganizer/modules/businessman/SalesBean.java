package de.jottyfan.camporganizer.modules.businessman;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import de.jottyfan.camporganizer.CampBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class SalesBean {
	private Integer pk;
	private String trader;
	private Integer fkCamp;
	private String provider;
	private Float cash;
	private String incredients;
	private Date buydate;
	private String recipeNumber;
	private byte[] recipeshot;
	private String recipeNote;
	private Boolean lockSales;

	private Part file;

	private List<CampBean> camps;
	private List<String> traders;
	private List<String> providers;

	/**
	 * get name and year of camp
	 * 
	 * @param campId
	 *          to be used as a reference
	 * @return name and year if found, empty string otherwise
	 */
	public String getCampNameAndYear(Integer campId) {
		for (CampBean bean : camps) {
			if (bean.getPk().equals(campId)) {
				StringBuilder buf = new StringBuilder();
				buf.append(bean.getName()).append(" ").append(bean.getYear());
				return buf.toString();
			}
		}
		return "";
	}

	/**
	 * convert upload file content to recipeshot
	 * 
	 * @throws IOException
	 */
	public void uploadFile() throws IOException {
		if (file != null) {
			InputStream inputStream = file.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			if (bytes.length > 0) {
				recipeshot = bytes;
			} else {
				recipeshot = null;
			}
		}
	}

	/**
	 * get all unlocked camp beans
	 * 
	 * @return list of unlocked camp beans
	 */
	public List<CampBean> getUnlockedCamps() {
		List<CampBean> list = new ArrayList<>();
		for (CampBean bean : camps) {
			if (!bean.getLockSales()) {
				list.add(bean);
			}
		}
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SalesBean [trader=");
		builder.append(trader);
		builder.append(", fkCamp=");
		builder.append(fkCamp);
		builder.append(", provider=");
		builder.append(provider);
		builder.append(", cash=");
		builder.append(cash);
		builder.append(", incredients=");
		builder.append(incredients);
		builder.append(", buydate=");
		builder.append(buydate);
		builder.append(", recipeNumber=");
		builder.append(recipeNumber);
		builder.append(", recipeNote=");
		builder.append(recipeNote);
		builder.append(", lockSales=");
		builder.append(lockSales);
		builder.append("]");
		return builder.toString();
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public Integer getFkCamp() {
		return fkCamp;
	}

	public void setFkCamp(Integer fkCamp) {
		this.fkCamp = fkCamp;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Float getCash() {
		return cash;
	}

	public BigDecimal getCashBigDecimal() {
		return cash == null ? null : new BigDecimal(cash);
	}

	public void setCash(Float cash) {
		this.cash = cash;
	}

	public Date getBuydate() {
		return buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	public String getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(String recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public String getRecipeNote() {
		return recipeNote;
	}

	public void setRecipeNote(String recipeNote) {
		this.recipeNote = recipeNote;
	}

	public List<CampBean> getCamps() {
		return camps;
	}

	public void setCamps(List<CampBean> camps) {
		this.camps = camps;
	}

	public String getIncredients() {
		return incredients;
	}

	public void setIncredients(String incredients) {
		this.incredients = incredients;
	}

	public byte[] getRecipeshot() {
		return recipeshot;
	}

	public void setRecipeshot(byte[] recipeshot) {
		this.recipeshot = recipeshot;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public List<String> getTraders() {
		return traders;
	}

	public void setTraders(List<String> traders) {
		this.traders = traders;
	}

	public List<String> getProviders() {
		return providers;
	}

	public void setProviders(List<String> providers) {
		this.providers = providers;
	}

	public Boolean getLockSales() {
		return lockSales;
	}

	public void setLockSales(Boolean lockSales) {
		this.lockSales = lockSales;
	}
}
