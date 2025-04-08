package com.didi.service;

import com.didi.pojo.PageResult;

public interface LogService {
    /**
     * 分页查询日志信息
     * @param page
     * @param pageSize
     * @return
     */
    PageResult findLogAll(Integer page, Integer pageSize);
}
