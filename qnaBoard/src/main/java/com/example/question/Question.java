package com.example.question;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.domain.CustomerList;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Question
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private CustomerList writer;

    private String title;
    
    private String contents;

    @Column(name="createtime")
    @CreationTimestamp
    private LocalDateTime createdTimedAt;
    
    @UpdateTimestamp
    private LocalDateTime updateTimeAt;

    public Question(){
        
    }
    
    public Question(CustomerList writer, String title, String contents){
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
    

	public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
	}


    
}