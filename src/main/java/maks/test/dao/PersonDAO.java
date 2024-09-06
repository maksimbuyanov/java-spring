package maks.test.dao;

import maks.test.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "John", 23, "John@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 28, "Mike@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Jane", 40, "Jane@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mary", 4, "Mary@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Peter", 90, "Peter@gmail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToUpdate = show(id);
        personToUpdate.setName(person.getName());
        personToUpdate.setAge(person.getAge());
        personToUpdate.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
