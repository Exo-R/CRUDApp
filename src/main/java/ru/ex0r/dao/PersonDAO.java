package ru.ex0r.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ex0r.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int PEOPLE_COUNT;


    public List<Person> index(){

        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){

        jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)",
                /*person.getId()*/ ++PEOPLE_COUNT, person.getName(), person.getEmail(), person.getAge());
    }

    public void update(int id, Person updatedPerson){

        jdbcTemplate.update("UPDATE Person SET name=?, email=?, age=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getEmail(), updatedPerson.getAge(), id);
    }

    public void delete(int id){

        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

}
