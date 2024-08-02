package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.customer;
import com.example.demo.repository.customer_repository;

import jakarta.servlet.http.HttpSession;

@Controller
public class customer_controller {

	@Autowired 
	
	customer_repository repo;
	
	@RequestMapping("/welcome")
	
	public String welcome() {
		
		return"welcome";
	
	}
	
	@GetMapping("/")
	public String home(Model m) {
		
		List<customer> c = (List<customer>) repo.findAll();
		
		m.addAttribute("add-products",c);
		
		return "home";
		
	}
	
	@GetMapping("/getbyid/{id}")
	public String getbyid(@PathVariable(value = "id") int id,Model m) {
		
		Optional<customer> c = repo.findById(id);
		
		customer ct = c.get();
		
		m.addAttribute("products",ct);
		
		return "edit";
		
	}
	
	@PostMapping("/save-products")
	public String insert(@ModelAttribute customer cc, HttpSession session) {
		
		repo.save(cc);
		
		session.setAttribute("message", "successfully added");
		
		return "redirect:/load_form";
	
	}

	@PutMapping("/update")
	public String edit(@ModelAttribute customer c,HttpSession session) {
		
		repo.save(c);
		
		session.setAttribute("message", "successfully updated");
		
		return "redirect:/";
	
	}
	
	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable (value = "id") int id, HttpSession session) {
		
		repo.deleteById(id);
		
		session.setAttribute("message", "successfully deleted");
		 
		return "redirect:/";
	
	}

	@GetMapping("/load_form")
	public String load_form() {
		
		return "add";
	}
}