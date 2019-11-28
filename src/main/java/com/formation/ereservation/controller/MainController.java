package com.formation.ereservation.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.formation.ereservation.entity.Employee;
import com.formation.ereservation.repository.EmployeeRepository;
 
@Controller
public class MainController {
 
	Logger logger = LoggerFactory.getLogger(MainController.class);
	 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    private static final String[] NAMES = new String[] { "Tom", "Jerry", "Donald" };
 

    @RequestMapping("/home")
    public String home() {
    	
    	 logger.trace("A TRACE Message");
         logger.debug("A DEBUG Message");
         logger.info("An INFO Message");
         logger.warn("A WARN Message");
         logger.error("An ERROR Message");
  
        return "employee";
    }
 
    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert() {
 
        Long empIdMax = this.employeeRepository.getMaxId();
 
        Employee employee = new Employee();
 
        int random = new Random().nextInt(3);
 
        long id = empIdMax + 1;
        String fullName = NAMES[random] + " " + id;
 
        employee.setId(id);
        employee.setEmpNo("E" + id);
        employee.setFullName(fullName);
        employee.setHireDate(new Date());
        this.employeeRepository.save(employee);
 
        return "Inserted: " + employee;
    }
 
    @ResponseBody
    @RequestMapping("/showAllEmployee")
    public String showAllEmployee() {
 
        Iterable<Employee> employees = this.employeeRepository.findAll();
 
        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }
 
        return html;
    }
 
    @ResponseBody
    @RequestMapping("/showFullNameLikeTom")
    public String showFullNameLikeTom() {
 
        List<Employee> employees = this.employeeRepository.findByFullNameLike("Tom");
 
        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }
 
        return html;
    }
 
    @ResponseBody
    @RequestMapping("/deleteAllEmployee")
    public String deleteAllEmployee() {
 
        this.employeeRepository.deleteAll();
        return "Deleted!";
    }
 
}
