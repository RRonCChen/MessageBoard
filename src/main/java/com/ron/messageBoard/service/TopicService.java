package com.ron.messageBoard.service;

import com.ron.messageBoard.exception.MessageBoardException;
import com.ron.messageBoard.entity.data.Topic;

import java.util.List;

public interface TopicService {

    Topic getTopic(Long id) throws MessageBoardException;

    List<Topic> getTopics() throws MessageBoardException;

    void insertTopic(String name) throws MessageBoardException;

    void updateTopicName(Long id, String name) throws MessageBoardException;

    void deleteTopic(Long id) throws MessageBoardException;
}
