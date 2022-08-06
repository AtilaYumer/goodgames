package com.konak.goodgames.domain.model;

import com.konak.goodgames.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "userRoles")
@Getter
@Setter
public class UserRole  {

    @Id
    @SequenceGenerator(name = "comments_id_seq", sequenceName = "comments_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "comments_id_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private Role role;
}
