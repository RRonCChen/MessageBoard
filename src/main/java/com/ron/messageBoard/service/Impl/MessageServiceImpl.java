package com.ron.messageBoard.service.Impl;

import com.ron.messageBoard.Exception.MessageBoardException;
import com.ron.messageBoard.Exception.MessageBoardNotFountException;
import com.ron.messageBoard.entity.api.result.MessageResultEntity;
import com.ron.messageBoard.entity.api.result.PageResultEntity;
import com.ron.messageBoard.entity.data.Message;
import com.ron.messageBoard.repository.MessageRepository;
import com.ron.messageBoard.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MessageResultEntity getMessage(Long id) throws MessageBoardException {
        Message message = messageRepository.findById(id).orElseThrow(() -> new MessageBoardNotFountException(String.format("查無此id : %d", id)));
        MessageResultEntity result = new MessageResultEntity();
        BeanUtils.copyProperties(message, result);
        if (null != message.getUser()) {
            result.setUserName(message.getUser().getName());
        }
        if (null != message.getTopic()) {
            result.setTopicName(message.getTopic().getName());
        }

        return result;
    }

    @Override
    public PageResultEntity<MessageResultEntity> getTopicMessages(Long topicId, Integer page, Integer size) throws MessageBoardException {
        Page<Message> pageResult = messageRepository.findAllByTopicIdOrderByCreateDateDesc(topicId,
                PageRequest.of(page, size));

        List<MessageResultEntity> elementList = new ArrayList<>();
        pageResult.getContent().forEach(message -> {
            MessageResultEntity element = new MessageResultEntity();
            BeanUtils.copyProperties(message, element);
            if (null != message.getUser()) {
                element.setUserName(message.getUser().getName());
            }
            if (null != message.getTopic()) {
                element.setTopicName(message.getTopic().getName());
            }
            elementList.add(element);
        });

        PageResultEntity<MessageResultEntity> pageResultEntity = new PageResultEntity<>();
        pageResultEntity.setEachPageSize(pageResult.getSize());
        pageResultEntity.setTotalElements(pageResult.getTotalElements());
        pageResultEntity.setTotalPages(pageResult.getTotalPages());
        pageResultEntity.setThisPageSize(pageResult.getNumberOfElements());
        pageResultEntity.setElementList(elementList);

        return pageResultEntity;
    }

    @Override
    public void insertMessage(Long userId, Long topicId, String content) throws MessageBoardException {
        Message message = new Message();
        message.setContent(content);
        message.setTopicId(topicId);
        message.setUserId(userId);
        message.setModifyDate(LocalDateTime.now());
        message.setCreateDate(LocalDateTime.now());
        messageRepository.save(message);
    }

    @Override
    public void updateMessage(Long id, String content) throws MessageBoardException {
        Message message = messageRepository.findById(id).orElseThrow(() -> new MessageBoardNotFountException(String.format("查無此id : %s ", id)));

        if ((null != content && !content.isBlank())) {
            message.setContent(content);
        }
        message.setModifyDate(LocalDateTime.now());
        messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) throws MessageBoardException {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        } else {
            throw new MessageBoardNotFountException(String.format("查無此id : %d 重複", id));
        }
    }
}
