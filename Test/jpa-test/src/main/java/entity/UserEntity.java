package entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by root on 14-11-23.
 */
@Entity
@Table(name = "user", schema = "", catalog = "test")
public class UserEntity {
  private int id;
  private String name;
  private String description;
  private CountryEntity countryEntity;

  @OneToOne(cascade = CascadeType.ALL,optional = true)
  @JoinColumn(name = "countryid")
  public CountryEntity getCountryEntity() {
    return countryEntity;
  }

  public void setCountryEntity(CountryEntity countryEntity) {
    this.countryEntity = countryEntity;
  }


  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserEntity that = (UserEntity) o;

    if (id != that.id) return false;
    if (description != null ? !description.equals(that.description) : that.description != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
