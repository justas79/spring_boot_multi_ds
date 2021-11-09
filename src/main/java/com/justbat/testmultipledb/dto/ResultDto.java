package com.justbat.testmultipledb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDto {
    private String symbol;
    private String advisor;

}
