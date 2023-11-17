package com.origin.admin.modules.system.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/15 18:44
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final String CREATETIME = "createTime";
    private static final String UPDATETIME = "updateTime";
    /**
     * 新增默认填充器
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject){
        //新增、最后更新时间
        this.strictInsertFill(metaObject,CREATETIME, LocalDateTime.class,LocalDateTime.now());
        this.strictUpdateFill(metaObject,UPDATETIME, LocalDateTime.class,LocalDateTime.now());
    }

    /**
     * 更新默认填充器
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject){
        //最后更新时间
        this.strictUpdateFill(metaObject,UPDATETIME, LocalDateTime.class,LocalDateTime.now());
    }


}
