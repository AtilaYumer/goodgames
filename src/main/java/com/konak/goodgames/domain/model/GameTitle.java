package com.konak.goodgames.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing()
public class GameTitle {

  @Id
  @SequenceGenerator(
      name = "game_title_id_seq",
      sequenceName = "game_title_id_seq",
      allocationSize = 1)
  @GeneratedValue(generator = "game_title_id_seq", strategy = GenerationType.SEQUENCE)
  private Long id;

  private String title;

  private String description;

  private String imageUrl;

  @ManyToOne
  @CreatedBy
  @JoinColumn(name = "created_by")
  private User createdBy;

  @CreatedDate private LocalDateTime createdDate;

  @OneToMany(mappedBy = "gameTitle")
  private List<Like> likes;

  @OneToMany(mappedBy = "gameTitle")
  @OrderBy("createdDate desc")
  private List<Comment> comments;
}
