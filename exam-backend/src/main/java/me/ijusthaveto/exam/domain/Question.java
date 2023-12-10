package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer questionId;

    /**
     *
     */
    private Integer questionBankId;

    /**
     *
     */
    private String questionType;

    /**
     *
     */
    private String difficultyLevel;

    /**
     *
     */
    private String questionContent;

    /**
     *
     */
    private Object options;

    /**
     *
     */
    private String correctAnswer;

    /**
     *
     */
    private Integer score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
