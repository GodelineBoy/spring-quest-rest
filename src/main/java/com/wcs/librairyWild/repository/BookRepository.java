package com.wcs.librairyWild.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcs.librairyWild.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	// custom query to search to blog post by title or content
    List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
}
