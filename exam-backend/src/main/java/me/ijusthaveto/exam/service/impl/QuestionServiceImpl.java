package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.service.QuestionService;
import me.ijusthaveto.exam.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【question】的数据库操作Service实现
* @createDate 2023-12-10 13:14:24
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




