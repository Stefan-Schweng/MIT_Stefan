package examples.model;

import examples.model.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-08-20T09:49:54")
@StaticMetamodel(Phone.class)
public class Phone_ { 

    public static volatile SingularAttribute<Phone, String> number;
    public static volatile SingularAttribute<Phone, Long> id;
    public static volatile SingularAttribute<Phone, String> type;
    public static volatile SingularAttribute<Phone, Employee> employee;

}