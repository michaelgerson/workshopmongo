package com.nelioalves.workshopmongo.resource.exception;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
