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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date startTime;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date endTime;

    /**
     * 题库ID
     */
    private Integer bankId;

    /**
     * 考试限制时间
     */
    private Integer limitTime;

    /**
     * 单选题数量
     */
    private Integer singleNum;

    /**
     * 单选题分数
     */
    private Double singleScore;

    /**
     * 多选题数量
     */
    private Integer multipleNum;

    /**
     * 多选题分数
     */
    private Double multipleScore;

    /**
     * 判断题数量
     */
    private Integer boolNum;

    /**
     * 判断题分数
     */
    private Double boolScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
