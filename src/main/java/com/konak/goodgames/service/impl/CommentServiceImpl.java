package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.dto.CommentDto;
import com.konak.goodgames.domain.model.Comment;
import com.konak.goodgames.domain.model.GameTitle;
import com.konak.goodgames.repository.CommentRepository;
import com.konak.goodgames.service.CommentService;
import com.konak.goodgames.service.GameTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final GameTitleService gameTitleService;
  private final CommentRepository commentRepository;

  @Override
  public void comment(long gameTitleId, CommentDto commentDto) {
    GameTitle gameTitle = gameTitleService.getGameTitle(gameTitleId);
    commentRepository.save(new Comment(gameTitle, commentDto.getComment()));
  }

  @Override
  public void edit(long commentId) {}

  @Override
  public void delete(long commentId) {
    commentRepository.deleteById(commentId);
  }
}
