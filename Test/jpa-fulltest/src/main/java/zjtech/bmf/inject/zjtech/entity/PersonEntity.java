package zjtech.bmf.inject.zjtech.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


/**
 * 单项的关系映射类
 */
@Entity(name = "PersonEntity_Single")
@Table(name = "person", schema = "", catalog = "smf_admin"
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

  /**
   * 单向一对多
   */
  private Set<AddressEntity> address = new HashSet<>();

  /**
   * 双向一对多
   */
  private Set<ClothesEntity> clothes = new HashSet<>();

  /**
   * 单项一对多关联
   */
  @OneToMany(
          cascade = {CascadeType.ALL},
          fetch = FetchType.EAGER,
          targetEntity = AddressEntity.class,
          orphanRemoval = true /**孤子删除，会将数据库中存在但Set集合中不存在的行删除掉，缺点是额外会多一条查询语句**/
  )
  @JoinColumn(name = "person_id", nullable = false)
  public Set<AddressEntity> getAddress() {
    return address;
  }

  public void setAddress(Set<AddressEntity> address) {
    this.address = address;
 /*   this.address.clear();
    this.address.addAll(address);*/
  }

  @OneToMany(mappedBy = "person",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE},
   orphanRemoval = true)
  public Set<ClothesEntity> getClothes() {
    return clothes;
  }

  public void setClothes(Set<ClothesEntity> clothes) {
    this.clothes = clothes;
  }

  public void addAddress(AddressEntity addressEntity) {
    address.add(addressEntity);

    /**这个很重要，因为address表中的person_id不能为null,
     所以在级联保存时，需要显示设置Person对象。**/
//    addressEntity.setPerson(this);

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
}
