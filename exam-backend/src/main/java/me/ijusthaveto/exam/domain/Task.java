package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author ijusthaveto
 * @create 2023-12-18
 * @TableName task
 */
@TableName(value ="task")
@Data
public class Task implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer taskId;
    private Integer userId;
    private Integer examId;
    private Double score;
    private Integer status;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
