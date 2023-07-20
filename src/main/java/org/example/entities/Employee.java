package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee implements org.example.entities.Entity {
  @Id
  @GeneratedValue (strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "dob")
  private Date DateOfBirthday;

  public Employee(String name, Date dateOfBirthday) {
    this.name = name;
    DateOfBirthday = dateOfBirthday;
  }

  public Employee() {

  }

  public Employee(Long id, String name, Date dateOfBirthday) {
    this.id = id;
    this.name = name;
    DateOfBirthday = dateOfBirthday;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateOfBirthday() {
    return DateOfBirthday;
  }

  public void setDateOfBirthday(Date dateOfBirthday) {
    DateOfBirthday = dateOfBirthday;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(DateOfBirthday, employee.DateOfBirthday);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, DateOfBirthday);
  }

  @Override
  public String toString() {
    return "Employee{" +
     "id=" + id +
     ", name='" + name + '\'' +
     ", DateOfBirthday=" + DateOfBirthday +
     '}';
  }
}