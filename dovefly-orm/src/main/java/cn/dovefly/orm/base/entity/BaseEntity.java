package cn.dovefly.orm.base.entity;

import net.sf.json.JSONArray;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fengchunming on 2017/7/11.
 */
public class BaseEntity implements Serializable {

    private Integer isValid = 1;

    private Date createTime = new Date();

    private String createBy;

    private Date updateTime = new Date();

    private String updateBy;

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * override method 'toString'
     *
     * @return String 字符串表示
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 将对象转换为JSON字符串格式
     *
     * @return String 字符串表示
     */
    public String toJSON() {
        return JSONArray.fromObject(this).toString();
    }
}
