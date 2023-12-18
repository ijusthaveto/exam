package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Task;
import me.ijusthaveto.exam.service.TaskService;
import me.ijusthaveto.exam.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【task】的数据库操作Service实现
* @createDate 2023-12-18 20:21:13
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

}




