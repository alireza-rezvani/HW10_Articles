package ir.maktab32.java.homeworks.hw10articles.entities.db2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String country;
    private String province;
    private String city;
    private String street;
    private String number;
    private String zipCode;

}
