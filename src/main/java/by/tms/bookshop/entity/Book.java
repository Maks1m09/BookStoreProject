package by.tms.bookshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bookName")
    private String bookName;
    @Column(name = "cost")
    private double cost;
    @Column(name = "amount")
    private int amount;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    public Book() {
    }


    public Book(String bookName, double cost, int amount) {
        this.bookName = bookName;
        this.cost = cost;
        this.amount = amount;
    }

    public Book(Long id, String bookName, double cost, int amount) {
        this.id = id;
        this.bookName = bookName;
        this.cost = cost;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", cost=" + cost +
                ", amount=" + amount +
                '}';
    }
}
