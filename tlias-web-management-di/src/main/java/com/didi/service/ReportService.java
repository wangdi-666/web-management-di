package com.didi.service;

import com.didi.pojo.ClazzCountOption;
import com.didi.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * 统计员工职位人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别人数
     * @return
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 统计学历人数
     */
    List<Map> getStudentDegreeData();

    /**
     * 统计班级人数
     */
    ClazzCountOption getStudentCountData();
}
