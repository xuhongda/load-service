package com.xu.bean;

public class Details {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 违章时间
	 */
	private String time;

	/**
	 * 违章地点
	 */

	private String location;

	/**
	 * 违章事由
	 */
	private String describe;

	/**
	 * 本次扣分
	 */
	private String point;

	/**
	 * 本次罚款
	 */
	private String fine;

	/**
	 * 服务费
	 */
	private String serviceFee;
	
	/**
	 * 违章id
	 */
   private Integer  pencancyId;
   
   
	public Integer getPencancyId() {
		return pencancyId;
	}

	public void setPencancyId(Integer pencancyId) {
		this.pencancyId = pencancyId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

}
