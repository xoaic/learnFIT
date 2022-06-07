package com.example.se2project.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Length(min = 2, max = 20)
    private String name;
}
