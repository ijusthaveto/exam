package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName exam
 */
@TableName(value = "exam")
@Data
public class Exam implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer examId;
    private String examTitle;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date endTime;
    private Integer bankId;
    private Integer limitTime;
    private Integer singleNum;
    private Double singleScore;
    private Integer multipleNum;
    private Double multipleScore;
    private Integer boolNum;
    private Double boolScore;
}
