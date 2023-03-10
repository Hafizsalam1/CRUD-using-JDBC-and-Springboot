package org.example.Repo;

import org.example.Model.Student;
import org.example.Model.Subject;
import org.example.Util.RandomUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("Subject")
public class SubjectRepo implements IRepo<Subject> {

    List <Subject> subjects = new ArrayList<>();

    @Autowired
    RandomUUID randomUUID;

    @Override
    public List<Subject> getAll() throws Exception {
        return subjects;
    }

    @Override
    public Subject create(Subject subject) throws Exception {
        subject.setId(randomUUID.Random());
        subjects.add(subject);
        return subject;
    }

    @Override
    public Optional findById(String Id) throws Exception {
        for(Subject subject: subjects){
            if(subject.getId().equals(Id)){
                return Optional.of(subject);
            }
        }
        return Optional.empty();

    }
}
