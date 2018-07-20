	package com.xu.bean;
	
	public class Car {
	
		/**
		 * id
		 */
		private Integer id;
	
		/**
		 * 车牌号码
		 */
		private String carNumber;
	
		/**
		 * 发动机号
		 */
		private String engineNumber;
	
		/**
		 * 车架号
		 */
		private String classNumber;
	
		/**
		 * 归属地简称
		 */
		private String belongedProviceShortName;
	
		/**
		 * 违章情况
		 */
		private Pencancy pencancy;
	
		public String getCarNumber() {
			return carNumber;
		}
	
		public void setCarNumber(String carNumber) {
			this.carNumber = carNumber;
		}
	
		public String getEngineNumber() {
			return engineNumber;
		}
	
		public void setEngineNumber(String engineNumber) {
			this.engineNumber = engineNumber;
		}
	
		public String getClassNumber() {
			return classNumber;
		}
	
		public void setClassNumber(String classNumber) {
			this.classNumber = classNumber;
		}
	
		public String getBelongedProviceShortName() {
			return belongedProviceShortName;
		}
	
		public void setBelongedProviceShortName(String belongedProviceShortName) {
			this.belongedProviceShortName = belongedProviceShortName;
		}
	
		public Pencancy getPencancy() {
			return pencancy;
		}
	
		public void setPencancy(Pencancy pencancy) {
			this.pencancy = pencancy;
		}
	
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
	}
