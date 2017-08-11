package de.jottyfan.camporganizer.sales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jottyfan.camporganizer.register.CampBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class SalesBean {
	private String trader;
	private Integer fkCamp;
	private String provider;
	private Float cash;
	// TODO: list of recipe types
	private Date buydate;
	private String recipeNumber;
	// TODO: blob container for image upload
	private String recipeNote;

	private List<CampBean> camps;

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
		builder.append(", buydate=");
		builder.append(buydate);
		builder.append(", recipeNumber=");
		builder.append(recipeNumber);
		builder.append(", recipeNote=");
		builder.append(recipeNote);
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
}
