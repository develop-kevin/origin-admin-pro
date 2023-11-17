package com.origin.admin.modules.system.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 21:10
 */
@Data
public class WhereRequest {
    @JsonProperty(value = "keyword")
    private String keyword;
    @JsonProperty(value = "begin_time")
    private String beginTime;
    @JsonProperty(value = "end_time")
    private String endTime;
}
