package com.didi.mapper;

import com.didi.pojo.Emp;
import com.didi.pojo.EmpQueryParam;
import com.didi.pojo.JobOption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    // -----------------原始分页查询实现-----------------
    /**
     * 查询总记录数
     * @return
     */
    /*@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();*/


    /**
     * 分页查询
     * @return
     */
    /*@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "order by e.update_time desc limit #{start}, #{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);*/


    // -----------------PageHelper分页查询实现-----------------

    /**
     * 分页查询
     * @return
     */
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件查询员工信息的方法
     * @param empQueryParam
     * @return
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键 -- 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据ID批量删除员工的基本信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工信息以及员工工作经历信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据ID更新员工基本信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * @return
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData(); //红色线是安装插件的误报，可以卸载插件也可以不用管不会影响正常运行，正常如果返回的是Map才需要@MapKey注解，也可以直接声明注解

    /**
     * 统计员工性别人数
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}
