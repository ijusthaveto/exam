package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName class
 */
@TableName(value ="class")
@Data
public class Class implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer classId;

    /**
     *
     */
    private String classNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
