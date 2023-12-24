package me.ijusthaveto.exam.domain;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ijusthaveto
 * @create 2023-12-18
 * @TableName task
 */
@TableName(value ="task")
@Data
public class Task implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer taskId;
    private Integer userId;
    private Integer examId;
    private Double score;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
