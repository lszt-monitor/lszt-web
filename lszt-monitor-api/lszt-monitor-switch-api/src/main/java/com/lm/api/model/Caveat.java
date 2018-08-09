package com.lm.api.model;


import java.io.Serializable;
import java.util.Date;

public class Caveat  implements Serializable {
  /**
   * 报警表  id
   */
  private Integer idCall;
  /**
   * 告警级别：告警的严重程度 1一般  2 中等 3 严重
   */
  private Integer warnleave;
  /**
   * 属于哪个交换机的警告
   */
  private String fromguy;
  /**
   * 告警内容
   */
  private String warncontent;
  /**
   * 是否处理 1代表处理 0代表未处理'
   */
  private Integer dealwith;
  /**
   * 告警时间
   */
  private Date warntime;
  /**
   * 交换机id
   */
  private Integer switchId;


  public Integer getIdCall() {
    return idCall;
  }

  public void setIdCall(Integer idCall) {
    this.idCall = idCall;
  }


  public Integer getWarnleave() {
    return warnleave;
  }

  public void setWarnleave(Integer warnleave) {
    this.warnleave = warnleave;
  }


  public String getFromguy() {
    return fromguy;
  }

  public void setFromguy(String fromguy) {
    this.fromguy = fromguy;
  }


  public String getWarncontent() {
    return warncontent;
  }

  public void setWarncontent(String warncontent) {
    this.warncontent = warncontent;
  }


  public Integer getDealwith() {
    return dealwith;
  }

  public void setDealwith(Integer dealwith) {
    this.dealwith = dealwith;
  }


  public java.util.Date getWarntime() {
    return warntime;
  }

  public void setWarntime(Date warntime) {
    this.warntime = warntime;
  }


  public Integer getSwitchId() {
    return switchId;
  }

  public void setSwitchId(Integer switchId) {
    this.switchId = switchId;
  }

}
