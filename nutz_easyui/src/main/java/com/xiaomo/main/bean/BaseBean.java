package com.xiaomo.main.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

public abstract class BaseBean {

	@Id
	protected Long id;
	
    @Column
    @Comment("创建时间")
    protected Date createTime;
    @Column
    @Comment("更新时间")
    protected Date updateTime;
	@Override
	public String toString() {
		// 这不是必须的, 只是为了debug的时候方便看
        return Json.toJson(this, JsonFormat.compact());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	
}
