package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName task
 */
@TableName(value ="task")
@Data
public class Task implements Serializable {
    /**
     * 考试ID
     */
    @TableId(type = IdType.AUTO)
    private Integer taskId;

    /**
     * 题库ID
     */
    private Integer bankId;

    /**
     * 考试名称
     */
    private String taskName;

    /**
     * 考试开始时间
     */
    private Date taskTime;

    /**
     * 考试限制时间
     */
    private Integer limitTime;

    /**
     * 单选题数量
     */
    private Integer singleNum;

    /**
     * 多选题数量
     */
    private Integer multipleNum;

    /**
     * 判断题数量
     */
    private Integer boolNum;

    /**
     * 单选题分数
     */
    private Double singleScore;

    /**
     * 多选题分数
     */
    private Double multipleScore;

    /**
     * 判断题分数
     */
    private Double boolScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
