package com.konak.goodgames.repository;

import com.konak.goodgames.domain.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

  @Modifying
  @Transactional
  @Query("delete from Like l where l.gameTitle.id=:gameTitleId and l.createdBy.id=:userId")
  void deleteByGameTitleIdAndUserId(
      @Param("gameTitleId") long gameTitleId, @Param("userId") long userId);
}
