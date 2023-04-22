package com.thanh.blog.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    Integer currentPage;
    Integer pageSize;
    Integer totalPages;
    Long totalItems;
    List<T> data;
}
