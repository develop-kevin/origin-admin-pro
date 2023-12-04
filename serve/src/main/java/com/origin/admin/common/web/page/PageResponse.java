package com.origin.admin.common.web.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 22:01
 */
@Data
@NoArgsConstructor
public class PageResponse<T> {
    /**
     * 总页数
     * */
    private long total;

    /**
     * 页码
     */
    private long page;

    /**
     * 分页后的数据
     * */
    private List<T> record;

    /**
     * 构建实体
     * */
    public PageResponse(long page,long total,List<T> record){
        this.page = page;
        this.total = total;
        this.record = record;
    }

}
