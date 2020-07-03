package controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import models.Event;
import models.SearchEvent;
import services.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Resource
	EventService eventService;
	
	/**
	 * Fetch all the Events created
	 * @return List of Events
	 */
	@ApiOperation(value = "Retrieve list of Events", notes = "This operation is used to find list of Events")
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<List<Event>> fetchEvents() {
		List<Event> event=eventService.fetchEventService();
		return new ResponseEntity<>(event, HttpStatus.OK);
	}

	/**
	 * Fetch event by event ID 
	 * @param eventId
	 * @return Event
	 */
	@ApiOperation(value = "Retrieve details of Event", notes = "This operation is used to find detail of Event")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<Event> fetchEventById(@PathVariable("id") String id) {
		Event event=eventService.fetchEventServiceById(id);
		return new ResponseEntity<>(event,HttpStatus.OK);
			
	}
	
	/**
	 * Fetch total no of records from Event List
	 * @return count
	 */
	@ApiOperation(value = "Retrieve total count of Events", notes = "This operation is used to get total count of Events", hidden=true)
	@RequestMapping(value = "count", method = RequestMethod.POST)
	public Long fetchEventListCount(@RequestBody SearchEvent searchEvent) {
		return eventService.fetchEventListCount(searchEvent.getCustomer(), searchEvent.getService(),
				searchEvent.getResource(), searchEvent.getEventNature(),searchEvent.getItem(),
				searchEvent.getTimePeriod(), searchEvent.getStartDate(), searchEvent.getEndDate(),searchEvent.getComponentName());
	}
	
	
}
