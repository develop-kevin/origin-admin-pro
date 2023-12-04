package com.origin.admin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.common.web.page.PageResponse;
import com.origin.admin.common.web.page.WhereRequest;

import java.util.List;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/29 11:30
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 插入信息
     * @param entity entity
     * @return Long 返回插入的ID
     * @throws Exception 异常
     */
    Long insert(T entity) throws Exception;

    /**
     * 检测名称存在
     * @param id id
     * @param title 名称
     * @return boolean
     */
    Boolean exist(Long id,String title);

    /**
     * 查询详情
     * @param id ID
     * @return T
     */
    T getDetail(Long id);

    /**
     * 更新
     * @param entity entity
     * @return Long 返回插入的ID
     * @throws Exception 异常
     */
    Long update(T entity) throws Exception;

    /**
     * 删除
     * @param ids ids
     * @return Integer
     */
    Integer delete(List<Long> ids);

    /**
     * 分页查询
     * @param whereRequest 条件
     * @param page  分页
     * @return  Page<T>
     */
    PageResponse<T> queryPage(WhereRequest whereRequest, PageRequest page);


}
