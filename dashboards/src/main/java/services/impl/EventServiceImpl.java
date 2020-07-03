package services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import dao.EventDao;
import models.Event;
import services.EventService;
import util.Constants;
import util.SeenUtility;

@Component
public class EventServiceImpl implements EventService {

	@Resource
	EventDao eventDao;
	
	@Value( "${404}" )
	private String error;
	
	long eventCount;
	long alarmCount;
	
	
	@Override
	public List<Event> fetchEventService() {
		return eventDao.fetchAllEvents();
	}

	@Override
	public Event fetchEventServiceById(String id) {
		return eventDao.fetchEventById(id);
	}

	
	/*
	 * Fetch total no of records from Event List
	 */
	@Override
	public Long fetchEventListCount(String customer, String service, String resource, String eventNature,String item,
			String timePeriod, Date startDate, Date endDate,String componentName) {
		LocalDateTime eventCreationStartDate = null;
		LocalDateTime eventCreationEndDate = null;
		if (Constants.CUSTOM.equalsIgnoreCase(timePeriod)) {
			if (Optional.ofNullable(startDate).isPresent() && Optional.ofNullable(endDate).isPresent()) {
				eventCreationStartDate = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
				eventCreationEndDate = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
			}
		} else {
			eventCreationStartDate = SeenUtility.calculateTime(timePeriod);
			eventCreationEndDate = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
		}
		return eventDao.fetchEventListCount(StringUtils.isEmpty(customer) ? null : customer,
				StringUtils.isEmpty(service) ? null : service, StringUtils.isEmpty(resource) ? null : resource,
				StringUtils.isEmpty(item) ? null : item, StringUtils.isEmpty(eventNature) ? null : eventNature,
				eventCreationStartDate, eventCreationEndDate,componentName);
	}

	

	
}
