package com.xiaomo.main.pojo;

import java.util.Collection;
import java.util.List;

import com.xiaomo.main.bean.BaseBean;

public class EasyUiJsonObj<T extends BaseBean> {

	private Collection<? extends BaseBean> rows;
	private Integer total;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Collection<? extends BaseBean> getRows() {
		return rows;
	}
	public void setRows(List<? extends BaseBean> list) {
		this.rows = list;
	}
	
}
