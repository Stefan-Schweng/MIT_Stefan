package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="m_chapter10_19singleTableInheritancePU")
    protected EntityManager em;

    public void createEmployee(Employee emp) {
        em.persist(emp);
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
