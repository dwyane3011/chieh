package com.chieh.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private String requestId;

    private String code = "200";

    private String msg = "success";

    private T data;

    private Integer pageIndex;

    private Integer pageSize;

    private Integer totalCount;

    public static Response data(Object data) {
        return Response.builder()
                .code("200")
                .msg("ok")
                .data(data)
                .build();
    }

    public static Response failure(String code, String msg) {
        return Response.builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static Response success() {
        return success("200", "操作成功");
    }

    public static Response success(String code, String msg) {
        return Response.builder()
                .code(code)
                .msg(msg)
                .build();
    }
}
