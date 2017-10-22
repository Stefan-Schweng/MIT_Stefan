package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="gc6_20Unit")
    EntityManager em;

    public void removeEmployee(int empId) {
        Employee emp = em.find(Employee.class, empId);
        em.remove(emp);

    }
    
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
    
    public List<Phone> findAllPhones() {
        return em.createQuery("SELECT p FROM Phone p", Phone.class)
                 .getResultList();
    }
}

