package de.jottyfan.camporganizer.modules.businessman;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class SalesModel {

	public SalesModel() {
		list = new ArrayList<>();
	}

	private List<SalesBean> list;

	private Integer activeIndex;

	public List<SalesBean> getList() {
		return list;
	}

	public void setList(List<SalesBean> list) {
		this.list = list;
	}

	public Integer getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(Integer activeIndex) {
		this.activeIndex = activeIndex;
	}
}
