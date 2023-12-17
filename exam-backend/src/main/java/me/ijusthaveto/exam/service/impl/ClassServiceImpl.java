package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Class;
import me.ijusthaveto.exam.service.ClassService;
import me.ijusthaveto.exam.mapper.ClassMapper;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【class】的数据库操作Service实现
* @createDate 2023-12-17 20:31:18
*/
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class>
    implements ClassService{

}




