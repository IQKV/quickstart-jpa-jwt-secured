package org.ujar.bs.rst.jwtauth.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Simple domain object that represents application user's role - ADMIN, USER, etc.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private List<User> users;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(name = "created")
  private Date created;

  @LastModifiedDate
  @Column(name = "updated")
  private Date updated;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status;

  @Override
  public String toString() {
    return "Role{" +
           "name='" + name + '\'' +
           ", users=" + users +
           ", id=" + id +
           ", created=" + created +
           ", updated=" + updated +
           ", status=" + status +
           '}';
  }
}
