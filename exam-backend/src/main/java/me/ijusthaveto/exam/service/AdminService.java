package me.ijusthaveto.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.ijusthaveto.exam.domain.Admin;

/**
* @author 修雯天
* @description 针对表【admin】的数据库操作Service
* @createDate 2023-12-12 15:44:33
*/
public interface AdminService extends IService<Admin> {

    void login(String secretCode);
}
