package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题库
 * @TableName bank
 */
@TableName(value ="bank")
@Data
public class Bank implements Serializable {
    /**
     * 题库ID
     */
    @TableId(type = IdType.AUTO)
    private Integer bankId;

    /**
     * 学科ID
     */
    private Integer subjectId;

    /**
     * 题库名称
     */
    private String bankTitle;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
