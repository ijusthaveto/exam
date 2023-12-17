package me.ijusthaveto.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String passwordHash;

    /**
     *
     */
    private Integer roleId;

    private Integer classId;

    private String userNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
