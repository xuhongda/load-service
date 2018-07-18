package com.xu.loadservicecrawler;

/**
 * @author xuhongda on 2018/6/14
 * pachong
 * dataplatform
 */

public class FaYuanLossBean {
    //黄琳迪 == 姓名
    private String iname;
    //33038219830****0367 == 身份证
    private String cardNum;
    //（2017）浙0302执9228号 == 案号
    private String caseCode;
    // 浙江 == 省份
    private String areaName;
    //温州市鹿城区人民法院 == 执行法院
    private String courtName;
    //有履行能力而拒不履行生效法律文书确定义务 == 失信被执行人行为具体情形
    private String disruptTypeName;
    //（2017）浙0302民初3166号，未履行执行标的300万元 == 生效法律文书确定的义务：
    private String duty;
    // 2018年06月11日 ==  发布时间
    private String publishDate;
    //全部未履行 == 被执行人的履行情况
    private String performance;

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getDisruptTypeName() {
        return disruptTypeName;
    }

    public void setDisruptTypeName(String disruptTypeName) {
        this.disruptTypeName = disruptTypeName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "FaYuanLossBean{" +
                "iname='" + iname + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", caseCode='" + caseCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", courtName='" + courtName + '\'' +
                ", disruptTypeName='" + disruptTypeName + '\'' +
                ", duty='" + duty + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", performance='" + performance + '\'' +
                '}';
    }
}
