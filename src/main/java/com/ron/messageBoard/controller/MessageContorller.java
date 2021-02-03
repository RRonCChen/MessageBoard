package com.ron.messageBoard.controller;

import com.ron.messageBoard.entity.api.response.ApiResponse;
import com.ron.messageBoard.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(tags = "留言相關API", description = "提供操作留言 Restful API")
@RestController
@RequestMapping("/V1/messages")
public class MessageContorller {

    @Autowired
    private MessageService messageService;

    @ApiOperation("取得特定Message")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMessage(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(messageService.getMessage(id)).success(null), HttpStatus.OK);
    }

    @ApiOperation("取得特定主題分頁Messages")
    @GetMapping
    public ResponseEntity<ApiResponse> getMessages(@RequestParam @NotNull Long topicId, @RequestParam @NotNull Integer page, @RequestParam @NotNull Integer size) {
        return new ResponseEntity<>(new ApiResponse<>(messageService.getTopicMessages(topicId, page, size)).success(null), HttpStatus.OK);
    }

    @ApiOperation("新增Message")
    @PostMapping
    public ResponseEntity<ApiResponse> insertMessage(@RequestParam @NotNull Long userId, @RequestParam @NotNull Long topicId, @RequestParam @NotBlank String content) {
        messageService.insertMessage(userId, topicId, content);
        return new ResponseEntity<>(new ApiResponse<>().created(), HttpStatus.CREATED);
    }

    @ApiOperation("修改Message")
    @PutMapping
    public ResponseEntity<ApiResponse> updateMessage(@RequestParam @NotNull Long id, @RequestParam @NotBlank String content) {
        messageService.updateMessage(id, content);
        return new ResponseEntity<>(new ApiResponse<>().success("修改成功"), HttpStatus.OK);
    }

    @ApiOperation("刪除特定Message")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable @NotNull Long id) {
        messageService.deleteMessage(id);
        return new ResponseEntity<>(new ApiResponse<>().success("刪除成功"), HttpStatus.OK);
    }

}
