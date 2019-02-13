package com.lambda.employee;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
//import org.springframework.stereotype.Component;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//import javax.annotation.Resources;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeResourceAssembler employeeResourceAssembler;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeResourceAssembler employeeResourceAssembler) {
        this.employeeRepository = employeeRepository;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }
    // RequestMapping(method = RequestMethod.GET)

//    @GetMapping("/employees") // RequestMapping(method=RequestMethod.GET)
    @GetMapping("/employees")
    public Resources<Resource<Employee>> getAllEmployee(){
//
        List<Resource<Employee>> employees = employeeRepository.findAll().stream()
                .map(employeeResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel());
    }

    @GetMapping("/employee/{id}")
    public Resource<Employee> findOne(@PathVariable Long id){
        Employee found = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id)
        );
        return employeeResourceAssembler.toResource(found);
    }

}
