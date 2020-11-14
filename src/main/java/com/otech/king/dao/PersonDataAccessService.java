package com.otech.king.dao;

import com.otech.king.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override // Need to finish this method
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO public.person (id, name) VALUES (?, ?)";
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT * FROM public.person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
           UUID id = UUID.fromString(resultSet.getString("id"));
           String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM public.person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM public.person WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override // Need to finish this method
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE public.person SET name = ? WHERE id = ?";
        //jdbcTemplate.update(sql, new Object[]{id}, (resultSet, i) -> {});
        return 0;
    }
}
