package com.project.scm.Implementations;

import com.project.scm.Dao.ContactDao;
import com.project.scm.Entities.Contact;
import com.project.scm.Entities.User;
import com.project.scm.Helpers.ResourceNotFoundException;
import com.project.scm.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public Contact save(Contact contact) {
        return contactDao.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return contactDao.findAll();
    }

    @Override
    public Contact getById(Long id) {
        return contactDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with id:"+id));
    }

    @Override
    public void delete(Long id) {
        var contact = contactDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with id:"+id));
        contactDao.delete(contact);
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactDao.findByUserId(userId);
    }

    @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactDao.findByUser(user, pageable);

    }

    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactDao.findByUserAndNameContaining(user,nameKeyword,pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page,size,sort);

        return contactDao.findByUserAndEmailContaining(user,emailKeyword,pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order, User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page,size,sort);

        return contactDao.findByUserAndPhoneNumberContaining(user,phoneNumberKeyword,pageable);
    }


}
