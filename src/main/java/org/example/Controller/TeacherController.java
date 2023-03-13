package org.example.Controller;

import org.example.Model.Response.SuccessResponse;
import org.example.Model.Student;
import org.example.Model.Subject;
import org.example.Model.Teacher;
import org.example.Service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
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
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Teacher>>("Get All Succeed", teachers));

    }

    @PostMapping
    public ResponseEntity createTeacher(@RequestBody Teacher teacher) throws Exception {
        Teacher teacher1 = new Teacher();
        teacher1 = (Teacher) serviceTeacher.create(teacher);
        System.out.println(teacher1);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Teacher>("Add Teacher Succeed", teacher1));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {

        Optional<Teacher> teachers = serviceTeacher.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Teacher>>("Get by Id Succeed", teachers));

    }

    @PostMapping("/bulk")
    public ResponseEntity addBulkTeacher(@RequestBody List<Teacher> teachers) throws Exception {
        List<Teacher> teachers1 =  serviceTeacher.AddBulk(teachers);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Teacher>>("Add Bulk Teacher Succeed", teachers1));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id) throws Exception {
        serviceTeacher.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("Delete Succeed","Id guru yang terhapus: "+ id ));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@RequestBody Teacher teacher, @PathVariable String id) throws Exception {
        serviceTeacher.update(teacher, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Teacher>("Update Succeed",teacher));
    }

}
