package com.administrador.api_gerencia.generic;

import com.administrador.api_gerencia.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GenericService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    public GenericService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> listar() {
        return repository.findAll();
    }

    public T buscarPorId(ID id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public T salvar(T entity) {
        return repository.save(entity);
    }

    public T editar(T entity) {
        return repository.save(entity);
    }

    public void deletar(ID id) {
        repository.deleteById(id);
    }

}
