package org.example.Controller;

import org.example.Model.Student;
import org.example.Model.Teacher;
import org.example.Service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    @Qualifier("Teacher")
    IService serviceTeacher;



    @GetMapping
    public ResponseEntity getAllTeacher() throws Exception {
        List<Teacher> teachers = serviceTeacher.getAll();
        if(teachers.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data masih kosong");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(teachers);

        }
    }

    @PostMapping
    public ResponseEntity createTeacher(@RequestBody Teacher teacher) throws Exception {
        Teacher teacher1 = new Teacher();
        teacher1 = (Teacher) serviceTeacher.create(teacher);
        System.out.println(teacher1);
        return ResponseEntity.status(HttpStatus.OK).body("sukses");
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {

        Optional<Teacher> teachers = serviceTeacher.findById(id);

        if (teachers.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }
        else{return ResponseEntity.status(HttpStatus.OK).body(teachers);}

    }

}
