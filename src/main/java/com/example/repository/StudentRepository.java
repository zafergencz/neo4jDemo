package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends Neo4jRepository<Student, Long> {

    List<Student> findByName(String name);
    List<Student> findByNameAndBirthYear(String name, Integer birthYear);
    List<Student> findByNameOrBirthYear(String name, Integer birthYear);
    List<Student> findByBirthYearIn(List<Integer> birthYears);

    @Query("match (st:Student) where st.name = $name and " +
            "st.birth_year = $birth_year return st")
    List<Student> getByNameAndBirthYear(String name,
                                        @Param("birth_year") Integer birthYear);

    List<Student> findByNameLike(String name);
    List<Student> findByNameStartsWith(String name);
}
