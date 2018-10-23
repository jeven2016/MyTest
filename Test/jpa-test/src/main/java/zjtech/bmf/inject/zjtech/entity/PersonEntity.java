package zjtech.bmf.inject.zjtech.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(name = "person", schema = "smf_admin", catalog = ""
        /*uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "name" //名称队列不能重复
                )
        }*/
)
public class PersonEntity {
  private int id;
  private String name;
  private String desct;

  private ArrayList<AddressEntity> address = new ArrayList<>();

  @OneToMany(mappedBy = "person",
          cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
          fetch = FetchType.LAZY,
          targetEntity = AddressEntity.class
          //orphanRemoval = true
  )
  public Collection<AddressEntity> getAddress() {
    return address;
  }

  public void setAddress(Collection<AddressEntity> address) {
    this.address.clear();
    this.address.addAll(address);
  }

  public void addAddress(AddressEntity addressEntity) {
    address.add(addressEntity);

    /**这个很重要，因为address表中的person_id不能为null,
     所以在级联保存时，需要显示设置Person对象。**/
    addressEntity.setPerson(this);

  }

  /**
   * 该主键不允许手动插入、更新
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) /*生成主键的方式*/
  @Column(name = "id", insertable = false, updatable = false)
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
  @Column(name = "desct")
  public String getDesct() {
    return desct;
  }

  public void setDesct(String desct) {
    this.desct = desct;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PersonEntity that = (PersonEntity) o;

    if (id != that.id) return false;
    if (desct != null ? !desct.equals(that.desct) : that.desct != null)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (desct != null ? desct.hashCode() : 0);
    return result;
  }
}
