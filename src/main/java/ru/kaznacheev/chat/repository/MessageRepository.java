package ru.kaznacheev.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaznacheev.chat.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
