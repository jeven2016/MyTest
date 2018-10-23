package zjtech.bmf.service;

import zjtech.bmf.inject.zjtech.entity.PersonEntity;
import zjtech.bmf.inject.zjtech.rep.PersonRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseService<PersonEntity, Integer> {
  @Autowired
  PersonRep rep;

  @Override
  protected Repository getRepository() {
    return rep;
  }
}
