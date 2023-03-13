package org.example.Controller;

import org.example.Model.Response.SuccessResponse;
import org.example.Model.Student;
import org.example.Service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    @Qualifier("Student")
    IService serviceStudent;



    @GetMapping
    public ResponseEntity getAllStudent() throws Exception {
        List<Student> students = serviceStudent.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Student>>("Get All Succeed", students));

    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) throws Exception {
        Student student1 = new Student();
        student1 = (Student) serviceStudent.create(student);
        System.out.println(student1);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Student>("Add Student Succeed", student1));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {

        Optional<Student> students = serviceStudent.findById(id);

       return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Student>>("Get by Id Succeed", students));

    }

    @PostMapping("/bulk")
    public ResponseEntity addBulkStudent(@RequestBody List<Student> students) throws Exception {
        List<Student> students1 =  serviceStudent.AddBulk(students);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Student>>("Add Bulk Student Succeed", students1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id) throws Exception {
        serviceStudent.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("Delete Succeed","Id student yang terhapus: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@RequestBody Student student, @PathVariable String id) throws Exception {
        serviceStudent.update(student, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Student>("Update Succeed",student));
    }




}
