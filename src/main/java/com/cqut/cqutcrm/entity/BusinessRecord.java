package com.cqut.cqutcrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("business_record")
public class BusinessRecord {
    @TableId(type = IdType.AUTO,value="record_id")
    private Integer recordId;
    @TableField("record_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GHT+8")
    private Date recordDate;
    @TableField("record_addr")
    private String recordAddr;
    @TableField("record_message")
    private String recordMessage;
    @TableField("cus_id")
    private Integer cusId;
    @TableField("u_name")
    private String uName;
    @TableField("record_desc")
    private String recordDesc;

    public String getRecordDesc() {
        return recordDesc;
    }

    public void setRecordDesc(String recordDesc) {
        this.recordDesc = recordDesc;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordAddr() {
        return recordAddr;
    }

    public void setRecordAddr(String recordAddr) {
        this.recordAddr = recordAddr;
    }

    public String getRecordMessage() {
        return recordMessage;
    }

    public void setRecordMessage(String recordMessage) {
        this.recordMessage = recordMessage;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}
