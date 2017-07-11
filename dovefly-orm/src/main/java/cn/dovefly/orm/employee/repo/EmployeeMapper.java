package cn.dovefly.orm.employee.repo;

import cn.dovefly.orm.employee.entity.Employee;

public interface EmployeeMapper {
    /**
     *
     * @mbg.generated 2017-07-11
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2017-07-11
     */
    int insert(Employee record);

    /**
     *
     * @mbg.generated 2017-07-11
     */
    int insertSelective(Employee record);

    /**
     *
     * @mbg.generated 2017-07-11
     */
    Employee selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2017-07-11
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     *
     * @mbg.generated 2017-07-11
     */
    int updateByPrimaryKey(Employee record);
}