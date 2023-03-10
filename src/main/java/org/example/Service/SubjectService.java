package org.example.Service;

import org.example.Model.Student;
import org.example.Model.Subject;
import org.example.Repo.IRepo;
import org.example.Repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
        try{return repoSubject.getAll();}
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject create(Subject subject) throws Exception {
        try{
            return (Subject) repoSubject.create(subject);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subject> findById(String Id) throws Exception {
        try{ return repoSubject.findById(Id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
