package com.icicle.masterdb.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import javax.persistence.*;

@Table(name = "department_matrix")
public class DepartmentMatrix {
    @Id
    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "principal_level1")
    private String principalLevel1;

    @Column(name = "principal_level2")
    private String principalLevel2;

    private String chief;

    @Column(name = "vice_president")
    private String vicePresident;

    private String president;

    @Column(name = "hr_bp")
    private String hrBp;

    private String member01;

    private String member02;

    private String member03;

    private String member04;

    private String member05;

    private String member06;

    private String member07;

    private String member08;

    private String member09;

    private String member10;

    private String creater;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_update_by")
    private String lastUpdateBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return department_id
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return principal_level1
     */
    public String getPrincipalLevel1() {
        return principalLevel1;
    }

    /**
     * @param principalLevel1
     */
    public void setPrincipalLevel1(String principalLevel1) {
        this.principalLevel1 = principalLevel1;
    }

    /**
     * @return principal_level2
     */
    public String getPrincipalLevel2() {
        return principalLevel2;
    }

    /**
     * @param principalLevel2
     */
    public void setPrincipalLevel2(String principalLevel2) {
        this.principalLevel2 = principalLevel2;
    }

    /**
     * @return chief
     */
    public String getChief() {
        return chief;
    }

    /**
     * @param chief
     */
    public void setChief(String chief) {
        this.chief = chief;
    }

    /**
     * @return vice_president
     */
    public String getVicePresident() {
        return vicePresident;
    }

    /**
     * @param vicePresident
     */
    public void setVicePresident(String vicePresident) {
        this.vicePresident = vicePresident;
    }

    /**
     * @return president
     */
    public String getPresident() {
        return president;
    }

    /**
     * @param president
     */
    public void setPresident(String president) {
        this.president = president;
    }

    /**
     * @return hr_bp
     */
    public String getHrBp() {
        return hrBp;
    }

    /**
     * @param hrBp
     */
    public void setHrBp(String hrBp) {
        this.hrBp = hrBp;
    }

    /**
     * @return member01
     */
    public String getMember01() {
        return member01;
    }

    /**
     * @param member01
     */
    public void setMember01(String member01) {
        this.member01 = member01;
    }

    /**
     * @return member02
     */
    public String getMember02() {
        return member02;
    }

    /**
     * @param member02
     */
    public void setMember02(String member02) {
        this.member02 = member02;
    }

    /**
     * @return member03
     */
    public String getMember03() {
        return member03;
    }

    /**
     * @param member03
     */
    public void setMember03(String member03) {
        this.member03 = member03;
    }

    /**
     * @return member04
     */
    public String getMember04() {
        return member04;
    }

    /**
     * @param member04
     */
    public void setMember04(String member04) {
        this.member04 = member04;
    }

    /**
     * @return member05
     */
    public String getMember05() {
        return member05;
    }

    /**
     * @param member05
     */
    public void setMember05(String member05) {
        this.member05 = member05;
    }

    /**
     * @return member06
     */
    public String getMember06() {
        return member06;
    }

    /**
     * @param member06
     */
    public void setMember06(String member06) {
        this.member06 = member06;
    }

    /**
     * @return member07
     */
    public String getMember07() {
        return member07;
    }

    /**
     * @param member07
     */
    public void setMember07(String member07) {
        this.member07 = member07;
    }

    /**
     * @return member08
     */
    public String getMember08() {
        return member08;
    }

    /**
     * @param member08
     */
    public void setMember08(String member08) {
        this.member08 = member08;
    }

    /**
     * @return member09
     */
    public String getMember09() {
        return member09;
    }

    /**
     * @param member09
     */
    public void setMember09(String member09) {
        this.member09 = member09;
    }

    /**
     * @return member10
     */
    public String getMember10() {
        return member10;
    }

    /**
     * @param member10
     */
    public void setMember10(String member10) {
        this.member10 = member10;
    }

    /**
     * @return creater
     */
    public String getCreater() {
        return creater;
    }

    /**
     * @param creater
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return last_update_by
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * @param lastUpdateBy
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * @return last_update_date
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}