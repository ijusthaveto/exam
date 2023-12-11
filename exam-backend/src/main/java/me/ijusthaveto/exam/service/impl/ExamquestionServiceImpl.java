package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Examquestion;
import me.ijusthaveto.exam.service.ExamquestionService;
import me.ijusthaveto.exam.mapper.ExamquestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【examquestion】的数据库操作Service实现
* @createDate 2023-12-11 12:42:47
*/
@Service
public class ExamquestionServiceImpl extends ServiceImpl<ExamquestionMapper, Examquestion>
    implements ExamquestionService{

}




