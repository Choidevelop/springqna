package com.example.answer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.example.domain.AbstractEntity;
import com.example.domain.CustomerList;
import com.example.question.Question;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Answer
 */
@Entity
public class Answer extends AbstractEntity{
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @JsonProperty
    private CustomerList writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    @JsonProperty
    private Question question;

    @Lob
    @JsonProperty
    private String contents;

    @Column(name = "createtime")
    @CreationTimestamp
    @JsonProperty
    private LocalDateTime createdTimedAt;

    @UpdateTimestamp
    @Column(name = "updatetime")
    @JsonProperty
    private LocalDateTime updateTimeAt;

    public Answer() {
    }

    public Answer(CustomerList writer, Question question, String contents) {
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Answer [contents=" + contents + ", createdTimedAt=" + super.toString() + ", writer=" + writer + "]";
    }

	public boolean isSameWriter(CustomerList loginUser) {
		return loginUser.equals(this.writer);
	}

}