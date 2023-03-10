package org.example.Controller;

import org.example.Model.Student;
import org.example.Model.Subject;
import org.example.Service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    @Qualifier("Subject")
    IService serviceSubject;



    @GetMapping
    public ResponseEntity getAllSubject() throws Exception {
        List<Subject> subjects = serviceSubject.getAll();
        if(subjects.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data masih kosong");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(subjects);

        }
    }

    @PostMapping
    public ResponseEntity createSubject(@RequestBody Subject subject) throws Exception {
        Subject subject1 = new Subject();
        subject1 = (Subject) serviceSubject.create(subject);
        System.out.println(subject1);
        return ResponseEntity.status(HttpStatus.OK).body("sukses");
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {

        Optional<Subject> subjects = serviceSubject.findById(id);

        if (subjects.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }
        else{return ResponseEntity.status(HttpStatus.OK).body(subjects);}

    }


}
