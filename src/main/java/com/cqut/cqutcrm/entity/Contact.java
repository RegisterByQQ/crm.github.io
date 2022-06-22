package com.cqut.cqutcrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("contact")
public class Contact {
    @TableId(type = IdType.AUTO,value = "con_id")
    private Integer conId;
    @TableField("con_name")
    private String conName;
    @TableField("con_sex")
    private String conSex;
    @TableField("con_job")
    private String conJob;
    @TableField("con_tel")
    private String conTel;
    @TableField("con_phone")
    private String conPhone;
    @TableField("con_note")
    private String conNote;
    @TableField("cus_id")
    private Integer cusId;

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConSex() {
        return conSex;
    }

    public void setConSex(String conSex) {
        this.conSex = conSex;
    }

    public String getConJob() {
        return conJob;
    }

    public void setConJob(String conJob) {
        this.conJob = conJob;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getConPhone() {
        return conPhone;
    }

    public void setConPhone(String conPhone) {
        this.conPhone = conPhone;
    }

    public String getConNote() {
        return conNote;
    }

    public void setConNote(String conNote) {
        this.conNote = conNote;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}
