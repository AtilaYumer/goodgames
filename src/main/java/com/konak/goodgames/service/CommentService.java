package com.konak.goodgames.service;

import com.konak.goodgames.domain.dto.CommentDto;

public interface CommentService {
  void comment(long gameTitleId, CommentDto commentDto);

  void edit(long commentId);

  void delete(long commentId);
}
