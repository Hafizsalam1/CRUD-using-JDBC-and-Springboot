package org.example.Service;

import org.example.Exception.MaximumDataException;
import org.example.Exception.NotFoundException;
import org.example.Model.Student;
import org.example.Model.Subject;
import org.example.Model.Teacher;
import org.example.Repo.IRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        try {
            List<Teacher> teacherss = teacherRepo.getAll();

            if (teacherss.isEmpty()) {
                throw new NotFoundException("Teacher Not Found");
            }
            return teacherss;
        }

        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Teacher create(Teacher teacher) throws Exception {
        try{
            List<Teacher> teachers = teacherRepo.getAll();

            if (teachers.size()>=6){
                throw new MaximumDataException("Kapasitas maksimum data guru sudah terpenuhi");
            }
            else {
                Teacher teacher1 = (Teacher) teacherRepo.create(teacher);
                return teacher1;
            } }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Teacher> findById(String Id) throws Exception {
        try{Optional<Teacher> teachers = teacherRepo.findById(Id);
            if (teachers.isEmpty()) {
                throw new NotFoundException("Teacher Not Found");
            }
            return teachers;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Teacher> AddBulk(List<Teacher> teachers) throws Exception {
        try{
            List<Teacher> teachers1 = teacherRepo.getAll();
            if (teachers1.size()+teachers.size()>=8){
                throw new MaximumDataException("Kapasitas maksimum data guru sudah terpenuhi");
            }
            else{
                List<Teacher> teachers2 = teacherRepo.AddBulk(teachers);
                return teachers2;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Teacher teacher, String Id) throws Exception {
        try{
            List<Teacher> teachers = teacherRepo.getAll();
            List<Teacher> teachers1 = new ArrayList<Teacher>();
            for(Teacher teacher1: teachers){
                if(teacher1.getId().equals(Id)){
                    teacherRepo.update(teacher, Id);
                    teachers1.add(teacher1);
                }
            }
            if(teachers1.isEmpty()){
                throw new NotFoundException("Teacher Not Found");
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String Id) throws Exception {
        try{
            List<Teacher> teachers = teacherRepo.getAll();
            List<Teacher> teachers1 = new ArrayList<Teacher>();
            for(Teacher teacher: teachers){
                if(teacher.getId().equals(Id)){
                    teacherRepo.delete(Id);
                    teachers1.add(teacher);
                }
            }
            if(teachers1.isEmpty()){
                throw new NotFoundException("Teacher Not Found");
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
