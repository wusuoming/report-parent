<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
        package ${basepackage}.service.impl;
        import ${basepackage}.service.CommonService;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;

        import java.io.Serializable;
        import java.util.List;

public class CommonServiceImpl<T, BaseDao extends BasicCrudDao<T>> implements CommonService<T> {

    @Autowired
    protected BaseDao baseDao;


    @Override
    public int insert(T t) {
        return baseDao.insert(t);
    }

    @Override
    public int updateById(T t) {
        return baseDao.updateById(t);
    }

    @Override
    public int deleteById(Serializable pk) {
        return baseDao.deleteById(pk);
    }

    @Override
    public T getById(Serializable pk) {
        return baseDao.getById(pk);
    }

    @Override
    public <Dto> T getEntity(Dto queryDto) {
        return baseDao.getEntity(queryDto);
    }

    @Override
    public <V> V getValueById(Object pk, String fieldName) {
        return baseDao.getValueById(pk, fieldName);
    }

    @Override
    public int insertBatch(List<T> list) {
        return baseDao.insertBatch(list);
    }


    @Override
    public List<T> getEntityList() {
        return baseDao.getEntityList();
    }

    @Override
    public <Dto> List<T> getEntityList(Dto queryDto) {
        return baseDao.getEntityList(queryDto);
    }


    @Override
    public Page<T> getEntityListForPage(Pageable pageable) {
        return baseDao.getEntityListForPage(pageable);
    }

    @Override
    public <Dto> Page<T> getEntityListForPage(Dto queryDto, Pageable pageable) {
        return baseDao.getEntityListForPage(queryDto, pageable);
    }

    @Override
    public Long getEntityCount() {
        return baseDao.getEntityCount();
    }

    @Override
    public <Dto> Long getEntityCount(Dto queryDto) {
        return baseDao.getEntityCount(queryDto);
    }

}
