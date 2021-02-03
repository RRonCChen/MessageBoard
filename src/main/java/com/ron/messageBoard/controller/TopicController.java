package com.ron.messageBoard.controller;

import com.ron.messageBoard.entity.api.response.ApiResponse;
import com.ron.messageBoard.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(tags = "主題相關API", description = "提供操作主題 Restful API")
@RestController
@RequestMapping("/V1/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ApiOperation("取得特定Topic")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTopic(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(new ApiResponse<>(topicService.getTopic(id)).success(null), HttpStatus.OK);
    }

    @ApiOperation("取得所有Topics")
    @GetMapping
    public ResponseEntity<ApiResponse> getTopics() {
        return new ResponseEntity<>(new ApiResponse<>(topicService.getTopics()).success(null), HttpStatus.OK);
    }

    @ApiOperation("新增Topic")
    @PostMapping
    public ResponseEntity<ApiResponse> insertTopic(@RequestParam String name) {
        topicService.insertTopic(name);
        return new ResponseEntity<>(new ApiResponse<>().created(), HttpStatus.CREATED);
    }

    @ApiOperation("修改Topic")
    @PutMapping
    public ResponseEntity<ApiResponse> updateTopic(@RequestParam @NotNull Long id, @RequestParam @NotBlank String name) {
        topicService.updateTopicName(id, name);
        return new ResponseEntity<>(new ApiResponse<>().success("修改成功"), HttpStatus.OK);
    }

    @ApiOperation("刪除Topic")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTopic(@PathVariable @NotNull Long id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>(new ApiResponse<>().success("刪除成功"), HttpStatus.OK);
    }


}
