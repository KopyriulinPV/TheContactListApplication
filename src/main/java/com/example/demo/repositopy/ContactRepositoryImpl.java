package com.example.demo.repositopy;
import com.example.demo.Contact;
import com.example.demo.repositopy.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String sql = "Select * from contacts where id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        contact.setId(System.currentTimeMillis());
        String sql = "insert into contacts (id, firstName, lastName, email, phone) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        Contact existedTask = findById(contact.getId()).orElse(null);
        if (existedTask != null) {
            String sql = "UPDATE contacts SET firstName = ?, lastName = ?, email = ?, phone = ? where id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
