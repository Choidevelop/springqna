package com.example.domain;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * AbstractEntity
 */
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;
    
    @JsonProperty
    @CreationTimestamp
    public LocalDateTime createdTimedAt;
    
    @JsonProperty
    @UpdateTimestamp
    public LocalDateTime updateTimeAt;

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
        AbstractEntity other = (AbstractEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractEntity [createdTimedAt=" + createdTimedAt + ", id=" + id + ", updateTimeAt=" + updateTimeAt
                + "]";
    }
}