package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.binding.ContactForm;
import com.example.service.ContactService;

@RestController
public class ContactController {
	
	@Autowired
	private ContactService service ;
	
	@PostMapping("/save")
	public String saveContacts(@RequestBody ContactForm form) {
		String saveContact = service.saveContact(form);
		return saveContact;
	}
	@GetMapping("/contacts")
	public List<ContactForm>viewContacts(){
		List<ContactForm> viewContacts = service.viewContacts();
		return viewContacts;
	}
	@GetMapping("edit/{contactId}")
	public ContactForm editContact(@PathVariable Integer contactId) {
		ContactForm editContact = service.editContact(contactId);
		return editContact;
	}
	@DeleteMapping("delete/{contactId}")
	public List <ContactForm>deleteContact(@PathVariable Integer contactId){
		List<ContactForm> deleteContact = service.deleteContact(contactId);
		return deleteContact;
	}

}
