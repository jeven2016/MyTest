package zjtech.bmf.inject.zjtech.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clothes类与Person是双向多对一关联
 */
@Entity
@Table(name = "clothes", schema = "", catalog = "smf_admin")
public class ClothesEntity {
  private int id;
  private Integer type;
  private String name;

  private PersonEntity person;

  /**
   * 与Person是双向多对一关联
   */
  @ManyToOne(targetEntity = PersonEntity.class, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
  @JoinColumn(name = "person_id", nullable = true)
  public PersonEntity getPerson() {
    return person;
  }

  public void setPerson(PersonEntity person) {
    this.person = person;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) /*生成主键的方式*/
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "type")
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
