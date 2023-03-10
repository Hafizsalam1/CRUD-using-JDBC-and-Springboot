package org.example.Repo;

import org.example.Model.Subject;
import org.example.Model.Teacher;
import org.example.Util.RandomUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("Teacher")
public class TeacherRepo implements IRepo<Teacher>{

    List <Teacher> teachers= new ArrayList<>();

    @Autowired
    RandomUUID randomUUID;

    @Override
    public List<Teacher> getAll() throws Exception {
        return teachers;
    }

    @Override
    public Teacher create(Teacher teacher) throws Exception {
        teacher.setId(randomUUID.Random());
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public Optional findById(String Id) throws Exception {
        for(Teacher teacher: teachers){
            if(teacher.getId().equals(Id)){
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }
}
