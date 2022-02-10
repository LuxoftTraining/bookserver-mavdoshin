package books.model;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;
/*
    public Books() {
    }
*/
    public Books(String bookName) {
        this.bookName = bookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
