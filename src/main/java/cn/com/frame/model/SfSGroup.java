package cn.com.frame.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sf_s_group")
public class SfSGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "GROUPID")
    private String groupid;

    @Column(name = "GROUPNAME")
    private String groupname;

    @Column(name = "GROUPSTATUS")
    private String groupstatus;

    private String a01;

    private String a02;

    private String a03;

    private String a04;

    private String a05;

    private String a06;

    private String a07;

    private String a08;

    private String a09;

    private String a10;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "UPDATER")
    private String updater;

    @Column(name = "UPDATETIME")
    private String updatetime;

    @Column(name = "EFFECTIVE")
    private Integer effective;

    @Column(name = "CREATEDAY")
    private Date createday;

    @Column(name = "UPDATEDAY")
    private Date updateday;

    @Column(name = "UPDATECOUNT")
    private Double updatecount;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return GROUPID
     */
    public String getGroupid() {
        return groupid;
    }

    /**
     * @param groupid
     */
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    /**
     * @return GROUPNAME
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * @param groupname
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * @return GROUPSTATUS
     */
    public String getGroupstatus() {
        return groupstatus;
    }

    /**
     * @param groupstatus
     */
    public void setGroupstatus(String groupstatus) {
        this.groupstatus = groupstatus;
    }

    /**
     * @return a01
     */
    public String getA01() {
        return a01;
    }

    /**
     * @param a01
     */
    public void setA01(String a01) {
        this.a01 = a01;
    }

    /**
     * @return a02
     */
    public String getA02() {
        return a02;
    }

    /**
     * @param a02
     */
    public void setA02(String a02) {
        this.a02 = a02;
    }

    /**
     * @return a03
     */
    public String getA03() {
        return a03;
    }

    /**
     * @param a03
     */
    public void setA03(String a03) {
        this.a03 = a03;
    }

    /**
     * @return a04
     */
    public String getA04() {
        return a04;
    }

    /**
     * @param a04
     */
    public void setA04(String a04) {
        this.a04 = a04;
    }

    /**
     * @return a05
     */
    public String getA05() {
        return a05;
    }

    /**
     * @param a05
     */
    public void setA05(String a05) {
        this.a05 = a05;
    }

    /**
     * @return a06
     */
    public String getA06() {
        return a06;
    }

    /**
     * @param a06
     */
    public void setA06(String a06) {
        this.a06 = a06;
    }

    /**
     * @return a07
     */
    public String getA07() {
        return a07;
    }

    /**
     * @param a07
     */
    public void setA07(String a07) {
        this.a07 = a07;
    }

    /**
     * @return a08
     */
    public String getA08() {
        return a08;
    }

    /**
     * @param a08
     */
    public void setA08(String a08) {
        this.a08 = a08;
    }

    /**
     * @return a09
     */
    public String getA09() {
        return a09;
    }

    /**
     * @param a09
     */
    public void setA09(String a09) {
        this.a09 = a09;
    }

    /**
     * @return a10
     */
    public String getA10() {
        return a10;
    }

    /**
     * @param a10
     */
    public void setA10(String a10) {
        this.a10 = a10;
    }

    /**
     * @return OWNER
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return UPDATER
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * @param updater
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * @return UPDATETIME
     */
    public String getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return EFFECTIVE
     */
    public Integer getEffective() {
        return effective;
    }

    /**
     * @param effective
     */
    public void setEffective(Integer effective) {
        this.effective = effective;
    }

    /**
     * @return CREATEDAY
     */
    public Date getCreateday() {
        return createday;
    }

    /**
     * @param createday
     */
    public void setCreateday(Date createday) {
        this.createday = createday;
    }

    /**
     * @return UPDATEDAY
     */
    public Date getUpdateday() {
        return updateday;
    }

    /**
     * @param updateday
     */
    public void setUpdateday(Date updateday) {
        this.updateday = updateday;
    }

    /**
     * @return UPDATECOUNT
     */
    public Double getUpdatecount() {
        return updatecount;
    }

    /**
     * @param updatecount
     */
    public void setUpdatecount(Double updatecount) {
        this.updatecount = updatecount;
    }
}