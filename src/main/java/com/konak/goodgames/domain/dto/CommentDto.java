package com.konak.goodgames.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
  private long id;
  private long userId;
  private String username;
  private String comment;
  private LocalDateTime createdDate;
}
