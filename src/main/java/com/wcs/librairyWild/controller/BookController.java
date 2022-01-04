package com.wcs.librairyWild.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.librairyWild.entity.Book;
import com.wcs.librairyWild.repository.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	//recupère tous les books
	@GetMapping("/books")
	public List<Book> index(){
		return bookRepository.findAll();
	}
	
	//recupère un book avec son id 
	@GetMapping("/books/{id}")
	public Book show(@PathVariable long id) {
		return bookRepository.findById(id).get();
	}
	
	//recherche un book
	@PostMapping("/books/search")
	public List<Book> search(@RequestBody Map<String, String> body){
		String searchTerm = body.get("text");
		return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
	}
	
	// créer un book
	@PostMapping("/books")
	public Book create(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	//modifier un book avec son id
	@PutMapping("/books/{id}")
	public Book update(@PathVariable long id, @RequestBody Book book) {
		//getting book
		Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }
	
	//supprimer un book de la data
	@DeleteMapping("books/{id}")
	public boolean delete(@PathVariable long id) {
		bookRepository.deleteById(id);
		return true;
	}
	
	
	
}
