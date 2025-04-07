package com.didi.service.impl;

import com.didi.mapper.EmpMapper;
import com.didi.pojo.JobOption;
import com.didi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1. 调用mapper接口
        List<Map<String, Object>> list = empMapper.countEmpJobData(); // map: pos = 教研主管，num = 1

        // 2. 组装接口，并返回
        List<Object> jobList =list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList =list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
