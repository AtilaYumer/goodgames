package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.dto.GameTitleDto;
import com.konak.goodgames.domain.dto.UserDto;
import com.konak.goodgames.domain.model.GameTitle;
import com.konak.goodgames.domain.model.Like;
import com.konak.goodgames.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService extends ModelMapper {

  public ModelMapperService() {
    userDtoToUser();
    gameTitleToGameTitleDto();
    likeToLong();
  }

  private void userDtoToUser() {
    createTypeMap(UserDto.class, User.class)
        .setPostConverter(
            mappingContext -> {
              UserDto source = mappingContext.getSource();
              User destination = mappingContext.getDestination();

              BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
              String encodedPassword = bCryptPasswordEncoder.encode(source.getPassword());
              destination.setPassword(encodedPassword);
              return destination;
            });
  }

  private void gameTitleToGameTitleDto() {
    createTypeMap(GameTitle.class, GameTitleDto.class)
        .setPostConverter(
            mappingContext -> {
              GameTitle source = mappingContext.getSource();
              GameTitleDto destination = mappingContext.getDestination();

              if (source.getCreatedBy() != null) {
                User createdBy = source.getCreatedBy();
                destination.setCreatedBy(
                    String.format("%s %s", createdBy.getFirstName(), createdBy.getLastName()));
                destination.setCreatedById(createdBy.getId());
              }
              return destination;
            });
  }

  private void likeToLong() {
    createTypeMap(Like.class, Long.class)
        .setPostConverter(
            mappingContext -> {
              Like source = mappingContext.getSource();
              return source.getCreatedBy().getId();
            });
  }
}
