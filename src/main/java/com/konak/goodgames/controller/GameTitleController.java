package com.konak.goodgames.controller;

import com.konak.goodgames.domain.dto.CreateGameTitleDto;
import com.konak.goodgames.domain.dto.GameTitleDto;
import com.konak.goodgames.service.GameTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-titles")
public class GameTitleController {

  private final GameTitleService gameTitleService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  public void create(
      @RequestPart("title") String title,
      @RequestPart("description") String description,
      @RequestParam("image") MultipartFile imageFile)
      throws Exception {
    gameTitleService.createGameTitle(new CreateGameTitleDto(title, description, imageFile));
  }

  @PutMapping(path = "/{gameTitleId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void update(
          @PathVariable long gameTitleId,
          @RequestPart("title") String title,
          @RequestPart("description") String description,
          @RequestParam(value = "image", required = false) MultipartFile imageFile)
          throws Exception {
    gameTitleService.updateGameTitle(gameTitleId, new CreateGameTitleDto(title, description, imageFile));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<GameTitleDto> getGameTitles(
      @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
    return gameTitleService.getGameTitles(pageable);
  }

  @GetMapping(path = "/{gameTitleId}")
  public GameTitleDto getGameTitleById(@PathVariable long gameTitleId) {
    return gameTitleService.getGameTitleById(gameTitleId);
  }

  @DeleteMapping(path = "/{gameTitleId}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long gameTitleId) throws IOException {
    gameTitleService.delete(gameTitleId);
  }
}
