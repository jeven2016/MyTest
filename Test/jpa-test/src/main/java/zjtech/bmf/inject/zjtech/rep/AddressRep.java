package zjtech.bmf.inject.zjtech.rep;

import zjtech.bmf.inject.zjtech.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 15-3-30.
 */
public interface AddressRep extends JpaRepository<AddressEntity, Integer> {
}
