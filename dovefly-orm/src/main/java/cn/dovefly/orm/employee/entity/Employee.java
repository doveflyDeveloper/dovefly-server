package cn.dovefly.orm.employee.entity;

import cn.dovefly.orm.base.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * 员工表（代码生成测试用）
 * @author fengchunming
 * @date 2017-07-11
 */
public class Employee extends BaseEntity {
    /**
     * ID
     */
    private Integer id;

    /**
     * 员工编码
     */
    private String empCode;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 性别：M-男，F-女
     */
    private String empSex;

    /**
     * 年龄
     */
    private Integer empAge;

    /**
     * 工资
     */
    private BigDecimal empSalary;

    /**
     * 员工类型：1-正式工，2-临时工，3-实习生
     */
    private Boolean empType;

    /**
     * 员工等级
     */
    private BigDecimal empLevel;

    /**
     * 员工概述
     */
    private String empSummary;

    /**
     * 员工详情
     */
    private String empDesc;

    /**
     *
     * @mbg.generated 2017-07-11
     */
    public Employee(Integer id, String empCode, String empName, String empSex, Integer empAge, BigDecimal empSalary, Boolean empType, BigDecimal empLevel, String empSummary, String empDesc) {
        this.id = id;
        this.empCode = empCode;
        this.empName = empName;
        this.empSex = empSex;
        this.empAge = empAge;
        this.empSalary = empSalary;
        this.empType = empType;
        this.empLevel = empLevel;
        this.empSummary = empSummary;
        this.empDesc = empDesc;
    }

    /**
     *
     * @mbg.generated 2017-07-11
     */
    public Employee() {
        super();
    }

    /**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 员工编码
     * @return emp_code 员工编码
     */
    public String getEmpCode() {
        return empCode;
    }

    /**
     * 员工编码
     * @param empCode 员工编码
     */
    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    /**
     * 员工姓名
     * @return emp_name 员工姓名
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 员工姓名
     * @param empName 员工姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * 性别：M-男，F-女
     * @return emp_sex 性别：M-男，F-女
     */
    public String getEmpSex() {
        return empSex;
    }

    /**
     * 性别：M-男，F-女
     * @param empSex 性别：M-男，F-女
     */
    public void setEmpSex(String empSex) {
        this.empSex = empSex == null ? null : empSex.trim();
    }

    /**
     * 年龄
     * @return emp_age 年龄
     */
    public Integer getEmpAge() {
        return empAge;
    }

    /**
     * 年龄
     * @param empAge 年龄
     */
    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    /**
     * 工资
     * @return emp_salary 工资
     */
    public BigDecimal getEmpSalary() {
        return empSalary;
    }

    /**
     * 工资
     * @param empSalary 工资
     */
    public void setEmpSalary(BigDecimal empSalary) {
        this.empSalary = empSalary;
    }

    /**
     * 员工类型：1-正式工，2-临时工，3-实习生
     * @return emp_type 员工类型：1-正式工，2-临时工，3-实习生
     */
    public Boolean getEmpType() {
        return empType;
    }

    /**
     * 员工类型：1-正式工，2-临时工，3-实习生
     * @param empType 员工类型：1-正式工，2-临时工，3-实习生
     */
    public void setEmpType(Boolean empType) {
        this.empType = empType;
    }

    /**
     * 员工等级
     * @return emp_level 员工等级
     */
    public BigDecimal getEmpLevel() {
        return empLevel;
    }

    /**
     * 员工等级
     * @param empLevel 员工等级
     */
    public void setEmpLevel(BigDecimal empLevel) {
        this.empLevel = empLevel;
    }

    /**
     * 员工概述
     * @return emp_summary 员工概述
     */
    public String getEmpSummary() {
        return empSummary;
    }

    /**
     * 员工概述
     * @param empSummary 员工概述
     */
    public void setEmpSummary(String empSummary) {
        this.empSummary = empSummary == null ? null : empSummary.trim();
    }

    /**
     * 员工详情
     * @return emp_desc 员工详情
     */
    public String getEmpDesc() {
        return empDesc;
    }

    /**
     * 员工详情
     * @param empDesc 员工详情
     */
    public void setEmpDesc(String empDesc) {
        this.empDesc = empDesc == null ? null : empDesc.trim();
    }
}