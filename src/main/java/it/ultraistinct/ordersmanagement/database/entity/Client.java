package it.ultraistinct.ordersmanagement.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Client {

    @Id
    private Long id;

    private String companyName;
    private String vatNumber;
    private String fiscalCode;
}
