package examples.stateless;

import java.util.List;
import javax.ejb.*;
import javax.persistence.*;
import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="iii_chapter7_09bulkQueryExamplePU")
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void assignManager(Department dept, Employee manager) {
         em.createQuery("UPDATE Employee e " +
                        "SET e.manager = :manager " +
                        "WHERE e.department = :dept ")
           .setParameter("manager", manager)
           .setParameter("dept", dept)
           .executeUpdate();
    }

    public Employee findEmployee(int id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}

