package zjtech.bmf.inject.zjtech.rep;

import zjtech.bmf.inject.zjtech.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PersonRep extends JpaRepository<PersonEntity, Integer> {

  @Modifying
  @Query("delete from AddressEntity address where address.person.id = ?1")
  public void deleteAssociatedAddresses(int personId);
}
