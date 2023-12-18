package me.ijusthaveto.exam.service.impl;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.bean.copier.ValueProvider;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.ExamDto;
import me.ijusthaveto.exam.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import javax.annotation.Resource;
import java.util.List;
import java.util.TreeMap;

@SpringBootTest
class ExamServiceImplTest {

    @Resource
    private ExamService examService;

    @Test
    void selectExamListById() {
        Integer loginId = 1;
        List<Exam> exams = examService.selectExamListById(loginId);
        for (Exam exam : exams) {
            System.out.println(exam.toString());
        }
    }

    @Test
    void selectQuestionListByExamId() {
        Integer examId = 1;
        List<Question> questions = examService.selectQuestionListByExamId(examId);
        for (Question question : questions) {
            System.out.println(question.toString());
        }
    }

    @Test
    void testHutoolBeanUtil() {
        ExamDto dto = new ExamDto();
        dto.setClassId(RandomUtil.randomInt());
        dto.setClassNo(RandomUtil.randomString(7));
        dto.setExamStartToEnd("");
        dto.setExamPeopleNum(0);
        dto.setExamId(0);
        dto.setExamTitle("");
        dto.setStartTime(new Date());
        dto.setEndTime(new Date());
        dto.setBankId(0);
        dto.setLimitTime(0);
        dto.setSingleNum(0);
        dto.setSingleScore(0.0D);
        dto.setMultipleNum(0);
        dto.setMultipleScore(0.0D);
        dto.setBoolNum(0);
        dto.setBoolScore(0.0D);
    }

    @Test
    void testHutoolJSONUtil() {
        TreeMap<Object, Object> examTime = new TreeMap<Object, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("startTime", "2023-12-01 08:00:00");
                put("endTime", "2023-12-01 10:00:00");
            }
        };

        String examTimeJson = JSONUtil.toJsonStr(examTime);

        ExamDto dto = new ExamDto();
        dto.setClassId(RandomUtil.randomInt(10));
        dto.setClassNo("Z094" + RandomUtil.randomInt(210, 220));
        dto.setExamStartToEnd(examTimeJson);
        dto.setExamPeopleNum(RandomUtil.randomInt(100));
        dto.setExamId(RandomUtil.randomInt());
        dto.setExamTitle("Exam" + RandomUtil.randomInt(10));

        JSONObject examTimeObject = JSONUtil.parseObj(examTimeJson);
        String startTime = examTimeObject.getStr("startTime");
        String endTime = examTimeObject.getStr("endTime");

        dto.setStartTime(DateUtil.parse(startTime));
        dto.setEndTime(DateUtil.parse(endTime));
        dto.setBankId(RandomUtil.randomInt());
        dto.setLimitTime(RandomUtil.randomInt(20, 60));
        dto.setSingleNum(30);
        dto.setSingleScore(1.0);
        dto.setMultipleNum(20);
        dto.setMultipleScore(2.0);
        dto.setBoolNum(30);
        dto.setBoolScore(1.0);

        Exam exam = new Exam();
        System.out.println(exam.toString());
        BeanUtils.copyProperties(dto, exam);
        System.out.println(exam.toString());
    }

    @Test
    void testAddExam() {
        TreeMap<Object, Object> examTime = new TreeMap<Object, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("startTime", "2023-12-01 08:00:00");
                put("endTime", "2023-12-01 10:00:00");
            }
        };

        String examTimeJson = JSONUtil.toJsonStr(examTime);

        ExamDto dto = new ExamDto();
        dto.setClassId(RandomUtil.randomInt(10));
        dto.setClassNo("Z094" + RandomUtil.randomInt(210, 220));
        dto.setExamStartToEnd(examTimeJson);
        dto.setExamPeopleNum(RandomUtil.randomInt(100));
        dto.setExamId(RandomUtil.randomInt());
        dto.setExamTitle("Exam" + RandomUtil.randomInt(10));

        JSONObject examTimeObject = JSONUtil.parseObj(examTimeJson);
        String startTime = examTimeObject.getStr("startTime");
        String endTime = examTimeObject.getStr("endTime");

        dto.setStartTime(DateUtil.parse(startTime));
        dto.setEndTime(DateUtil.parse(endTime));
        dto.setBankId(RandomUtil.randomInt());
        dto.setLimitTime(RandomUtil.randomInt(20, 60));
        dto.setSingleNum(30);
        dto.setSingleScore(1.0);
        dto.setMultipleNum(20);
        dto.setMultipleScore(2.0);
        dto.setBoolNum(30);
        dto.setBoolScore(1.0);

        Exam exam = new Exam();

        examService.addExam(dto);
    }

    @Test
    public void testCurrentDate() {
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(current));
    }
}















