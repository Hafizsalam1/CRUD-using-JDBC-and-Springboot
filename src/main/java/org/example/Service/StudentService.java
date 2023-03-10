package org.example.Service;

import org.example.Model.Student;
import org.example.Model.Teacher;
import org.example.Repo.IRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("Student")
public class StudentService implements IService<Student> {

    @Autowired
    @Qualifier("Student")
    IRepo repoStudent;

    @Override
    public List<Student> getAll() throws Exception {
        try{return repoStudent.getAll();}
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student create(Student student) throws Exception {
        try{
            return (Student) repoStudent.create(student);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> findById(String Id) throws Exception {
        try{ return repoStudent.findById(Id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
