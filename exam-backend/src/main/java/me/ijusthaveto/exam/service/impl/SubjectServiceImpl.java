package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Subject;
import me.ijusthaveto.exam.mapper.SubjectMapper;
import me.ijusthaveto.exam.service.SubjectService;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【subject】的数据库操作Service实现
* @createDate 2023-12-17 18:09:33
*/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
    implements SubjectService{

}




