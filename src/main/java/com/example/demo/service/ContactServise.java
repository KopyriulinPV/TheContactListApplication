package com.example.demo.service;
import com.example.demo.Contact;
import java.util.List;

public interface ContactServise {

    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(Long id);

}
