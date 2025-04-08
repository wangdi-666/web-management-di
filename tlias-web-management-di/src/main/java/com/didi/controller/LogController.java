package com.didi.controller;

import com.didi.pojo.PageResult;
import com.didi.pojo.Result;
import com.didi.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 条件分页查询日志
     */
    @GetMapping("/page")
    public Result findLogAll(@RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10")Integer pageSize){
        PageResult pageResult = logService.findLogAll(page , pageSize);
        return Result.success(pageResult);
    }
}
