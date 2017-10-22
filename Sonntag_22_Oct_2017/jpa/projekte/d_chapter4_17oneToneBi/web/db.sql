DROP TABLE employee;
DROP TABLE department;

CREATE TABLE PARKING_SPACE (ID INTEGER  NOT NULL AUTO_INCREMENT, 
                            LOT INTEGER, LOCATION VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE EMPLOYEE (ID INTEGER  NOT NULL AUTO_INCREMENT, 
                       NAME VARCHAR(255), SALARY BIGINT, 
                       PSPACE_ID INTEGER, PRIMARY KEY (ID),
                       CONSTRAINT PSPACE_FK FOREIGN KEY (PSPACE_ID) REFERENCES PARKING_SPACE(ID));


INSERT INTO PARKING_SPACE (LOT, LOCATION) VALUES (1, 'East Lot');
INSERT INTO PARKING_SPACE (LOT , LOCATION) VALUES (2, 'West Lot');

INSERT INTO EMPLOYEE (NAME, SALARY, PSPACE_ID) VALUES ('Jenny', 59000, 1);
INSERT INTO EMPLOYEE (NAME, SALARY, PSPACE_ID) VALUES ('John', 58000, 2);

