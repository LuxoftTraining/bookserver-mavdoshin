package books.controllers;


import books.model.Books;
import books.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    /**
     * Use Accept application/json or application/xml
     * to select what you want to receive.
     */
    @GetMapping("/all"/*produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}*/)
    public List<Books> findAll() {
        List<Books> books = booksRepository.findAll();

        return books;
    }

    @GetMapping("/find")
    public List<Books> findByName(@RequestParam String name) {

        if (name.isBlank())
        {
            throw new IllegalArgumentException("Name should not be null or empty");
        }

        String[] listBookNames = name.split(" ");
        List<Books> listBooks = booksRepository.findAll();
        for (String bookName: listBookNames) {
            listBooks = listBooks
                    .stream()
                    .filter(p -> p.getBookName()
                            .contains(bookName))
                    .collect(Collectors.toList());
        }

        return listBooks;
    }

    /*
    @GetMapping("/add")
    public Books addBook(@RequestParam(name="book", required=false) String book, Model model)
    {
            return booksRepository.save(new Books(book));
    }
    */

    @PostMapping("/add")
    public Books addBook(@RequestBody String book)
    {

        return booksRepository.save(new Books(book));
    }

    @PostMapping("/add1000")
    public List<Books> add1000Book()
    {
        int random1;
        int random2;
        int random3;
        List<Books> listBooks = new ArrayList<>();

        for(int i = 0; i < 1000; i++) {
            random1 = (int)(Math.random()*((99999-1000)+1))+1000;
            random2 = (int)(Math.random()*((99999-1000)+1))+1000;
            random3 = (int)(Math.random()*((99999-1000)+1))+1000;
            listBooks.add(booksRepository.save(new Books("Book"+random1+" by AuthorName"+random2+" AuthorSurname"+random3)));
        }
        return listBooks;
    }

}
