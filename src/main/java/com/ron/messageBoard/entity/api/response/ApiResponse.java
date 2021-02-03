package com.ron.messageBoard.entity.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dom4j.util.StringUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {


    private String status;
    private String message;
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse success(String message) {
        this.status = "Success";
        this.message = message;
        return this;
    }

    public ApiResponse created() {
        this.status = "Success";
        this.message = "新增成功";
        return this;
    }

    public ApiResponse badRequest(String message) {
        this.status = "Error";
        this.message = message;
        return this;
    }

    public ApiResponse notFound(String message) {
        this.status = "Error";
        this.message = message;
        return this;
    }

    public ApiResponse serverError() {
        this.status = "Error";
        this.message = "serverError";
        return this;
    }


}
