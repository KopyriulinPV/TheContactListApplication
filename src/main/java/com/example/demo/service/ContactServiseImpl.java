package com.example.demo.service;

import com.example.demo.Contact;
import com.example.demo.repositopy.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiseImpl implements ContactServise{

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        contactRepository.update(contact);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
}
