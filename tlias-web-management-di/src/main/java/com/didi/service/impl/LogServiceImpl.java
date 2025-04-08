package com.didi.service.impl;

import com.didi.mapper.OperateLogMapper;
import com.didi.pojo.Clazz;
import com.didi.pojo.OperateLog;
import com.didi.pojo.PageResult;
import com.didi.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult findLogAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<OperateLog> dataList = operateLogMapper.findLogAll();
        Page<OperateLog> p = (Page<OperateLog>) dataList;

        return new PageResult(p.getTotal(), p.getResult());
    }
}
