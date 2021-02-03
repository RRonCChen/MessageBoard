package com.ron.messageBoard.entity.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResultEntity implements Serializable {

    private Long id;

    private String content;

    private String userName;

    private String topicName;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
}
