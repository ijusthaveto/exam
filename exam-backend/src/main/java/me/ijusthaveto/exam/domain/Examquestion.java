package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName examquestion
 */
@TableName(value ="examquestion")
@Data
public class Examquestion implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer examQuestionId;

    /**
     *
     */
    private Integer examId;

    /**
     *
     */
    private Integer questionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
