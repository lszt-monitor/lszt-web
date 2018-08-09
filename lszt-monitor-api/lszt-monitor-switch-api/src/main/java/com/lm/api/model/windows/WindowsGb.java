package com.lm.api.model.windows;
public class WindowsGb {
    // 盘的总数
    private Double hardDisk;
    // 盘的使用
    private Double used_GB;
    // 盘的可用
    private Double be_Usable_Double_GB;

    //盘的使用率
    private Double usageRate;

    public WindowsGb() {
        super();

    }

    public Double getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(Double hardDisk) {
        this.hardDisk = hardDisk;
    }

    public Double getUsed_GB() {
        return used_GB;
    }

    public void setUsed_GB(Double used_GB) {
        this.used_GB = used_GB;
    }

    public Double getBe_Usable_Double_GB() {
        return be_Usable_Double_GB;
    }

    public void setBe_Usable_Double_GB(Double be_Usable_Double_GB) {
        this.be_Usable_Double_GB = be_Usable_Double_GB;
    }

    public Double getUsageRate() {
        return usageRate;
    }

    public void setUsageRate(Double usageRate) {
        this.usageRate = usageRate;
    }
}
