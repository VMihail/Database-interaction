package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Problems")
public class Problem implements org.example.entities.Entity {
  @Id
  @GeneratedValue (strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "deadline")
  private Date deadline;
  @Column(name = "description")
  private String description;
  @Column(name = "type")
  private String type;
  @Column(name = "employeeId")
  private Long employeeId;

  public Problem() {

  }

  public Problem(Long id, String name, Date deadline, String description, String type, Long employeeId) {
    this.id = id;
    this.name = name;
    this.deadline = deadline;
    this.description = description;
    this.type = type;
    this.employeeId = employeeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Problem problem = (Problem) o;
    return Objects.equals(id, problem.id) && Objects.equals(name, problem.name) && Objects.equals(deadline, problem.deadline) && Objects.equals(description, problem.description) && Objects.equals(type, problem.type) && Objects.equals(employeeId, problem.employeeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, deadline, description, type, employeeId);
  }

  @Override
  public String toString() {
    return "Problem{" +
     "id=" + id +
     ", name='" + name + '\'' +
     ", deadline=" + deadline +
     ", description='" + description + '\'' +
     ", type='" + type + '\'' +
     ", employeeId=" + employeeId +
     '}';
  }
}
