package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.dto.CreateGameTitleDto;
import com.konak.goodgames.domain.dto.GameTitleDto;
import com.konak.goodgames.domain.model.GameTitle;
import com.konak.goodgames.exception.BadRequestException;
import com.konak.goodgames.exception.NotFoundException;
import com.konak.goodgames.repository.GameTitleRepository;
import com.konak.goodgames.service.BlobStorageService;
import com.konak.goodgames.service.GameTitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameTitleServiceImpl implements GameTitleService {

  private final GameTitleRepository gameTitleRepository;

  private final BlobStorageService blobStorageService;

  private final ModelMapperService mapperService;

  @Override
  public void createGameTitle(CreateGameTitleDto dto) throws Exception {
    String imageUrl =
        blobStorageService.upload(getImageFile(dto.getImage()), dto.getImage().getContentType());
    GameTitle gameTitle = mapperService.map(dto, GameTitle.class);
    gameTitle.setImageUrl(imageUrl);
    gameTitleRepository.save(gameTitle);
  }

  @Override
  public void updateGameTitle(long gameTitleId, CreateGameTitleDto createGameTitleDto) throws IOException {
      GameTitle gameTitle =
          gameTitleRepository
              .findById(gameTitleId)
              .orElseThrow(() -> new NotFoundException("Game title not found"));
      gameTitle.setTitle(createGameTitleDto.getTitle());
      gameTitle.setDescription(createGameTitleDto.getDescription());
      if (!Objects.isNull(createGameTitleDto.getImage())) {
        blobStorageService.delete(gameTitle.getImageUrl());
        String imageUrl =
            blobStorageService.upload(
                getImageFile(createGameTitleDto.getImage()),
                createGameTitleDto.getImage().getContentType());
        gameTitle.setImageUrl(imageUrl);
      }
      gameTitleRepository.save(gameTitle);
  }

  @Override
  public Page<GameTitleDto> getGameTitles(Pageable pageable) {
    Page<GameTitle> gameTitles = gameTitleRepository.findAll(pageable);
    return new PageImpl<>(
        mapperService.map(
            gameTitles.getContent(), new TypeToken<List<GameTitleDto>>() {}.getType()),
        pageable,
        gameTitles.getTotalPages());
  }

  @Override
  public GameTitleDto getGameTitleById(long id) {
    GameTitle gameTitle =
        gameTitleRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Game title not found"));
    return mapperService.map(gameTitle, GameTitleDto.class);
  }

  @Override
  public GameTitle getGameTitle(long gameTitleId) {
    return gameTitleRepository
        .findById(gameTitleId)
        .orElseThrow(() -> new NotFoundException("Game title not found"));
  }

  @Override
  public void delete(long gameTitleId) throws IOException {
    try {
      GameTitle gameTitle =
          gameTitleRepository
              .findById(gameTitleId)
              .orElseThrow(() -> new NotFoundException("Game title not found"));
      blobStorageService.delete(gameTitle.getImageUrl());
      gameTitleRepository.deleteById(gameTitleId);
    } catch (IOException e) {
      log.error("Cannot delete image because {}", e.getMessage());
      throw e;
    }
  }

  private File getImageFile(MultipartFile image) throws IOException {

    InputStream initialStream = image.getInputStream();
    byte[] buffer = new byte[initialStream.available()];
    initialStream.read(buffer);

    File targetFile = new File(getRandomFilename());

    try (OutputStream outStream = new FileOutputStream(targetFile)) {
      outStream.write(buffer);
    }
    return targetFile;
  }

  private String getRandomFilename() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    return random
        .ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .append(".png")
        .toString();
  }
}
