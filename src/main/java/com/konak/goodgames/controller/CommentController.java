package com.konak.goodgames.controller;

import com.konak.goodgames.domain.dto.CommentDto;
import com.konak.goodgames.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("game-titles/{gameTitleId}/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public void comment(@PathVariable long gameTitleId, @RequestBody CommentDto commentDto) {
    commentService.comment(gameTitleId, commentDto);
  }

  @DeleteMapping(path = "/{commentId}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long commentId) {
    commentService.delete(commentId);
  }
}
