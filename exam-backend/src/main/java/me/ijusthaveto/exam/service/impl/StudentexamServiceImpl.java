package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Studentexam;
import me.ijusthaveto.exam.service.StudentexamService;
import me.ijusthaveto.exam.mapper.StudentexamMapper;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【studentexam】的数据库操作Service实现
* @createDate 2023-12-11 12:42:55
*/
@Service
public class StudentexamServiceImpl extends ServiceImpl<StudentexamMapper, Studentexam>
    implements StudentexamService{

}




