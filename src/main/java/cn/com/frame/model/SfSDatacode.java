package cn.com.frame.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sf_s_datacode")
public class SfSDatacode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID")
    private String uuid;

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

    private String a11;

    private String a12;

    private String a13;

    private String a14;

    private String a15;

    private String a16;

    private String a17;

    private String a18;

    private String a19;

    private String a20;

    private String a21;

    private String a22;

    private String a23;

    private String a24;

    private String a25;

    private String a26;

    private String a27;

    private String a28;

    private String a29;

    private String a30;

    private Double d01;

    private Double d02;

    private Double d03;

    private Double d04;

    private Double d05;

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

    private String b01;

    private String b02;

    private String b03;

    private String b04;

    private String b05;

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
     * @return a11
     */
    public String getA11() {
        return a11;
    }

    /**
     * @param a11
     */
    public void setA11(String a11) {
        this.a11 = a11;
    }

    /**
     * @return a12
     */
    public String getA12() {
        return a12;
    }

    /**
     * @param a12
     */
    public void setA12(String a12) {
        this.a12 = a12;
    }

    /**
     * @return a13
     */
    public String getA13() {
        return a13;
    }

    /**
     * @param a13
     */
    public void setA13(String a13) {
        this.a13 = a13;
    }

    /**
     * @return a14
     */
    public String getA14() {
        return a14;
    }

    /**
     * @param a14
     */
    public void setA14(String a14) {
        this.a14 = a14;
    }

    /**
     * @return a15
     */
    public String getA15() {
        return a15;
    }

    /**
     * @param a15
     */
    public void setA15(String a15) {
        this.a15 = a15;
    }

    /**
     * @return a16
     */
    public String getA16() {
        return a16;
    }

    /**
     * @param a16
     */
    public void setA16(String a16) {
        this.a16 = a16;
    }

    /**
     * @return a17
     */
    public String getA17() {
        return a17;
    }

    /**
     * @param a17
     */
    public void setA17(String a17) {
        this.a17 = a17;
    }

    /**
     * @return a18
     */
    public String getA18() {
        return a18;
    }

    /**
     * @param a18
     */
    public void setA18(String a18) {
        this.a18 = a18;
    }

    /**
     * @return a19
     */
    public String getA19() {
        return a19;
    }

    /**
     * @param a19
     */
    public void setA19(String a19) {
        this.a19 = a19;
    }

    /**
     * @return a20
     */
    public String getA20() {
        return a20;
    }

    /**
     * @param a20
     */
    public void setA20(String a20) {
        this.a20 = a20;
    }

    /**
     * @return a21
     */
    public String getA21() {
        return a21;
    }

    /**
     * @param a21
     */
    public void setA21(String a21) {
        this.a21 = a21;
    }

    /**
     * @return a22
     */
    public String getA22() {
        return a22;
    }

    /**
     * @param a22
     */
    public void setA22(String a22) {
        this.a22 = a22;
    }

    /**
     * @return a23
     */
    public String getA23() {
        return a23;
    }

    /**
     * @param a23
     */
    public void setA23(String a23) {
        this.a23 = a23;
    }

    /**
     * @return a24
     */
    public String getA24() {
        return a24;
    }

    /**
     * @param a24
     */
    public void setA24(String a24) {
        this.a24 = a24;
    }

    /**
     * @return a25
     */
    public String getA25() {
        return a25;
    }

    /**
     * @param a25
     */
    public void setA25(String a25) {
        this.a25 = a25;
    }

    /**
     * @return a26
     */
    public String getA26() {
        return a26;
    }

    /**
     * @param a26
     */
    public void setA26(String a26) {
        this.a26 = a26;
    }

    /**
     * @return a27
     */
    public String getA27() {
        return a27;
    }

    /**
     * @param a27
     */
    public void setA27(String a27) {
        this.a27 = a27;
    }

    /**
     * @return a28
     */
    public String getA28() {
        return a28;
    }

    /**
     * @param a28
     */
    public void setA28(String a28) {
        this.a28 = a28;
    }

    /**
     * @return a29
     */
    public String getA29() {
        return a29;
    }

    /**
     * @param a29
     */
    public void setA29(String a29) {
        this.a29 = a29;
    }

    /**
     * @return a30
     */
    public String getA30() {
        return a30;
    }

    /**
     * @param a30
     */
    public void setA30(String a30) {
        this.a30 = a30;
    }

    /**
     * @return d01
     */
    public Double getD01() {
        return d01;
    }

    /**
     * @param d01
     */
    public void setD01(Double d01) {
        this.d01 = d01;
    }

    /**
     * @return d02
     */
    public Double getD02() {
        return d02;
    }

    /**
     * @param d02
     */
    public void setD02(Double d02) {
        this.d02 = d02;
    }

    /**
     * @return d03
     */
    public Double getD03() {
        return d03;
    }

    /**
     * @param d03
     */
    public void setD03(Double d03) {
        this.d03 = d03;
    }

    /**
     * @return d04
     */
    public Double getD04() {
        return d04;
    }

    /**
     * @param d04
     */
    public void setD04(Double d04) {
        this.d04 = d04;
    }

    /**
     * @return d05
     */
    public Double getD05() {
        return d05;
    }

    /**
     * @param d05
     */
    public void setD05(Double d05) {
        this.d05 = d05;
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

    /**
     * @return b01
     */
    public String getB01() {
        return b01;
    }

    /**
     * @param b01
     */
    public void setB01(String b01) {
        this.b01 = b01;
    }

    /**
     * @return b02
     */
    public String getB02() {
        return b02;
    }

    /**
     * @param b02
     */
    public void setB02(String b02) {
        this.b02 = b02;
    }

    /**
     * @return b03
     */
    public String getB03() {
        return b03;
    }

    /**
     * @param b03
     */
    public void setB03(String b03) {
        this.b03 = b03;
    }

    /**
     * @return b04
     */
    public String getB04() {
        return b04;
    }

    /**
     * @param b04
     */
    public void setB04(String b04) {
        this.b04 = b04;
    }

    /**
     * @return b05
     */
    public String getB05() {
        return b05;
    }

    /**
     * @param b05
     */
    public void setB05(String b05) {
        this.b05 = b05;
    }
}