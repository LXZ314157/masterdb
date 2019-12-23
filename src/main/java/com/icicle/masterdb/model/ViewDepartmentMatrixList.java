package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_department_matrix_list")
public class ViewDepartmentMatrixList {

    @Id
    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "principal_level1")
    private String principalLevel1;

    @Column(name = "principal_level1_code")
    private String principalLevel1Code;

    @Column(name = "principal_level2")
    private String principalLevel2;

    @Column(name = "principal_level2_code")
    private String principalLevel2Code;

    @Column(name = "chief_code")
    private String chiefCode;

    private String chief;

    @Column(name = "vice_president_code")
    private String vicePresidentCode;

    @Column(name = "vice_president")
    private String vicePresident;

    @Column(name = "president_code")
    private String presidentCode;

    private String president;

    @Column(name = "hr_bp_code")
    private String hrBpCode;

    @Column(name = "hr_bp")
    private String hrBp;

    @Column(name = "member01_code")
    private String member01Code;

    private String member01;

    @Column(name = "member02_code")
    private String member02Code;

    private String member02;

    @Column(name = "member03_code")
    private String member03Code;

    private String member03;

    @Column(name = "member04_code")
    private String member04Code;

    private String member04;

    @Column(name = "member05_code")
    private String member05Code;

    private String member05;

    @Column(name = "member06_code")
    private String member06Code;

    private String member06;

    @Column(name = "member07_code")
    private String member07Code;

    private String member07;

    @Column(name = "member08_code")
    private String member08Code;

    private String member08;

    @Column(name = "member09_code")
    private String member09Code;

    private String member09;

    @Column(name = "member10_code")
    private String member10Code;

    private String member10;


    public String getPrincipalLevel1Code() {
        return principalLevel1Code;
    }

    public void setPrincipalLevel1Code(String principalLevel1Code) {
        this.principalLevel1Code = principalLevel1Code;
    }

    public String getPrincipalLevel2Code() {
        return principalLevel2Code;
    }

    public void setPrincipalLevel2Code(String principalLevel2Code) {
        this.principalLevel2Code = principalLevel2Code;
    }

    public String getChiefCode() {
        return chiefCode;
    }

    public void setChiefCode(String chiefCode) {
        this.chiefCode = chiefCode;
    }

    public String getVicePresidentCode() {
        return vicePresidentCode;
    }

    public void setVicePresidentCode(String vicePresidentCode) {
        this.vicePresidentCode = vicePresidentCode;
    }

    public String getPresidentCode() {
        return presidentCode;
    }

    public void setPresidentCode(String presidentCode) {
        this.presidentCode = presidentCode;
    }

    public String getHrBpCode() {
        return hrBpCode;
    }

    public void setHrBpCode(String hrBpCode) {
        this.hrBpCode = hrBpCode;
    }

    public String getMember01Code() {
        return member01Code;
    }

    public void setMember01Code(String member01Code) {
        this.member01Code = member01Code;
    }

    public String getMember02Code() {
        return member02Code;
    }

    public void setMember02Code(String member02Code) {
        this.member02Code = member02Code;
    }

    public String getMember03Code() {
        return member03Code;
    }

    public void setMember03Code(String member03Code) {
        this.member03Code = member03Code;
    }

    public String getMember04Code() {
        return member04Code;
    }

    public void setMember04Code(String member04Code) {
        this.member04Code = member04Code;
    }

    public String getMember05Code() {
        return member05Code;
    }

    public void setMember05Code(String member05Code) {
        this.member05Code = member05Code;
    }

    public String getMember06Code() {
        return member06Code;
    }

    public void setMember06Code(String member06Code) {
        this.member06Code = member06Code;
    }

    public String getMember07Code() {
        return member07Code;
    }

    public void setMember07Code(String member07Code) {
        this.member07Code = member07Code;
    }

    public String getMember08Code() {
        return member08Code;
    }

    public void setMember08Code(String member08Code) {
        this.member08Code = member08Code;
    }

    public String getMember09Code() {
        return member09Code;
    }

    public void setMember09Code(String member09Code) {
        this.member09Code = member09Code;
    }

    public String getMember10Code() {
        return member10Code;
    }

    public void setMember10Code(String member10Code) {
        this.member10Code = member10Code;
    }

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
}