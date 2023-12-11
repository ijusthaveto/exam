package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName studentexam
 */
@TableName(value ="studentexam")
@Data
public class Studentexam implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer studentExamId;

    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private Integer examId;

    /**
     *
     */
    private Object status;

    /**
     *
     */
    private Integer score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
