package org.example.app.service;

import org.example.app.entity.employee.Employee;
import org.example.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public Optional<Employee> save(Employee employee) {
        return Optional.of(repository.save(employee));
    }

    public Optional<List<Employee>> getAll() {
        return Optional.of(repository.findAll());
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee update(Long id, Employee user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String phone = user.getPhone();
        String position = user.getPosition();
        Optional<Employee> optional = repository.findById(id);
        if (optional.isPresent()) {
            Employee userUpdate = optional.get();
            if (firstName != null)
                userUpdate.setFirstName(firstName);
            if (lastName != null)
                userUpdate.setLastName(lastName);
            if (position != null)
                userUpdate.setPosition(position);
            if (phone != null)
                userUpdate.setPhone(phone);
            repository.save(userUpdate);
        }
        return repository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }

    public List<Employee> getByFirstName(String firstName) {
        return repository.findByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    public List<Employee> getByLastName(String lastName) {
        return repository.findByLastName(lastName)
                .orElse(Collections.emptyList());
    }

    public List<Employee> getByPosition(String pos) {
        return repository.findByPosition(pos)
                .orElse(Collections.emptyList());
    }

}
