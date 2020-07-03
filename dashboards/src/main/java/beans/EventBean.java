package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import models.Event;

@Component
public class EventBean {

	//@Autowired
	//private EventFacade eventFacade;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(EventBean.class);
	public void loadEvents(List<Event> eventRulesList, String product) {
		
		List<Event> eventList = new ArrayList<>();
		LOGGER.info("====Event processing Start=====");
		LOGGER.info("====Delete events Start=====");
		LOGGER.info("====Events processing of easy go completed=====");
	}
	
}
