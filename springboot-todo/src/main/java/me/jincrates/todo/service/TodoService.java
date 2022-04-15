package me.jincrates.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.todo.domain.todo.Todo;
import me.jincrates.todo.domain.todo.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Todo> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<Todo> update(final Todo entity) {
        //1. 저장할 엔티티가 유효한지 확인한다.
        validate(entity);

        //2. 넘겨받은 엔티티가 id를 이용해 Todo를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문
        final Optional<Todo> original = repository.findById(entity.getId());

        //Lambda
        original.ifPresent(todo -> {
            //3. 반환된 Todo가 존재하면 값을 새 entity 값으로 덮어 씌운다.
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            //4. 데이터베이스에 새 값을 저장한다.
            repository.save(todo);
        });

        // 5. retrieve 메서드를 이용해 사용자의 모든 Todo 리스트를 리턴한다.
        return retrieve(entity.getUserId());
    }
}
