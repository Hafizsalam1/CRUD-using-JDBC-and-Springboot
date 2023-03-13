package org.example.Service;

import org.example.Exception.IdenticalDataException;
import org.example.Exception.MaximumDataException;
import org.example.Exception.NotFoundException;
import org.example.Model.Subject;
import org.example.Repo.IRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("Subject")
public class SubjectService implements IService<Subject> {
    @Autowired
    @Qualifier("Subject")
    IRepo repoSubject;


    @Override
    public List<Subject> getAll() throws Exception {
        try {
            List<Subject> subjects = repoSubject.getAll();

            if (subjects.isEmpty()) {
                throw new NotFoundException("Subject Not Found");
            }
            return subjects;
        }

        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject create(Subject subject) throws Exception {
        try{
            List<Subject> subjects = repoSubject.getAll();

            if (subjects.size()>=9){
                throw new MaximumDataException("Kapasitas maksimum data subjek sudah terpenuhi");
            }
            else {
                for(Subject subject1: subjects){
                    if(subject1.getSubject_name().equals(subject.getSubject_name())){
                        throw new IdenticalDataException("Subject name cannot be identical");
                    }
                }
                Subject subject1 = (Subject) repoSubject.create(subject);
                return subject1;
            }
            }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subject> findById(String Id) throws Exception {
        try{Optional<Subject> subjects = repoSubject.findById(Id);
            if (subjects.isEmpty()) {
                throw new NotFoundException("Subject Not Found");
            }
            return subjects;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subject> AddBulk(List<Subject> subjects) throws Exception {
        try{
            List<Subject> subjects1 = repoSubject.getAll();
            if (subjects1.size()+subjects.size()>=8){
                throw new MaximumDataException("Kapasitas maksimum data subjek sudah terpenuhi");
            }
            else{

                List<Subject> subjects2 = repoSubject.AddBulk(subjects);
                return subjects2;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Subject subject, String Id) throws Exception {
        try{
            List<Subject> subjects = repoSubject.getAll();
            List<Subject> subjects1 = new ArrayList<Subject>();
            for(Subject subject1: subjects){
                if(subject.getId().equals(Id)){
                    subjects1.add(subject1);
                }
            }
            for(Subject subject1: subjects){
                if(subject1.getSubject_name().equals(subject.getSubject_name())){
                    throw new IdenticalDataException("Subject name cannot be identical");
                }
            }
            if(subjects1.isEmpty()){
                throw new NotFoundException("Subject Not Found");
            }
            else{ repoSubject.update(subject, Id);
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String Id) throws Exception {
        try{
            List<Subject> subjects = repoSubject.getAll();
            List<Subject> subjects1 = new ArrayList<Subject>();
            for(Subject subject: subjects){
                if(subject.getId().equals(Id)){
                    repoSubject.delete(Id);
                    subjects1.add(subject);
                }
            }
            if(subjects1.isEmpty()){
                throw new NotFoundException("Subject Not Found");
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
