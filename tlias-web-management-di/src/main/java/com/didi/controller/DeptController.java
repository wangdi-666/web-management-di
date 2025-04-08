package com.didi.controller;


import com.didi.anno.Log;
import com.didi.pojo.Dept;
import com.didi.pojo.Result;
import com.didi.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //可以直接使用注解 @Slf4j
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class); //固定代码

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //method: 指定请求方式
    @GetMapping
    public Result list(){
        // System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门 方式一：基于HttpServletRequest 获取请求参数(不推荐)
     */
    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据ID删除部门数据：" + id);
        return Result.success();
    }*/

    /**
     * 删除部门 方式二：基于@RequestParam("id")注解
     * 注意事项：一旦声明了@RequestParam("id")注解，该参数在请求的时候必须传递，如果不传递将会报错, (默认 required 为 true)
     *          如果有些情况下，请求参数可传递或者不传递，此时需要设置 required 为 false
     */
    /*@DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId){
        System.out.println("根据ID删除部门数据：" + deptId);
        return Result.success();
    }*/

    /**
     * 删除部门 方式三：如果请求参数名与形参变量名相同，直接定义方法形参即可接受。(省略@RequestParam注解)(项目开发推荐方式)
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id){
        //System.out.println("根据ID删除部门数据：" + id);
        log.info("根据ID删除部门数据：{}", id);// 可以使用占位符，避免使用 + 加号拼接字符串
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门：" + dept);
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     */
    /*@GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        System.out.println("根据ID查询部门：" + deptId);
        return Result.success();
    }*/

    /**
     * 根据ID查询部门(页面回显)
     */
    @GetMapping("/{id}") // 如果路径参数的名称和形参变量名相同，可以使用@PathVariable注解省略形参变量名
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门：" + id);
        log.info("根据ID查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门：" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
