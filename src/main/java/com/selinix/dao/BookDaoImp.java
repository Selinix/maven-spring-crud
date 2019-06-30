package com.selinix.dao;

import com.selinix.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoImp implements BookDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private SqlParameterSource getSqlParameterByModel (Book book) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        if (book != null) {
            paramSource.addValue("id", book.getId());
            paramSource.addValue("name", book.getName());
            paramSource.addValue("autor", book.getAutor());
            paramSource.addValue("price", book.getPrice());
        }
        return paramSource;
    }

    @Override
    public List<Book> listAllBooks() {
        String sql = "SELECT ID_BOOK, NAME_BOOK, AUTOR_BOOK, PRICE_BOOK FROM BOOKS";

        List<Book> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new BookMapper());
        return list;
    }

    @Override
    public void addBook(Book book) {
        String sql = "insert into BOOKS (NAME_BOOK, AUTOR_BOOK, PRICE_BOOK) values (:name, :autor, :price)";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(book));
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE BOOKS SET NAME_BOOK = :name, AUTOR_BOOK = :autor, PRICE_BOOK = :price WHERE ID_BOOK = :id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(book));
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM BOOKS WHERE ID_BOOK = :id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(new Book(id)));
    }

    @Override
    public Book findBookById(int id) {
        String sql = "SELECT * FROM BOOKS WHERE ID_BOOK = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(new Book(id)), new BookMapper());
    }

    private static final class BookMapper implements RowMapper<Book> {
        public Book mapRow (ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("ID_BOOK"));
            book.setName(rs.getString("NAME_BOOK"));
            book.setAutor(rs.getString("AUTOR_BOOK"));
            book.setPrice(rs.getInt("PRICE_BOOK"));

            return book;
        }
    }
}
