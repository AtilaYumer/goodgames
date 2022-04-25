package com.konak.goodgames.service;

public interface LikeService {
    void like(long gameTitleId);

    void dislike(long gameTitleId);
}
