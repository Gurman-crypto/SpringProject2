package dao;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import dao.repository.IEventRepository;
import models.Event;

@Repository
public class EventDao {

	@Resource
	IEventRepository eventRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Value("${paging.defaultSize}")
	private Integer defaultSize;
	@Value("${paging.defaultPage}")
	private Integer defaultPage;
	@Value("${paging.defaultEventSortField}")
	private String defaultSortField;
	@Value("${paging.defaultSortOrder}")
	private String defaultSortOrder;
	private static final Logger LOGGER = LoggerFactory.getLogger(EventDao.class);
	
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final String EVENTS = "events";
	public static final String EVENT_NATURE ="eventNature";
	public static final String CUSTOMER = "customerName";
	
	public void pushEventData(List<Event> events) {
		eventRepository.saveAll(events);
	}

	public void pushEvent(Event event) {
		eventRepository.save(event);
	}


	public boolean checkByEventSourceId(String eventSourceId) {
		return eventRepository.existsByEventSourceId(eventSourceId);
	}

	public List<Event> findByEventSourceId(String eventSourceId) {
		return eventRepository.findByEventSourceId(eventSourceId);
	}

	public List<Event> fetchAllEvents() {
		return eventRepository.findAll();
	}

	public void deleteEvent(Event event) {
		eventRepository.delete(event);
	}

	public Event fetchEventById(String id) {
		return eventRepository.findById(id);
	}

	public List<Event> fetchEventList() {
		return eventRepository.findAll();
	}

	public List<Event> fetchEventList(String customer, String service, String resource, String item, String nature,
			LocalDateTime startDate, LocalDateTime endDate) {
		Query dataquery = new Query();
		Optional.ofNullable(customer).ifPresent(cust -> dataquery.addCriteria(Criteria.where(CUSTOMER).in(Arrays.asList(cust.split(",")))));
		Optional.ofNullable(service).ifPresent(ser -> dataquery.addCriteria(Criteria.where("parentService").in(Arrays.asList(ser.split(",")))));
		Optional.ofNullable(resource).ifPresent(res -> dataquery.addCriteria(Criteria.where("eventSource").is(res)));
		Optional.ofNullable(item).ifPresent(itm -> dataquery.addCriteria(Criteria.where("eventItem").is(itm)));
		Optional.ofNullable(nature).ifPresent(nat -> dataquery.addCriteria(Criteria.where("eventNature").is(nat)));
		if (Optional.ofNullable(startDate).isPresent() && Optional.ofNullable(endDate).isPresent())
			dataquery.addCriteria(Criteria.where("eventCreationDate").ne(null).andOperator(
					Criteria.where("eventCreationDate").gte(startDate),
					Criteria.where("eventCreationDate").lte(endDate)));
		LOGGER.info("Event dataquery========" + dataquery.toString());
		List<Event> eventList = mongoTemplate.find(dataquery, Event.class);
		LOGGER.info("eventList size============>" + eventList.size());
		return eventList;
	}
	
	/*
	 * Fetch total no of events on the basis of searching criteria.
	 */
	public Long fetchEventListCount(String customer, String service, String resource, String item, String eventNature,
			LocalDateTime startDate, LocalDateTime endDate,String componentName) {
		Query dataquery = new Query();
		Optional.ofNullable(customer).ifPresent(cust -> dataquery.addCriteria(Criteria.where(CUSTOMER).in(Arrays.asList(cust.split(",")))));
		Optional.ofNullable(service).ifPresent(ser -> dataquery.addCriteria(Criteria.where("parentService").in(Arrays.asList(ser.split(",")))));
		Optional.ofNullable(resource).ifPresent(res -> dataquery.addCriteria(Criteria.where("eventSource").is(res)));
		Optional.ofNullable(item).ifPresent(itm -> dataquery.addCriteria(Criteria.where("eventItem").is(itm)));
		Optional.ofNullable(eventNature).ifPresent(nat -> dataquery.addCriteria(Criteria.where("eventNature").is(nat)));
		Optional.ofNullable(componentName).ifPresent(compName -> dataquery.addCriteria(Criteria.where("eventAttachedResource").is(compName)));
		
		if (Optional.ofNullable(startDate).isPresent() && Optional.ofNullable(endDate).isPresent())
			dataquery.addCriteria(Criteria.where("eventCreationDate").ne(null).andOperator(
					Criteria.where("eventCreationDate").gte(startDate),
					Criteria.where("eventCreationDate").lte(endDate)));
		
		LOGGER.info("Event dataquery========" + dataquery.toString());
		return mongoTemplate.count(dataquery, Event.class);
	}


	public long fetchEventCount() {
		return eventRepository.count();
	}
	
}
