package com.konak.goodgames.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GameTitle {

    @Id
    @SequenceGenerator(name = "game_title_id_seq", sequenceName = "game_title_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "game_title_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String description;

    private String imageUrl;
}
