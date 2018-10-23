package zjtech.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Holder {
  @Autowired
  Man man;

  @Resource
  Person person;

  public boolean isManNull() {
    return man == null;
  }

  public boolean isPersonNull() {
    return person == null;
  }
}
