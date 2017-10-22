package examples.stateless;

import java.util.List;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.*;
import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="j_chapter6_21mergingDetachedEntitiesPU")
    EntityManager em;

    public void updateEmployee(Employee emp) {
        if (em.find(Employee.class, emp.getId()) == null) {
            throw new IllegalArgumentException("Unknown employee id: " + emp.getId());
        }
        Employee managedEmp = em.merge(emp);
        managedEmp.setLastAccessTime(new Date());
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
