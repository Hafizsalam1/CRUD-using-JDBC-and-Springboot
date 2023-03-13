package org.example.Service;

import org.example.Exception.MaximumDataException;
import org.example.Exception.NotFoundException;
import org.example.Model.Student;
import org.example.Model.Teacher;
import org.example.Repo.IRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.ArrayList;
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
        try {
            List<Student> students = repoStudent.getAll();

            if (students.isEmpty()) {
                throw new NotFoundException("Student Not Found");
            }
            return students;
        }

        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student create(Student student) throws Exception {
        try{
            List<Student> students = repoStudent.getAll();

            if (students.size()>=25){
                throw new MaximumDataException("Kapasitas maksimum data siswa sudah terpenuhi");
            }
            else {
                Student student1 = (Student) repoStudent.create(student);
                return student1;

            }

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> findById(String Id) throws Exception {
        try{Optional<Student> students = repoStudent.findById(Id);
            if (students.isEmpty()) {
                throw new NotFoundException("Student Not Found");
            }
            return students;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> AddBulk(List<Student> students) throws Exception {
        try{
            List<Student> students1 = repoStudent.getAll();
            if (students1.size()+students.size()>=24){
                throw new MaximumDataException("Kapasitas maksimum data siswa sudah terpenuhi");
            }
            else{
                List<Student> students2 = repoStudent.AddBulk(students);
                return students2;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student, String Id) throws Exception {
        try{
            List<Student> students = repoStudent.getAll();
            List<Student> students1 = new ArrayList<Student>();
            for(Student student1: students){
                if(student1.getId().equals(Id)){
                    repoStudent.update(student, Id);
                    students1.add(student1);
                }
            }
            if(students1.isEmpty()){
                throw new NotFoundException("Student Not Found");
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(String Id) throws Exception {
        try{
            List<Student> students = repoStudent.getAll();
            List<Student> students1 = new ArrayList<Student>();
            for(Student student1: students){
                if(student1.getId().equals(Id)){
                    repoStudent.delete(Id);
                    students1.add(student1);
                }
            }
            if(students1.isEmpty()){
                throw new NotFoundException("Student Not Found");
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
