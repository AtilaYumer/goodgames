package com.konak.goodgames.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameTitleDto {
  private Long id;

  private String title;

  private String description;

  private String imageUrl;

  private String createdBy;

  private long createdById;

  private List<Long> likes;

  private List<CommentDto> comments;
}
