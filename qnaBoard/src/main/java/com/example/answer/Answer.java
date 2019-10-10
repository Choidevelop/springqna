package com.example.answer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.example.domain.CustomerList;
import com.example.question.Question;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Answer
 */
@Entity
public class Answer {
    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @JsonProperty
    private CustomerList writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    @JsonProperty
    private Question question;
    @JsonProperty
    @Lob
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Answer other = (Answer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Answer [contents=" + contents + ", createdTimedAt=" + createdTimedAt + ", id=" + id + ", updateTimeAt="
                + updateTimeAt + ", writer=" + writer + "]";
    }

}