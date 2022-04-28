package model;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType)
}
