package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Building;
import com.sjs.jsvill.entity.sample.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BuildingRepository extends JpaRepository<Building, Long> {

}