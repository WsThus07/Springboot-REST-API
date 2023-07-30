package net.javaguides.ems.controller;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    //build Add employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody  EmployeeDto employeeDto){
        EmployeeDto savedEmployee= employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto=employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //build Get All employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees= employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto=  employeeService.updateEmployee(employeeId,updateEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    //build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee (@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted succefully!.");
    }

}
