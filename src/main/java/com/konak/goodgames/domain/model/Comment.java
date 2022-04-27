package com.konak.goodgames.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
  @Id
  @SequenceGenerator(name = "comments_id_seq", sequenceName = "comments_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "comments_id_seq", strategy = GenerationType.SEQUENCE)
  private long id;

  private String comment;

  @ManyToOne
  @JoinColumn(name = "game_title_id")
  private GameTitle gameTitle;

  @ManyToOne
  @CreatedBy
  @JoinColumn(name = "created_by")
  private User createdBy;

  @CreatedDate private LocalDateTime createdDate;

  public Comment(GameTitle gameTitle, String comment) {
    this.gameTitle = gameTitle;
    this.comment = comment;
  }
}
