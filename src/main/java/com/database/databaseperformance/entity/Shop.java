package com.database.databaseperformance.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Shop extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String status;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String type;

    @Column
    private String locationX;
    @Column
    private String locationY;

}
