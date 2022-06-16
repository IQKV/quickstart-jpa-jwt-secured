package org.ujar.basics.restful.jwtauth.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Simple domain object that represents application user's role - ADMIN, USER, etc.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity {

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private List<User> users;

  @Override
  public String toString() {
    return "Role{" +
           "id: " + super.getId() + ", " +
           "name: " + name + "}";
  }
}
