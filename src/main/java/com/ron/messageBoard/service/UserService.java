package com.ron.messageBoard.service;

import com.ron.messageBoard.Exception.MessageBoardException;
import com.ron.messageBoard.entity.api.result.PageResultEntity;
import com.ron.messageBoard.entity.data.Message;
import com.ron.messageBoard.entity.data.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    User getUser(Long id) throws MessageBoardException;

    PageResultEntity<User> getUsers(Integer page, Integer size) throws MessageBoardException;

    void insertUser(String account, String password, String name) throws MessageBoardException;

    void updateUser(Long id, String password, String name) throws MessageBoardException;

    void deleteUser(Long id) throws MessageBoardException;
}
