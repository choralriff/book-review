package com.kylewalker.bookreview;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BookReviewController {
  private BookRepository bookRepo;

  @Autowired
  public BookReviewController(BookRepository bookRepo) {
    this.bookRepo = bookRepo;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String home(Map<String, Object> model) {
    List<Book> books = bookRepo.findAll();
    model.put("books", books);
    return "home";
  }

  @RequestMapping(method=RequestMethod.POST)
  public String submit(Book book) {
    bookRepo.save(book);
    return "redirect:/";
  }
}
