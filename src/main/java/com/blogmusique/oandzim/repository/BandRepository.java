package com.blogmusique.oandzim.repository;

import com.blogmusique.oandzim.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    List<Band> findBandsById (Long id);
}
