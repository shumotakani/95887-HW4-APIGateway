package edu.shuwang.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

public class Quote {
    private Long id;
    private String text;
    private String source;
    
    
    private Author author;
    

    public Quote() {}

    public Quote(String text, String source, Author author) {
        this.text = text;
        this.source = source;
        this.author = author;
    }

    public String toString() {
        return String.format("Quote[id=%d, text='%s', by='%s']", this.id, this.text, this.author.getName());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }
    
    
}
