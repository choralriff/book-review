package com.kylewalker.bookreview;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
  private JdbcTemplate jdbc;

  @Autowired
  public BookRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  public List<Book> findAll() {
    return jdbc.query(
      "select id, title, authorLastName, authorFirstName " +
      "from books order by title",
      new RowMapper<Book>() {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
          Book book = new Book();
          book.setId(rs.getLong(1));
          book.setTitle(rs.getString(2));
          book.setAuthorLastName(rs.getString(3));
          book.setAuthorFirstName(rs.getString(4));

          return book;
        }

      }
    );
  }

  public void save(Book book) {
    jdbc.update (
      "insert into books " + "(authorLastName, authorFirstName, title, id) " +
      "values (?, ?, ?, ?)",
      book.getAuthorLastName(), book.getAuthorFirstName(), book.getTitle(), book.getId()
    );
  }
}
