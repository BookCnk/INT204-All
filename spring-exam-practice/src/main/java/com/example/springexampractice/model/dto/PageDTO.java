package com.example.springexampractice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO<T> {
    private List<T> content;
    private Pageable pageable;
    private Integer totalPages;
    private Long totalElements;
    private Integer size;
    private boolean isLast;
    private boolean isFirst;

    @JsonIgnore
    private Integer number;

    public Integer getPage() {
        return number;
    }
}
