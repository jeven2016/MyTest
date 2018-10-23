package zjtech.bmf.service;


import zjtech.bmf.inject.zjtech.entity.ClothesEntity;
import zjtech.bmf.inject.zjtech.rep.ClothesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ClothesService extends BaseService<ClothesEntity, Integer> {
  @Autowired
  private ClothesRep clothesRep;

  @Override
  protected Repository getRepository() {
    return clothesRep;
  }

  @Transactional
  public void deleteByIds(Collection<Integer> ids) {
    clothesRep.deleteByIds(ids);
  }


  @Transactional
  public void updateName(String name, int id) throws Exception {
    clothesRep.updateName(name, id);
  }

  @Transactional
  public void updateNameThrowException(String name, int id) throws Exception {
    clothesRep.updateName(name, id);
    throw new Exception("updateNameThrowException");
  }

  @Transactional(rollbackFor = Exception.class)
  public void updateNameCatchException(String name, int id) throws Exception {
    clothesRep.updateName(name, id);
    throw new Exception("updateNameCatchException");
  }

  @Transactional
  public void updateNameThrowRuntimeException(String name, int id) throws
          RuntimeException {
    clothesRep.updateName(name, id);
    throw new RuntimeException("updateNameThrowRuntimeException");
  }

  /**
   * 代码捕捉Exception并回滚事务，当遇到Exception,回滚修改
   */
  @Transactional(rollbackFor = Exception.class)
  public void updateAndDeleteByService(int updateId,int deleteId) throws
          Exception {
    updateName("updateNow~", updateId);
    updateName("updateNow~11", updateId);
    deleteById(deleteId);
    updateNameCatchException("updateNameCatchException", updateId);
  }

  /**
   * 代码未捕捉Exception并回滚事务，当遇到Exception,不会回滚修改
   */
  @Transactional
  public void updateAndDeleteByService_notRollBack() throws Exception {
    updateName("updateNow", 19);
    updateName("updateNow2", 19);
    deleteById(29);
    updateNameThrowException("updateNameThrowException", 19);
  }
}
