package com.origin.admin.modules.system.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.origin.admin.common.tools.core.HelperUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final String CREATEBY = "createBy";
    private static final String UPDATEBY = "updateBy";
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
        Long userId = HelperUtils.getUserId();
        log.info("insertFill=userId={}",userId);
        if(userId != null){
            //插入用户ID
            this.strictInsertFill(metaObject, CREATEBY, Long.class, userId);
        }
    }

    /**
     * 更新默认填充器
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject){
        //最后更新时间
        this.strictUpdateFill(metaObject,UPDATETIME, LocalDateTime.class,LocalDateTime.now());
        Long userId = HelperUtils.getUserId();
        log.info("updateFill=userId={}",userId);
        if(userId != null){
            //插入用户ID
            this.strictInsertFill(metaObject, UPDATEBY, Long.class, userId);
        }
    }

}
