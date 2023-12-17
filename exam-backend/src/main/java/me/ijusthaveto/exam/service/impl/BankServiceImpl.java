package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Bank;
import me.ijusthaveto.exam.service.BankService;
import me.ijusthaveto.exam.mapper.BankMapper;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【bank】的数据库操作Service实现
* @createDate 2023-12-17 18:20:22
*/
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank>
    implements BankService{

}




