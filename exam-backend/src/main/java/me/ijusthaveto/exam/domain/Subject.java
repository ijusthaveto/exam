package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName subject
 */
@TableName(value ="subject")
@Data
public class Subject implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer subjectId;

    /**
     *
     */
    private String subjectName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
