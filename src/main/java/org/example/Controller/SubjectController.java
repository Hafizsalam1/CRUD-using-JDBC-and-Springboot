package org.example.Controller;

import org.example.Model.Response.SuccessResponse;
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
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Subject>>("Get All Succeed", subjects));
    }

    @PostMapping
    public ResponseEntity createSubject(@RequestBody Subject subject) throws Exception {
        Subject subject1 = new Subject();
        subject1 = (Subject) serviceSubject.create(subject);
        System.out.println(subject1);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Subject>("Add Subject Succeed", subject1));
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {

        Optional<Subject> subjects = serviceSubject.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Subject>>("Get by Id Succeed", subjects));
    }

    @PostMapping("/bulk")
    public ResponseEntity addBulkSubject(@RequestBody List<Subject> subjects) throws Exception {
        List<Subject> subjects1 =  serviceSubject.AddBulk(subjects);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Subject>>("Add Bulk Subject Succeed", subjects1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id) throws Exception {
        serviceSubject.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("Delete Succeed","Id subjek yang terhapus: "+ id ));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@RequestBody Subject subject, @PathVariable String id) throws Exception {
        serviceSubject.update(subject, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Subject>("Update Succeed",subject));
    }


}
