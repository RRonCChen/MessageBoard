package com.ron.messageBoard.entity.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResultEntity<T> implements Serializable {
    private Integer totalPages;
    private Long totalElements;
    private Integer eachPageSize;
    private Integer thisPageSize;
    private List<T> elementList;

}
