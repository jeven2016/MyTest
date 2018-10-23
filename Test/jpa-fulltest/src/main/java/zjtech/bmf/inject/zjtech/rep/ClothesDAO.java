package zjtech.bmf.inject.zjtech.rep;

import zjtech.bmf.inject.zjtech.entity.ClothesEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;


public class ClothesDAO extends SimpleJpaRepository<ClothesEntity, Integer> {

  public ClothesDAO(Class<ClothesEntity> domainClass, EntityManager em) {
    super(domainClass, em);
  }
}
