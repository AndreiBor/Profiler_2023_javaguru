package by.itacademy.profiler.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.itacademy.profiler.persistence.model.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findSkillsByPositionsIdOrderByName(Long positionId);

    List<Skill> findAllByOrderByName();

}
