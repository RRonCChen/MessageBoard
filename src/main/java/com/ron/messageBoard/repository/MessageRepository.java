package com.ron.messageBoard.repository;

import com.ron.messageBoard.entity.data.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findAllByTopicIdOrderByCreateDateDesc(Long topicId, Pageable pageable);
}
