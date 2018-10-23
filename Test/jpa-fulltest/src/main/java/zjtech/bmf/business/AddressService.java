package zjtech.bmf.service;

import zjtech.bmf.inject.zjtech.entity.AddressEntity;
import zjtech.bmf.inject.zjtech.rep.AddressRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseService<AddressEntity,
        Integer> {
  @Autowired
  AddressRep rep;

  @Override
  protected Repository getRepository() {
    return rep;
  }
}
