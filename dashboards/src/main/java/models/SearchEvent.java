package models;

import java.io.Serializable;
import java.util.Date;

public class SearchEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String customer;
	private String service;
	private String resource;
	private String eventNature;
	private String timePeriod;
	private String item;
	private Date startDate;
	private Date endDate;
	private Integer pageSize;
	private Integer rowNumber;
	private String sortField;
	private String sortOrder;
	private String componentName;
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getEventNature() {
		return eventNature;
	}
	public void setEventNature(String eventNature) {
		this.eventNature = eventNature;
	}
	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	@Override
	public String toString() {
		return "SearchEvent [customer=" + customer + ", service=" + service + ", resource=" + resource
				+ ", eventNature=" + eventNature + ", timePeriod=" + timePeriod + ", item=" + item + ", startDate="
				+ startDate + ", endDate=" + endDate + ", pageSize=" + pageSize + ", rowNumber=" + rowNumber
				+ ", sortField=" + sortField + ", sortOrder=" + sortOrder + "]";
	}
	
	

}
