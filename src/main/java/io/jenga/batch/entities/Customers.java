package io.jenga.batch.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "second_name")
    private  String second_name;

    @Column(name = "status")
    private  Integer status;
}
