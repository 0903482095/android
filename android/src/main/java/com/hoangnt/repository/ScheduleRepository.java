package com.hoangnt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hoangnt.entity.Schedule;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	@Query("SELECT s FROM Schedule s WHERE s.user.id like ?1")
	List<Schedule> findByIdUser(int id);
}
