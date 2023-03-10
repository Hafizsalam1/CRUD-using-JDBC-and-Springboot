package org.example.Controller;

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
        if(students.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data masih kosong");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(students);

        }
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) throws Exception {
        Student student1 = new Student();
        student1 = (Student) serviceStudent.create(student);
        System.out.println(student1);
        return ResponseEntity.status(HttpStatus.OK).body("sukses");
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {

        Optional<Student> students = serviceStudent.findById(id);

        if (students.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }
        else{return ResponseEntity.status(HttpStatus.OK).body(students);}

    }


}
