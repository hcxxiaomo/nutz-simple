package com.xiaomo.main.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.xiaomo.main.bean.BaseBean;
import com.xiaomo.main.pojo.EasyUiJsonObj;


@IocBean
public abstract class BaseService<T extends BaseBean> {

	protected static final Log log = Logs.get();

	 @Inject
	 protected Dao dao;
	 
	 private T baseBeanInner;
	
	 
	 public NutMap addOrUpdate(T  baseBean){
//		 BaseBean bb = (BaseBean) baseBean;
		 Date date = new Date();
		 boolean result = false;
		 baseBean.setUpdateTime(date);
		 try {
			 if (baseBean.getId() == null) {
				 baseBean.setCreateTime(date);
				 baseBean = dao.insert(baseBean);
				}else{
					dao.updateIgnoreNull(baseBean);
				}
			result = true;
		} catch (Exception e) {
			log.error("addOrUpdate",e);
		}
		 if (result) {
			 return new NutMap().addv("success", "true");
		 }else{
			 return new NutMap().addv("errorMsg", "保存数据出错，请稍后再操作！");
		 }
	 }
	 
	 /**T里面的id必须要有数据呢*/
	 public NutMap delete(T  baseBean) {
		 boolean result = false;
		 try {
			 dao.delete(baseBean);
			 //删除
			// dao.clear(baseBean.getClass(), Cnd.where("id", "=", baseBean.getId()));
			 result = true;
		 } catch (Exception e) {
			 log.error("delete error", e);
		 }
		 if (result) {
			 return new NutMap().addv("success", "true");
		 }else{
			 return new NutMap().addv("errorMsg", "删除出错，请联系管理员！");
		 }
	 }
	 
	 public NutMap delete(String[] arrays) {
		 boolean result = false;
		 try {
			 SimpleCriteria  cri = Cnd.cri();
			 cri.where().andIn("id", arrays);
			 dao.clear(baseBeanInner.getClass(), Cnd.byCri(cri));
			 //删除
			 // dao.clear(baseBean.getClass(), Cnd.where("id", "=", baseBean.getId()));
			 result = true;
		 } catch (Exception e) {
			 log.error("delete error", e);
		 }
		 if (result) {
			 return new NutMap().addv("success", "true");
		 }else{
			 return new NutMap().addv("errorMsg", "删除出错，请联系管理员！");
		 }
	 }
	 
	 public EasyUiJsonObj<? extends BaseBean> getInfoByPage(Integer  pageNumber,Integer pageSize
				, Map<String,Object> params
				 ){
		 
			 EasyUiJsonObj<T>  easyUiJsonObj = new EasyUiJsonObj<>();
			 List<? extends BaseBean> list = null;
			 
			 Cnd c = null;
			 //Condition condition = null;
			 c =  Cnd.where(Cnd.exps("1", "=",1));
			 
			 //Condition condition = Cnd.orderBy().desc("id");
			 
			 
			 if (params != null) {
				for (Map.Entry<String,Object> obj :  params.entrySet()) {
					if (obj.getValue() != null) {
						if ("startDate".equals(obj.getKey())) {
							c.and(Cnd.exps("createTime", ">=", obj.getValue().toString().concat(" 00:00:00")));
						}else if ("endDate".equals(obj.getKey())) {
							c.and(Cnd.exps("createTime", "<=", obj.getValue().toString().concat(" 23:59:59")));
						}else{
							c.and(Cnd.exp(obj.getKey(), "=", obj.getValue()));
						}
					}
					
				}
			}
			 c.desc("id");
			 log.info(c.toString());
				Pager page = dao.createPager(pageNumber, pageSize);
				list = dao.query(baseBeanInner.getClass(), c, page);
				easyUiJsonObj.setTotal(dao.count(baseBeanInner.getClass(),c));
				easyUiJsonObj.setRows(list);
				return easyUiJsonObj;
			 
		 }
	 
	 
	 public List<? extends BaseBean> getAll(T  baseBean){
		 return dao.query(baseBean.getClass(),null);
	 }
	 
}
