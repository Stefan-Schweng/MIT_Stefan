package examples.model;

import examples.model.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-08-20T09:49:54")
@StaticMetamodel(ParkingSpace.class)
public class ParkingSpace_ { 

    public static volatile SingularAttribute<ParkingSpace, Integer> lot;
    public static volatile SingularAttribute<ParkingSpace, String> location;
    public static volatile SingularAttribute<ParkingSpace, Integer> id;
    public static volatile SingularAttribute<ParkingSpace, Employee> employee;

}