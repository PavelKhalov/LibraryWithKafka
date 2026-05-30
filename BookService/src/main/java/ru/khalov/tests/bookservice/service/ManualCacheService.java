package ru.khalov.tests.bookservice.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ru.khalov.tests.bookservice.repository.BookRepository;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManualCacheService {

    private final BookRepository bookRepository;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;


    @PostConstruct
    public void init() {
        bookRepository.findAll().forEach(book -> {
            try{
                String json = objectMapper.writeValueAsString(book);
                redisTemplate.opsForValue().set("book:"+book.getId(), json);
            } catch (Exception e){
                log.error(e.getMessage());
                throw new RuntimeException();
            }
        });
    }


    public List<String> findAll() {
        Set<String> keys = redisTemplate.keys("book:*");

        if(keys == null || keys.isEmpty()) {
            return List.of();
        }

        List<String> books = redisTemplate.opsForValue().multiGet(keys);
        if(books == null){
            return List.of();
        }

        return books;
    }

    public String findById(Long id){
        log.info("find book with id = {} in cache", id);
        String objFromCache = redisTemplate.opsForValue().get("book:" + id); //Json формат

        if(objFromCache != null) {
            log.info("Book with id = {} founded", id);
            return objFromCache;
        } else {
            log.error("Book with id = {} not found", id);
            throw new EntityNotFoundException("Entity not found");
        }
    }

}
