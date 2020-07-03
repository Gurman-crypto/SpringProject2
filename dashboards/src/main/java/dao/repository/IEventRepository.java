package dao.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import models.Event;

public interface IEventRepository extends MongoRepository<Event, Serializable> {

	public boolean existsByEventSourceId(String eventSourceId);

	public List<Event> findByEventSourceId(String eventSourceId);

	public Event findById(String id);

}
