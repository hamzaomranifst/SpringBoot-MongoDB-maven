package com.hamzaomranifst.springbootmongodbrestapi.repositories;

import com.hamzaomranifst.springbootmongodbrestapi.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    
}