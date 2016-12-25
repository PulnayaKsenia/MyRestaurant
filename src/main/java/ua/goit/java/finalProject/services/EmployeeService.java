package ua.goit.java.finalProject.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.finalProject.dao.EmployeeDao;
import ua.goit.java.finalProject.entity.Employee;
import ua.goit.java.finalProject.entity.Position;
import ua.goit.java.finalProject.entity.Waiter;

import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao;

    public List<Employee> getEmployees(){
        return employeeDao.findAll();
    }

    @Transactional
    public List<Employee> getEmployeesByName(String employeeName) {
        return employeeDao.findByName1(employeeName);
    }

    @Transactional
    public void updateEmployeeInfo(int id, Employee employeeWithNewInformation) {
        employeeDao.updateEmployee(id, employeeWithNewInformation);
    }

    @Transactional
    public Employee getEmployeeByID(int id) {
        return employeeDao.findById(id);
    }

    public List<Waiter> getAllWaiters() {
        return employeeDao.findAllWaiters();
    }

    @Transactional
    public void deleteEmployee(Employee employee) {
        employeeDao.remove(employee);
    }

    @Transactional
    public void addNewEmployee(String firstName, String position) {

        Employee employee = new Employee();

        employee.setFirstName(firstName);
        setEmployeePosition(position, employee);
        employee.setPhotoURL("/images/employees/default.jpg");

        employeeDao.save(employee);
    }

    public Employee setInformation(String name, String position) {

        Employee employee = new Employee();
        employee.setFirstName(name);

        setEmployeePosition(position, employee);

        return employee;
    }

    public void setEmployeePosition(String position, Employee employee) {
        if (position.equals("Waiter")) {
            employee.setPosition(Position.WAITER);
        } else if (position.equals("Cook")) {
            employee.setPosition(Position.COOK);
        } else if (position.equals("Administrator")) {
            employee.setPosition(Position.ADMINISTRATOR);
        }
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
