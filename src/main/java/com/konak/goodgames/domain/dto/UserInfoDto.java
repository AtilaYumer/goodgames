package com.konak.goodgames.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
  private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
}
