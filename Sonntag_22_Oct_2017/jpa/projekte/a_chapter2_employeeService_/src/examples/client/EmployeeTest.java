package examples.client;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.Employee;
import examples.model.EmployeeService;

public class EmployeeTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("a_chapter2_employeeService_PU");
        EntityManager em = emf.createEntityManager();
        EmployeeService service = new EmployeeService(em);
        
        //  create and persist an employee
        em.getTransaction().begin();
            Employee emp = service.createEmployee(200, "Peter Doe", 45000);
        em.getTransaction().commit();
        System.out.println("Persisted " + emp);
        
        // find a specific employee
        emp = service.findEmployee(200);
        System.out.println("Found " + emp);
        
        // find all employees
        Collection<Employee> emps = service.findAllEmployees();
        for (Employee e : emps) 
            System.out.println("Found Employee: " + e);
        
        // update the employee
        em.getTransaction().begin();
            emp = service.raiseEmployeeSalary(200, 1000);
        em.getTransaction().commit();
        System.out.println("Updated " + emp);  
        
        // close the EM and EMF when done
        em.close();
        emf.close();
    }
}

