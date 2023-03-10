package org.example.Repo;

import org.example.Model.Student;
import org.example.Util.RandomUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("Student")
public class StudentRepo implements IRepo<Student>{

    @Autowired
    RandomUUID randomUUID;

List<Student> students= new ArrayList<>();

    @Override
    public List getAll() throws Exception {
        return students;
    }

    @Override
    public Student create(Student student) throws Exception {
        student.setId(randomUUID.Random());
        students.add(student);
        return student;
    }

    @Override
    public Optional findById(String Id) throws Exception {
        for(Student student: students){
            if(student.getId().equals(Id)){
                return Optional.of(students);

            }
        }
        return Optional.empty();

    }

}
