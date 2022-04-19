package com.konak.goodgames.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameTitleDto {
  private String title;
  private String description;
  private MultipartFile image;
}
