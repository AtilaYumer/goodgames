package com.konak.goodgames.service;

import com.konak.goodgames.domain.dto.CreateGameTitleDto;
import com.konak.goodgames.domain.dto.GameTitleDto;
import com.konak.goodgames.domain.model.GameTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface GameTitleService {
    void createGameTitle(CreateGameTitleDto dto) throws Exception;

    void updateGameTitle(long gameTitleId, CreateGameTitleDto createGameTitleDto) throws IOException;

    Page<GameTitleDto> getGameTitles(Pageable pageable);

    GameTitleDto getGameTitleById(long id);

    GameTitle getGameTitle(long gameTitleId);

    void delete(long gameTitleId) throws IOException;
}
