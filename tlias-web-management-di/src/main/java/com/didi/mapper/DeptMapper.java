package com.didi.mapper;

import com.didi.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {


    /**
     * 查询所有部门信息
     * @return
     */
    // 方式一: 手动结果映射
    /*@Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })*/

    // 方式二: 起别名
    // @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")

    // 方式三：在配置文件application.yml中开启驼峰命名映射开关，只需要开启一次 (项目开发推荐) map-underscore-to-camel-case: true
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 根据id删除部门信息
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);


    /**
     * 添加部门信息
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime}) ")
    void insert(Dept dept);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 修改部门信息
     * @param dept
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
