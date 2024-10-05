package SpringBootApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private int id;

    private String brand;
    private String desc;
    private int qty;
    private int price;

}

/*
      IoC - Inversion of Control, DI - Dependency Injection
      Spring supports 2 DIs :
          - Constructor Injection
          - Setter Injection



       SpringBoot =
          Spring
        - Configuration(xml)
        +
      {
          Auto Configuration +
          Actuators +
          Starters +
          Dev Tools +
          Tomcat +
          Cloud +
          In Memory DB +
          CLI
      }

        Two Container:
            - Bean Factory
            - Application Context

*/