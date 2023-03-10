package org.example.Repo;

import org.example.Model.Student;

import java.util.List;
import java.util.Optional;

public interface IRepo <T>{
   public List<T> getAll() throws Exception;

    public T create(T params) throws Exception;

    public Optional<T> findById(String Id) throws Exception;


}
