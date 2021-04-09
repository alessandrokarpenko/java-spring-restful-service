package com.jamp.bt.rest.api.dto.subscription;

import com.jamp.bt.rest.api.dto.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private User user;

    private LocalDate startDate;

    public Subscription() {

    }

    public Subscription(User user, LocalDate startDate) {
        this.user = user;
        this.startDate = startDate;
    }

    public Subscription(Long id, User user, LocalDate startDate) {
        this.id = id;
        this.user = user;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id.equals(that.id) && user.equals(that.user) && startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, startDate);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", startDate=" + startDate +
                '}';
    }
}
