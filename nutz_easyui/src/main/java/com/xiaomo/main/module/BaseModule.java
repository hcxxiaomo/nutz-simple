package com.xiaomo.main.module;

import java.util.List;

import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.filter.CheckSession;

import com.xiaomo.main.bean.BaseBean;
import com.xiaomo.main.pojo.EasyUiJsonObj;

@Ok("json:{quoteName:true, ignoreNull:true}")
@Fail("http:500")
@Filters(@By(type=CheckSession.class, args={"me", "/index"})) // 检查当前Session是否带me这个属性
public abstract class BaseModule<T extends BaseBean>{
	

	@At
	public EasyUiJsonObj<? extends BaseBean> getInfoByPage(Integer page,Integer row ){
		return null;
	}
	
	
	@At
	public void goIndex(){
	}
	
	@At
	public NutMap save(BaseBean baseBean){
		return null;
	}
	
	@At
	public NutMap delete(String delIds){
		return null;
	}
	
	@At
	public List<? extends BaseBean> getAll(){
		return null;
	}
}
