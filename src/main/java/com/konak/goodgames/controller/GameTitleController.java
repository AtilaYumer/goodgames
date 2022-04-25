package com.konak.goodgames.controller;

import com.konak.goodgames.domain.dto.CreateGameTitleDto;
import com.konak.goodgames.domain.dto.GameTitleDto;
import com.konak.goodgames.service.GameTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-titles")
public class GameTitleController {

  private final GameTitleService gameTitleService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void create(
      @RequestPart("title") String title,
      @RequestPart("description") String description,
      @RequestParam("image") MultipartFile imageFile)
      throws Exception {
    gameTitleService.createGameTitle(new CreateGameTitleDto(title, description, imageFile));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<GameTitleDto> getGameTitles(@PageableDefault Pageable pageable) {
    return gameTitleService.getGameTitles(pageable);
  }

  @GetMapping(path = "/{gameTitleId}")
  public GameTitleDto getGameTitleById(@PathVariable long gameTitleId) {
    return gameTitleService.getGameTitleById(gameTitleId);
  }
}
