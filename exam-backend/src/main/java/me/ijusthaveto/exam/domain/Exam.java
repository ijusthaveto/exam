package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 * @TableName exam
 */
@TableName(value ="exam")
@Data
public class Exam implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer examId;

    /**
     *
     */
    private String examTitle;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
