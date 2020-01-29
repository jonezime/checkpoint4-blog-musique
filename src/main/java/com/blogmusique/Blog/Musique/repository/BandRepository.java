package com.blogmusique.Blog.Musique.repository;

import com.blogmusique.Blog.Musique.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    List<Band> findBandsById (Long id);

}
