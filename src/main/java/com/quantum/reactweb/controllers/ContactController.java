package com.quantum.reactweb.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quantum.reactweb.models.Contact;
import com.quantum.reactweb.repositories.ContactRepository;

@RestController
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepo;
	
	@RequestMapping(method=RequestMethod.GET, value="/contacts")
	public Iterable<Contact> contact(){
		return contactRepo.findAll();
	}

	@RequestMapping(method=RequestMethod.POST, value="/contacts")
	public Contact save(@RequestBody Contact contact) {
		Contact savedContact = contactRepo.save(contact);
		
		return savedContact;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/contacts/{id}")
	public Optional<Contact> show(@PathVariable String id) {
		return contactRepo.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/contacts/{id}")
	public Contact update(@PathVariable String id, @RequestBody Contact contact) {
		Optional<Contact> optContact = contactRepo.findById(id);
		Contact c = optContact.get();
		
		if(contact.getName() != null) {
			c.setName(contact.getName());
		}
		if(contact.getAddress() != null)
            c.setAddress(contact.getAddress());
		
        if(contact.getCity() != null)
            c.setCity(contact.getCity());
        
        if(contact.getPhone() != null)
            c.setPhone(contact.getPhone());
        
        if(contact.getEmail() != null)
            c.setEmail(contact.getEmail());
        
        contactRepo.save(c);
        return c;		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/contacts/{id}")
	public String delete(@PathVariable String id) {
		Optional<Contact> optcontact = contactRepo.findById(id);
        Contact contact = optcontact.get();
        contactRepo.delete(contact);

        return "";
	}
}
