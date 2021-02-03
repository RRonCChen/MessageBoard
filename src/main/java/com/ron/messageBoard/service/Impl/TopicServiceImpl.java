package com.ron.messageBoard.service.Impl;

import com.ron.messageBoard.Exception.MessageBoardException;
import com.ron.messageBoard.Exception.MessageBoardNotFountException;
import com.ron.messageBoard.Exception.MessageBoardParameterErrorException;
import com.ron.messageBoard.entity.data.Topic;
import com.ron.messageBoard.entity.data.User;
import com.ron.messageBoard.repository.TopicRepository;
import com.ron.messageBoard.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;


    @Override
    public Topic getTopic(Long id) throws MessageBoardException {
        return topicRepository.findById(id).orElseThrow(() -> new MessageBoardNotFountException(String.format("查無此id : %d", id)));
    }

    @Override
    public List<Topic> getTopics() throws MessageBoardException {
        List<Topic> result = topicRepository.findAll();

        if (result.size() == 0) {
            throw new MessageBoardNotFountException("查無資料");
        }

        return result;
    }

    @Override
    public void insertTopic(String name) throws MessageBoardException {
        topicRepository.findByName(name).ifPresent(user -> {
            throw new MessageBoardParameterErrorException(String.format("Topic name : %s 重複", name));
        });

        Topic topic = new Topic();
        topic.setName(name);
        topic.setModifyDate(LocalDateTime.now());
        topic.setCreateDate(LocalDateTime.now());
        topicRepository.save(topic);
    }

    @Override
    public void updateTopicName(Long id, String name) throws MessageBoardException {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new MessageBoardNotFountException(String.format("查無此id : %s ", id)));

        if ((null != name && !name.isBlank())) {
            topic.setName(name);
        }

        topic.setModifyDate(LocalDateTime.now());
        topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) throws MessageBoardException {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
        } else {
            throw new MessageBoardNotFountException(String.format("查無此id : %d 重複", id));
        }
    }
}
