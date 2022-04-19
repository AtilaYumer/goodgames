package com.konak.goodgames.repository;

import com.konak.goodgames.domain.model.GameTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameTitleRepository extends JpaRepository<GameTitle, Long> {

    @Query("select gt from GameTitle gt")
    Page<GameTitle> findAll(Pageable pageable);
}
