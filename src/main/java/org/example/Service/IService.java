package org.example.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    public List<T> getAll() throws Exception;

    public T create(T params) throws Exception;

    public Optional<T> findById(String Id) throws Exception;

    public List<T> AddBulk(List<T> params) throws Exception;

    public void update(T params, String Id) throws Exception;

    public void delete(String Id) throws Exception;


}
