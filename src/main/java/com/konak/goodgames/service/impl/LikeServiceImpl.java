package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.dto.UserInfoDto;
import com.konak.goodgames.domain.model.GameTitle;
import com.konak.goodgames.domain.model.Like;
import com.konak.goodgames.repository.LikeRepository;
import com.konak.goodgames.service.GameTitleService;
import com.konak.goodgames.service.LikeService;
import com.konak.goodgames.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final UserService userService;
  private final GameTitleService gameTitleService;
  private final LikeRepository likeRepository;

  @Override
  public void like(long gameTitleId) {
    GameTitle gameTitle = gameTitleService.getGameTitle(gameTitleId);
    likeRepository.save(new Like(gameTitle));
  }

  @Override
  public void dislike(long gameTitleId) {
    UserInfoDto userInfo = userService.getUserInfo();
    likeRepository.deleteByGameTitleIdAndUserId(gameTitleId, userInfo.getId());
  }
}
