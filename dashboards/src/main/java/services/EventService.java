package services;

import java.util.Date;
import java.util.List;

import models.Event;

public interface EventService {

	public List<Event> fetchEventService();

	public Event fetchEventServiceById(String id);

	
	public Long fetchEventListCount(String customer, String service, String resource, String alarmNature,
			String timePeriod, String item, Date startDate, Date endDate,String componentName);



}
