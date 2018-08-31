package com.xiaomo.main;

import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.xiaomo.main.bean.User;

public class MainSetup implements Setup {

	public void init(NutConfig conf) {
		 Ioc ioc = conf.getIoc();
	        Dao dao = ioc.get(Dao.class);
	        // 如果没有createTablesInPackage,请检查nutz版本
//	        Daos.createTablesInPackage(dao, "com.xiaomo.main", false);
	        Date date = new Date(); 
	        dao.create(User.class, false);
	        
	        Daos.migration(dao, User.class, true, false);
	     // 初始化默认根用户
	        if (dao.count(User.class) == 0) {
	            User user = new User();
	            user.setName("admin");
	            user.setPassword("9fc66c88926c53ad4e79fcd61f3dca28");//easyui@nutz
	            user.setSalt("apbb3v");
	            user.setType("super");
	            user.setCreateTime(date);
	            user.setUpdateTime(date);
	            dao.insert(user);
	        }
	        
	        // 获取NutQuartzCronJobFactory从而触发计划任务的初始化与启动
	        ioc.get(NutQuartzCronJobFactory.class);
	}

	public void destroy(NutConfig nc) {
		// TODO Auto-generated method stub
		
	}

}
