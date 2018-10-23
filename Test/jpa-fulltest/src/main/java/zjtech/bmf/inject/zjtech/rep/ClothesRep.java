package zjtech.bmf.inject.zjtech.rep;

import zjtech.bmf.inject.zjtech.entity.ClothesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ClothesRep extends JpaRepository<ClothesEntity, Integer> {
/**
 And --- 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)；
 Or --- 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)；
 Between --- 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)；
 LessThan --- 等价于 SQL 中的 "<"，比如 findBySalaryLessThan(int max)；
 GreaterThan --- 等价于 SQL 中的">"，比如 findBySalaryGreaterThan(int min)；
 IsNull --- 等价于 SQL 中的 "is null"，比如 findByUsernameIsNull()；
 IsNotNull --- 等价于 SQL 中的 "is not null"，比如 findByUsernameIsNotNull()；
 NotNull --- 与 IsNotNull 等价；
 Like --- 等价于 SQL 中的 "like"，比如 findByUsernameLike(String user)；
 NotLike --- 等价于 SQL 中的 "not like"，比如 findByUsernameNotLike(String user)；
 OrderBy --- 等价于 SQL 中的 "order by"，比如 findByUsernameOrderBySalaryAsc(String user)；
 Not --- 等价于 SQL 中的 "！ ="，比如 findByUsernameNot(String user)；
 In --- 等价于 SQL 中的 "in"，比如 findByUsernameIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
 NotIn --- 等价于 SQL 中的 "not in"，比如 findByUsernameNotIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；*/

  /**
   * 通过Spring  Data JPA 默认提供的操作方式，不需要显式地添加操作JPQL语句
   * In |findByAgeIn(Collection<Age> ages) |where x.age in ?1
   */
  @Modifying
  @Query("delete from ClothesEntity e where e.id in (:ids)")
  public void deleteByIds(@Param("ids") Collection<Integer> ids);


  /**
   * @Param 和 ？1 不能同时使用
   */
  @Modifying
  @Query("update ClothesEntity e set e.name=?1 where e.id = ?2")
  public void updateName(String name, int id);
}
