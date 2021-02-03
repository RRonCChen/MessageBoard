package com.ron.messageBoard.controller;

import com.ron.messageBoard.Exception.MessageBoardException;
import com.ron.messageBoard.entity.api.response.ApiResponse;
import com.ron.messageBoard.entity.api.result.PageResultEntity;
import com.ron.messageBoard.entity.data.User;
import com.ron.messageBoard.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(tags = "使用者相關API", description = "提供操作使用者 Restful API")
@RestController
@RequestMapping("/V1/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("取得特定User")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id) throws MessageBoardException {
        return new ResponseEntity<>(new ApiResponse<>(userService.getUser(id)).success(null), HttpStatus.OK);
    }

    @ApiOperation("新增User")
    @PostMapping
    public ResponseEntity<ApiResponse> insertUser(@RequestParam @NotBlank String account, @RequestParam @NotBlank String password, @RequestParam @NotBlank String name) throws MessageBoardException {
        userService.insertUser(account, password, name);
        return new ResponseEntity<>(new ApiResponse<>().created(), HttpStatus.CREATED);
    }

    @ApiOperation("修改User")
    @PutMapping
    public ResponseEntity<ApiResponse> updateUser(@RequestParam @NotNull Long id, @RequestParam String password, @RequestParam String name) {
        userService.updateUser(id, password, name);
        return new ResponseEntity<>(new ApiResponse<>().success("修改成功"), HttpStatus.OK);
    }

    @ApiOperation("刪除User")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable @NotNull Long id) throws MessageBoardException {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse<>().success("刪除成功"), HttpStatus.OK);
    }
}
