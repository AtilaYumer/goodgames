package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.dto.CreateGameTitleDto;
import com.konak.goodgames.domain.dto.GameTitleDto;
import com.konak.goodgames.domain.model.GameTitle;
import com.konak.goodgames.repository.GameTitleRepository;
import com.konak.goodgames.service.BlobStorageService;
import com.konak.goodgames.service.GameTitleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Random;

@Service
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
  public Page<GameTitleDto> getGameTitles(Pageable pageable) {
    Page<GameTitle> gameTitles = gameTitleRepository.findAll(pageable);
    return new PageImpl<>(
        mapperService.map(
            gameTitles.getContent(), new TypeToken<List<GameTitleDto>>() {}.getType()),
        pageable,
        gameTitles.getTotalPages());
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
