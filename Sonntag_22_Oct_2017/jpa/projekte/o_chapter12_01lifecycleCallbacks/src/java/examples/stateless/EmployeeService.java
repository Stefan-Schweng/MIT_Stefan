package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="o_chapter12_01lifecycleCallbacksPU")
    protected EntityManager em;
    
    public EntityManager getEntityManager() {
        return em;
    }

    public Employee createEmployee(int id, String name) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        getEntityManager().persist(emp);
        return emp;
    }

    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            getEntityManager().remove(emp);
        }
    }

    public Employee changeEmployeeName(int id, String newName) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            emp.setName(newName);
        }
        return emp;
    }

    public Employee findEmployee(int id) {
        return getEntityManager().find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        return getEntityManager().createQuery("SELECT e FROM Employee e", Employee.class)
                                 .getResultList();
    }
}
