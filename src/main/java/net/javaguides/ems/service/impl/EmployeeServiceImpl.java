package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.RessourceNotfoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToemployee(employeeDto);
        Employee saveEmployee= employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new RessourceNotfoundException("Employee is not exist with given id : "+employeeId));


        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees =employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new RessourceNotfoundException("employee is not exist with given id : "+employeeId));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setMail(updateEmployee.getEmail());
        Employee updatedEmployeeObj= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new RessourceNotfoundException("employee is not exist with given id : "+employeeId));
        employeeRepository.deleteById(employeeId);
    }

}
