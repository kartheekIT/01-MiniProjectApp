package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binding.ContactForm;
import com.example.entity.Contact;
import com.example.repository.ContactRepository;

@Service
public class ContactServiceimpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepo;

	@Override
	public String saveContact(ContactForm form) {
		Contact entity=new Contact();
		BeanUtils.copyProperties(form, entity);		
		 entity = contactRepo.save(entity);
		if(entity.getContactId()!=null) {
			return "SUCESS";
		}
		 
		return "failure";
		
	}

	@Override
	public List<ContactForm> viewContacts() {
		List<ContactForm>datalist=new ArrayList<>();
		List<Contact> findAll = contactRepo.findAll();
		for(Contact entity  : findAll) {
			ContactForm form =new ContactForm();
			BeanUtils.copyProperties(entity, form);
			datalist.add(form);
		}
		return datalist;
	}

	@Override
	public ContactForm editContact(Integer contactId) {
		Optional<Contact> findById = contactRepo.findById(contactId);
		if(findById.isPresent()) {
			Contact entity=findById.get();
			ContactForm form=new ContactForm();
			BeanUtils.copyProperties(entity, form);
			return form;
		}
		return null;
	}

	@Override
	public List<ContactForm> deleteContact(Integer contactId) {
		contactRepo.deleteById(contactId);
		return viewContacts();
	}

}
