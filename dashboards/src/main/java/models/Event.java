package models;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Document(collection = "events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

	@Id
	private String eventUuid;

	private String eventSourceId;
	private String eventSource;
	private String eventNature;
	private int eventScore;
	private String eventItem;
	private String[] eventDetails;

	private LocalDateTime eventCreationDate;
	private String resource;


	public String getEventUuid() {
		return eventUuid;
	}

	public void setEventUuid(String eventUuid) {
		this.eventUuid = eventUuid;
	}


	public String getEventSourceId() {
		return eventSourceId;
	}

	public void setEventSourceId(String eventSourceId) {
		this.eventSourceId = eventSourceId;
	}

	public String getEventNature() {
		return eventNature;
	}

	public void setEventNature(String eventNature) {
		this.eventNature = eventNature;
	}

	public int getEventScore() {
		return eventScore;
	}

	public void setEventScore(int eventScore) {
		this.eventScore = eventScore;
	}

	public String[] getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String[] eventDetails) {
		this.eventDetails = eventDetails;
	}

	public LocalDateTime getEventCreationDate() {
		return eventCreationDate;
	}

	public void setEventCreationDate(LocalDateTime eventCreationDate) {
		this.eventCreationDate = eventCreationDate;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}
	
	public String getEventItem() {
		return eventItem;
	}

	public void setEventItem(String eventItem) {
		this.eventItem = eventItem;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}


	@Override
	public String toString() {
		return "Event [eventUuid=" + eventUuid + ", eventSourceId=" + eventSourceId + ", eventSource=" + eventSource
				+ ", eventNature=" + eventNature + ", eventScore=" + eventScore + "]";
	}


}
