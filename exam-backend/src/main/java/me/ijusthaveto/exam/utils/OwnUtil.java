package me.ijusthaveto.exam.utils;

import cn.hutool.core.date.DateUtil;
import me.ijusthaveto.exam.domain.Question;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OwnUtil {

    public static Date getCurrentDate() {
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return DateUtil.parse(sdf.format(current));
    }

    /**
     * 从List从随机挑选指定count数量的Object，并将其封装到新的List
     * @param originalList
     * @param count
     * @return
     */
    public static List<Question> selectRandomQuestions(List<Question> originalList, int count) {
        count = Math.min(count, originalList.size());
        ArrayList<Question> selectedObjects = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; ++i) {
            int randomIdx = random.nextInt(originalList.size());
            selectedObjects.add(originalList.get(randomIdx));
            originalList.remove(randomIdx);
        }

        return selectedObjects;
    }

    /**
     * 将时间字符串转换为日期
     * @param dateString
     * @return
     */
    public static Date dateString2Date(String dateString) {

    }
}
