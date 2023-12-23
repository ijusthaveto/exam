package me.ijusthaveto.exam.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.*;
import me.ijusthaveto.exam.domain.Class;
import me.ijusthaveto.exam.domain.dto.History;
import me.ijusthaveto.exam.domain.dto.StuDto;
import me.ijusthaveto.exam.domain.dto.UserLoginDto;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.mapper.UserMapper;
import me.ijusthaveto.exam.service.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static me.ijusthaveto.exam.common.ErrorCode.*;
import static me.ijusthaveto.exam.constant.RoleConstant.DEFAULT_ROLE;
import static me.ijusthaveto.exam.constant.UserConstant.*;

/**
* @author 修雯天
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-12-09 23:20:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private ClassService classService;

    @Resource
    private TaskService taskService;

    @Resource
    private StudentexamService stuExamService;

    @Resource
    private BankService bankService;

    @Resource
    private ExamService examService;

    @Resource
    private SubjectService subjectService;

    @Override
    public void register(UserRegisterDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String conform = dto.getConfirmPassword();

        if (!conform.equals(password)) {
            throw new BusinessException(CONFIRM_ERROR);
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        Long count = baseMapper.selectCount(wrapper);
        if (count > 1) {
            throw new BusinessException(USERNAME_ERROR);
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(password);
        user.setRoleId(DEFAULT_ROLE);

        int insert = baseMapper.insert(user);
        if (insert != 1) {
            throw new BusinessException(REGISTER_ERROR);
        }
    }

    @Override
    public void login(UserLoginDto dto) {
        String password = dto.getPassword();
        String username = dto.getUsername();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        Long count = baseMapper.selectCount(wrapper);
        if (count != 1) {
            throw new BusinessException(USER_NOT_EXIST_ERROR);
        }
        User user = baseMapper.selectOne(wrapper);
        if (!user.getPasswordHash().equals(password)) {
            throw new BusinessException(LOGIN_ERROR);
        }

        StpUtil.login(user.getUserId());
        StpUtil.getSession().set("loginId", user.getUserId());
    }

    @Override
    public Page<StuDto> selectPage(Integer page, Integer size, String userNo) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(userNo != null, User::getUserNo, userNo)
                .orderByAsc(User::getUserId)
                .eq(User::getRoleId, DEFAULT_ROLE)
                .orderByAsc(User::getUserNo);
        Page<User> userPage = new Page<>(page, size);
        this.page(userPage, wrapper);

        List<User> records = userPage.getRecords();
        Page<StuDto> stuDtoPage = new Page<>();
        BeanUtils.copyProperties(userPage, stuDtoPage);
        List<StuDto> collect = records.parallelStream().map(user -> {
            StuDto stuDto = new StuDto();
            LambdaQueryWrapper<Class> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Class::getClassId, user.getClassId());
            Class one = classService.getOne(queryWrapper);
            BeanUtils.copyProperties(user, stuDto);
            if (one != null) {
                stuDto.setClassNo(one.getClassNo());
            }
            return stuDto;
        }).collect(Collectors.toList());
        stuDtoPage.setRecords(collect);
        return stuDtoPage;
    }

    @Override
    public void processCsvFile(MultipartFile file, String classNo) {
        Class newClass = new Class();
        newClass.setClassNo(classNo);
        classService.save(newClass);

        LambdaQueryWrapper<Class> classWrapper = new LambdaQueryWrapper<>();
        classWrapper.eq(Class::getClassNo, classNo);
        Class one = classService.getOne(classWrapper);
        if (Objects.isNull(one)) {
            throw new BusinessException(CREATE_CLASS_ERROR);
        }
        Integer classId = one.getClassId();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVParser records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : records) {
                User user = new User();
                user.setUsername(record.get(USERNAME));
                user.setUserNo(record.get(USER_NO));
                user.setPasswordHash(DEFAULT_PWD);
                user.setRoleId(DEFAULT_ROLE);
                user.setClassId(classId);

                this.save(user);
            }
        } catch (Exception e) {
            throw new BusinessException(USER_IMPORT_ERROR);
        }
    }

    @Override
    public StuDto getUserById(Integer userId) {
        User user = baseMapper.selectById(userId);
        Class clazz = classService.getById(user.getClassId());
        StuDto res = new StuDto();
        BeanUtils.copyProperties(user, res);
        res.setClassNo(clazz.getClassNo());
        return res;
    }

    @Override
    public void modifyUserInfo(StuDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        String classNo = dto.getClassNo();

        LambdaQueryWrapper<Class> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Class::getClassNo, classNo);
        long count = classService.count(wrapper);
        if (count == 1) {
            Class one = classService.getOne(wrapper);
            user.setClassId(one.getClassId());
        } else if (count == 0) {
            Class aClass = new Class();
            aClass.setClassNo(classNo);
            classService.save(aClass);
            Class one = classService.getOne(wrapper);
            user.setClassId(one.getClassId());
        }

        int result = baseMapper.updateById(user);
        if (result != 1) {
            throw new BusinessException(MODIFY_USER_INFO_ERROR);
        }
    }

    @Override
    public List<History> selectHistoryScore() {
        Integer loginId = (Integer) StpUtil.getSession().get("loginId");

        LambdaQueryWrapper<Studentexam> examUserWrapper = new LambdaQueryWrapper<>();
        examUserWrapper.eq(Studentexam::getUserId, loginId);
        List<Studentexam> stuExamList = stuExamService.list(examUserWrapper);

        List<History> historyList = new ArrayList<>();

        for (Studentexam item : stuExamList) {
            Integer examId = item.getExamId();

            Exam exam = examService.getById(examId);
            Integer bankId = exam.getBankId();
            Bank bank = bankService.getById(bankId);
            Integer subjectId = bank.getSubjectId();
            Subject subject = subjectService.getById(subjectId);


            LambdaQueryWrapper<Task> taskWrapper = new LambdaQueryWrapper<>();
            taskWrapper.eq(Task::getExamId, examId).eq(Task::getUserId, loginId);
            Task task = taskService.getOne(taskWrapper);
            if (Objects.isNull(task)) {
                continue;
            }
            History history = new History();
            history.setScore(task.getScore());
            history.setEndTime(task.getUpdateTime());
            history.setStartTime(task.getCreateTime());
            history.setExamTitle(exam.getExamTitle());
            history.setSubjectName(subject.getSubjectName());

            historyList.add(history);
        }

        return historyList;
    }
}




