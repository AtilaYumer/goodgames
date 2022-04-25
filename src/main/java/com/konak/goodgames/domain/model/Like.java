package com.konak.goodgames.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Like {
  @Id
  @SequenceGenerator(name = "likes_id_seq", sequenceName = "likes_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "likes_id_seq", strategy = GenerationType.SEQUENCE)
  private long id;

  @ManyToOne
  @JoinColumn(name = "game_title_id")
  private GameTitle gameTitle;

  @ManyToOne
  @CreatedBy
  @JoinColumn(name = "created_by")
  private User createdBy;

  public Like(GameTitle gameTitle) {
    this.gameTitle = gameTitle;
  }
}
