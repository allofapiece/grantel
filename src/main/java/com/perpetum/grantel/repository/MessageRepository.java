package com.perpetum.grantel.repository;

import com.perpetum.grantel.entity.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageRepository extends CrudRepository<Message, Long> {
}
