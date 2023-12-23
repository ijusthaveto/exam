package me.ijusthaveto.exam.utils;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.Subject;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.SubjectService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static me.ijusthaveto.exam.common.ErrorCode.PARSE_STRING_DATE_ERROR;

public class OwnUtil {


    public static Date getCurrentDate() {
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return DateUtil.parse(sdf.format(current));
    }

    /**
     * 从List从随机挑选指定count数量的Object，并将其封装到新的List
     *
     * @param originalList
     * @param count
     * @return
     */
    public static List<Question> selectRandomQuestions(List<Question> originalList, int count) {
        count = Math.min(count, originalList.size());

        ArrayList<Question> shuffledList = new ArrayList<>(originalList);

        Collections.shuffle(shuffledList);

        return shuffledList.subList(0, count);
    }

    /**
     * 将时间字符串转换为日期
     *
     * @param dateString
     * @return
     */
    public static Date dateString2Date(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new BusinessException(PARSE_STRING_DATE_ERROR);
        }
    }

}
