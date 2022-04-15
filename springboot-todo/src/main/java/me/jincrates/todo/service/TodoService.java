package me.jincrates.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.todo.domain.todo.Todo;
import me.jincrates.todo.domain.todo.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository repository;

    public List<Todo> create(final Todo entity) {
        //Validations
        validate(entity);

        repository.save(entity);

        log.info("Entity Id : " + entity.getId() + " is saved.");

        return repository.findByUserId(entity.getUserId());
    }

    //리팩터링한 메서드
    public void validate(final Todo entity) {
        if (entity == null) {
            log.warning("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warning("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
