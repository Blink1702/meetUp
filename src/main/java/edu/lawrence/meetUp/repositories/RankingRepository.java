package edu.lawrence.meetUp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.meetUp.entities.Ranking;


public interface RankingRepository extends JpaRepository<Ranking,UUID>{

}
