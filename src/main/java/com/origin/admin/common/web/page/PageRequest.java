package com.origin.admin.common.web.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 20:46
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    /**
     * 当前页码
     * */
    @JsonProperty(value = "page_num")
    @Schema(description = "当前页码",defaultValue = "1")
//    @JsonDeserialize(as = int.class)  // 将JSON对象反序列化为int类型
    private int pageNum;  // 默认值为1

    /**
     * 每页数量
     * */
    @JsonProperty(value = "page_size")
    @Schema(description = "每页数量",defaultValue = "10")
//    @JsonDeserialize(as = int.class)  // 将JSON对象反序列化为int类型
    private int pageSize;  // 默认值为10
}
