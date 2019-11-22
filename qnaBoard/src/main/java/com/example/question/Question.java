package com.example.question;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.example.answer.Answer;
import com.example.domain.AbstractEntity;
import com.example.domain.CustomerList;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Formula;

/*
    thymeleaf를 활용하여 게시글에 내용과 제목 작성자를 읽어오기 위해서는 필드들의 접근 지정자들을 private이 아닌
    public으로 해야만 읽어올수 있다.
*/
/**
 * Question
 */
@Entity
public class Question extends AbstractEntity {
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    @JsonProperty
    public CustomerList writer;

    @OneToMany(mappedBy = "question")
    @OrderBy("id desc")
    @JsonProperty
    public List<Answer> answers;

    @JsonProperty
    public String title;

    @Lob
    @JsonProperty
    public String contents;

    @JsonProperty
    private Integer countOfAnswer;

    @JsonProperty
    @Formula("(select count(*) from Question)")
    private Integer countOfQuestion;

    // @Column(name = "createtime")
    // @CreationTimestamp
    // @JsonProperty
    // private LocalDateTime createdTimedAt;

    // @UpdateTimestamp
    // @JsonProperty
    // private LocalDateTime updateTimeAt;

    public Question() {

    }

    public Question(CustomerList writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public boolean isSameWriter(CustomerList loginUser) {
        return this.writer.equals(loginUser);
    }

    public void addAnswer() {
        this.countOfAnswer += 1;
    }

    public void deleteAnswer() {
        this.countOfAnswer -= 1;
    }

    public Integer getCountOfAnswer() {
        return countOfAnswer;
    }

    public void setCountOfAnswer(Integer countOfAnswer) {
        this.countOfAnswer = countOfAnswer;
    }

    @Override
    public String toString() {
        return "Question [" + super.toString() + "answers=" + answers + ", contents=" + contents + ", countOfAnswer="
                + countOfAnswer + ", title=" + title + ", writer=" + writer + ", countOfQuestion=" + countOfQuestion
                + "]";
    }

    public Integer getCountOfQuestion() {
        return countOfQuestion;
    }

    public void setCountOfQuestion(Integer countOfQuestion) {
        this.countOfQuestion = countOfQuestion;
    }
}