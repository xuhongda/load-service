package com.xu.bean;

import java.util.List;

public class Pencancy {
	/**
	 *    id
	 */
	private Integer id;
	
	/**
	 *    违章总次数
	 */
	private String totalCount;
	
	/**
	 *    总罚款
	 */
	private String totalFine;
	
	/**
	 *    总扣分
	 */
	private String totalPointReducted;
	
	/**
	 *    关联car
	 */
	private Integer carId;
	/**
	 *    违章详情
	 */
	private List<Details> details;
	
	private Integer totalMoney;
	
	private Integer totalPoint;
	
	private Integer minTotalCount;
	
	private Integer maxTotalCount;
	
	private Integer minTotalPoint;

	private Integer maxTotalPoint;
	
	

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getMinTotalCount() {
		return minTotalCount;
	}

	public void setMinTotalCount(Integer minTotalCount) {
		this.minTotalCount = minTotalCount;
	}

	public Integer getMaxTotalCount() {
		return maxTotalCount;
	}

	public void setMaxTotalCount(Integer maxTotalCount) {
		this.maxTotalCount = maxTotalCount;
	}

	public Integer getMinTotalPoint() {
		return minTotalPoint;
	}

	public void setMinTotalPoint(Integer minTotalPoint) {
		this.minTotalPoint = minTotalPoint;
	}

	public Integer getMaxTotalPoint() {
		return maxTotalPoint;
	}

	public void setMaxTotalPoint(Integer maxTotalPoint) {
		this.maxTotalPoint = maxTotalPoint;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalFine() {
		return totalFine;
	}

	public void setTotalFine(String totalFine) {
		this.totalFine = totalFine;
	}

	public String getTotalPointReducted() {
		return totalPointReducted;
	}

	public void setTotalPointReducted(String totalPointReducted) {
		this.totalPointReducted = totalPointReducted;
	}

	public List<Details> getDetails() {
		return details;
	}

	public void setDetails(List<Details> details) {
		this.details = details;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
	
}
