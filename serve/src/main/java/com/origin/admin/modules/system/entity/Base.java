package com.origin.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/15 20:26
 */
@Data
public class Base implements Serializable {

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "自增ID")
    @JsonProperty(value = "id")
    public Long id;

    /**
     * 创建人
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    @Schema(description = "创建人")
    @JsonProperty(value = "create_by")
    public String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty(value = "create_time")
    public LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新人")
    @JsonProperty(value = "update_by")
    public String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty(value = "update_time")
    public LocalDateTime updateTime;

    /**
     * 是否删除 （0：未删除 1：删除）
     */
    @TableField(value = "is_delete", fill = FieldFill.INSERT)
    @Schema(description = "是否删除 0：未删除 1：删除")
    @JsonProperty(value = "is_delete")
    public Boolean isDelete;


    /**
     * 删除人
     */
    @TableField(value = "delete_by", fill = FieldFill.INSERT)
    @Schema(description = "删除人")
    @JsonProperty(value = "delete_by")
    @JsonIgnore
    public String deleteBy;

    /**
     * 删除时间
     */
    @TableField(value = "delete_time", fill = FieldFill.INSERT)
    @Schema(description = "删除时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty(value = "delete_time")
    @JsonIgnore
    public LocalDateTime deleteTime;

}
