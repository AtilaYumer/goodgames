package com.konak.goodgames.controller;

import com.konak.goodgames.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-titles/{gameTitleId}")
public class LikeController {

  private final LikeService likeService;

  @PostMapping(path = "/like")
  @ResponseStatus(code = HttpStatus.CREATED)
  public void like(@PathVariable long gameTitleId) {
    likeService.like(gameTitleId);
  }

  @DeleteMapping(path = "/like")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void dislike(@PathVariable long gameTitleId) {
    likeService.dislike(gameTitleId);
  }
}
