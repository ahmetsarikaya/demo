package com.example.demo.bootstrap;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;

    public ApplicationBootstrap(BookRepository bookRepository,AuthorRepository authorRepository,PublisherRepository publisherRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }
    private void init(){
        Publisher publisher = new Publisher("Foo","Istanbul/Turkiye");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","1234",publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        bookRepository.save(ddd);
        authorRepository.save(eric);


        Author rod = new Author("Rod","Johnson");
        Book noEjb = new Book("JEE Development Without EJB","22345",publisher);
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        bookRepository.save(noEjb);
        authorRepository.save(rod);


    }
}
