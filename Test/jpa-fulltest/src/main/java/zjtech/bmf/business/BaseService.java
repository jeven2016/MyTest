package zjtech.bmf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class BaseService<Entity, ID extends Serializable> {
  abstract protected Repository getRepository();

  private JpaRepository<Entity, ID> getCrudRepository() {
    return (JpaRepository<Entity, ID>) getRepository();
  }

  private PagingAndSortingRepository<Entity, ID> getQueryRepository() {
    return (PagingAndSortingRepository<Entity, ID>) getRepository();
  }

  @Transactional
  public Entity add(Entity personEntity) {
    return getCrudRepository().save(personEntity);
  }

  @Transactional
  public Collection<Entity> addAll(Collection<Entity> personEntities) {
    return getCrudRepository().save(personEntities);
  }

  @Transactional
  public Entity update(Entity personEntity) {
    return getCrudRepository().save(personEntity);
  }

  @Transactional
  public void deleteById(ID personId) {
    getCrudRepository().delete(personId);
  }

  @Transactional
  public void delete(Entity personEntity) {
    getCrudRepository().delete(personEntity);
  }

  @Transactional
  public void deleteAll() {
    getCrudRepository().deleteAll();
  }

  public Entity findOne(ID id) {
    return getCrudRepository().findOne(id);
  }

  public List<Entity> findAll() {
    return getCrudRepository().findAll();
  }

  /**
   * 获取分页数据
   *
   * @param page 第几页
   * @param size 每页显示的数据
   * @param sort 排序条件
   * @return Page对象
   */
  public Page<Entity> findByPage(int page, int size, Sort sort) {
    //默认每页显示3条数据
    PageRequest pageRequest = new PageRequest(page, size, sort);
    Page<Entity> entities = getQueryRepository().findAll(pageRequest);
    return entities;
  }

  public Page<Entity> findByPage(int page, int size) {
    //默认每页显示3条数据
    PageRequest pageRequest = new PageRequest(page, size, null);
    Page<Entity> entities = getQueryRepository().findAll(pageRequest);
    return entities;
  }

}
