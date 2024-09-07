package maks.test.dao;

import maks.test.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate template;

    @Autowired
    public PersonDAO(JdbcTemplate template) {
        this.template = template;
    }

    public List<Person> index() {
        List<Person> people = template.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
        people.forEach(System.out::println);
        return people;
    }

    public Person show(int id) {
        return template.query("SELECT * FROM person WHERE id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findFirst().get();
    }

    public void save(Person person) {
        template.update("INSERT INTO person (name, age, email) VALUES (?,?,?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        template.update("UPDATE person SET name =?, age =?, email =? WHERE id =?", person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        template.update("DELETE FROM person WHERE id =?", id);
    }
}
