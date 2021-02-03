package com.ron.messageBoard.service.Impl;

import com.ron.messageBoard.exception.MessageBoardException;
import com.ron.messageBoard.exception.MessageBoardNotFountException;
import com.ron.messageBoard.exception.MessageBoardParameterErrorException;
import com.ron.messageBoard.entity.api.result.PageResultEntity;
import com.ron.messageBoard.entity.data.User;
import com.ron.messageBoard.repository.UserRepository;
import com.ron.messageBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) throws MessageBoardException {
        return userRepository.findById(id).orElseThrow(() -> new MessageBoardNotFountException(String.format("查無此id : %d", id)));
    }

    @Override
    public PageResultEntity<User> getUsers(Integer page, Integer size) throws MessageBoardException {
        Page<User> pageResult = userRepository.findAll(
                PageRequest.of(page, size));

        PageResultEntity<User> pageResultEntity = new PageResultEntity<>();
        pageResultEntity.setEachPageSize(pageResult.getSize());
        pageResultEntity.setTotalElements(pageResult.getTotalElements());
        pageResultEntity.setTotalPages(pageResult.getTotalPages/**/());
        pageResultEntity.setThisPageSize(pageResult.getNumberOfElements());
        pageResultEntity.setElementList(pageResult.getContent());

        return pageResultEntity;
    }

    @Override
    public void insertUser(String account, String password, String name) throws MessageBoardException {
        userRepository.findByAccount(account).ifPresent(user -> {
            throw new MessageBoardParameterErrorException(String.format("Account : %s 重複", account));
        });

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setName(name);
        user.setModifyDate(LocalDateTime.now());
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, String password, String name) throws MessageBoardException {

        User user = userRepository.findById(id).orElseThrow(() -> new MessageBoardNotFountException(String.format("查無此id : %s ", id)));

        if ((null != password && !password.isBlank())) {
            user.setPassword(password);
        }
        if ((null != name && !name.isBlank())) {
            user.setName(name);
        }
        user.setModifyDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) throws MessageBoardException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new MessageBoardNotFountException(String.format("查無此id : %d 重複", id));
        }
    }
}
