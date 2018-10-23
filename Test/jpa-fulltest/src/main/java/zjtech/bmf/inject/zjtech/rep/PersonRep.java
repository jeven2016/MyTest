package zjtech.bmf.inject.zjtech.rep;

import zjtech.bmf.inject.zjtech.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRep extends JpaRepository<PersonEntity,
        Integer> {

  /*@Modifying
  @Query("delete from AddressEntity_Single address where address.person.id = ?1")
  public void deleteAssociatedAddresses(int personId);*/
}
