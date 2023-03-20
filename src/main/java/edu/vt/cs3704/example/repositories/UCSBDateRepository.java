package edu.vt.cs3704.example.repositories;

import edu.vt.cs3704.example.entities.UCSBDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UCSBDateRepository extends CrudRepository<UCSBDate, Long> {
  Iterable<UCSBDate> findAllByQuarterYYYYQ(String quarterYYYYQ);
}
