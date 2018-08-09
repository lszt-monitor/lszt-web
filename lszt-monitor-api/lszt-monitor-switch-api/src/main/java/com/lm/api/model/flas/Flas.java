package com.lm.api.model.flas;

import java.io.Serializable;
import java.util.Date;
public class Flas  implements Serializable {
    // 闪存id
    private Integer idSwitchFlas;
    private Long id;
    // 闪存百分比
    private Integer utilFlas;
    // 闪存总
    private Long totalFlas;
    // 闪存已用
    private Long usedFlas;
    // 存入日期
    private Date time;

    public Flas() {
    }

    public Integer getIdSwitchFlas() {
        return idSwitchFlas;
    }

    public void setIdSwitchFlas(Integer idSwitchFlas) {
        this.idSwitchFlas = idSwitchFlas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUtilFlas() {
        return utilFlas;
    }

    public void setUtilFlas(Integer utilFlas) {
        this.utilFlas = utilFlas;
    }

    public Long getTotalFlas() {
        return totalFlas;
    }

    public void setTotalFlas(Long totalFlas) {
        this.totalFlas = totalFlas;
    }

    public Long getUsedFlas() {
        return usedFlas;
    }

    public void setUsedFlas(Long usedFlas) {
        this.usedFlas = usedFlas;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Flas [idSwitchFlas=" + idSwitchFlas + ", id=" + id + ", utilFlas=" + utilFlas + ", totalFlas="
                + totalFlas + ", usedFlas=" + usedFlas + ", time=" + time + "]";
    }

    public Flas(Integer idSwitchFlas, Integer utilFlas, Long totalFlas, Long usedFlas, Date time) {
        super();
        this.idSwitchFlas = idSwitchFlas;
        this.utilFlas = utilFlas;
        this.totalFlas = totalFlas;
        this.usedFlas = usedFlas;
        this.time = time;
    }


}
