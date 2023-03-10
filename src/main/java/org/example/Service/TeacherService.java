package org.example.Service;

import org.example.Model.Teacher;
import org.example.Repo.IRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@Qualifier("Teacher")
public class TeacherService implements IService<Teacher> {


    @Autowired
    @Qualifier("Teacher")
    IRepo teacherRepo;

    @Override
    public List<Teacher> getAll() throws Exception {
        try{return teacherRepo.getAll();}
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Teacher create(Teacher teacher) throws Exception {
        try{
            return (Teacher) teacherRepo.create(teacher);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Teacher> findById(String Id) throws Exception {
        try{ return teacherRepo.findById(Id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
