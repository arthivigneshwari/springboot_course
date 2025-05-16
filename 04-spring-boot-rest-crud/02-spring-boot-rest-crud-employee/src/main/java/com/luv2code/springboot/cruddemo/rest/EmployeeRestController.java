package com.luv2code.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //private EmployeeDAO employeeDAO;
    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    //inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService,ObjectMapper theObjectMapper){
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }

    //export /employees and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    /// employees/{employeeId} find an employee by id and fetch it..add mapping for that.
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " +employeeId);
        }
        return theEmployee;
    }

    //create a new Employee using @PostMapping
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        //if Employee entity has int then we can use theEmployee.seId(0)
        //if Employee entity has Integer type then we can use theEmployee.setId(null)
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee; //in case of insert it has updated id from the database
    }

    //add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //add mapping for PATCH /employees/{employeeID} - patch employee... to perform partial updates
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String,Object> patchPayload){

        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new NullPointerException("Employee id not found - " +employeeId);
        }

        if(patchPayload.containsKey("id")){
            throw new NullPointerException("Employee id not allowed in request body - "+employeeId);
        }

        Employee patchedEmployee = apply(patchPayload,tempEmployee);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload,Employee tempEmployee) {
        //Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        //Convert patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }


    //add mapping for DELETE /employees - delete existing employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee not found with id  - " +employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee with id - "+employeeId;
    }

}
