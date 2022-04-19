package com.konak.goodgames.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameTitleDto {
  private Long id;

  private String title;

  private String description;

  private String imageUrl;
}
