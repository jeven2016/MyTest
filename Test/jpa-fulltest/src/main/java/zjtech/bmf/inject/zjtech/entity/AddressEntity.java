package zjtech.bmf.inject.zjtech.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address", schema = "", catalog = "smf_admin")
public class AddressEntity {
  private int id;
  private String name;
  private String dest;

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
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "dest")
  public String getDest() {
    return dest;
  }

  public void setDest(String dest) {
    this.dest = dest;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddressEntity that = (AddressEntity) o;

    if (id != that.id) return false;
    if (dest != null ? !dest.equals(that.dest) : that.dest != null)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (dest != null ? dest.hashCode() : 0);
    return result;
  }
}
