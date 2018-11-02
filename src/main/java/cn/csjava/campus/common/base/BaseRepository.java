package cn.csjava.campus.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author：hcqi .
 * des:仓库数据父类接口  所有子类接口继承该接口
 * email:hechuanqi.top@gmail.com
 * date: 2018/3/5
 */
public interface BaseRepository<DATA extends AbstractDocument, ID> {
    /**
     * 根据id单个查询
     *
     * @param id id
     * @return data
     */
    DATA findOne(ID id);

    /**
     * 单个查找 包含字段
     *
     * @param id      id
     * @param include 包含的字段
     * @return 结果
     */
    DATA findOneInclude(ID id, Set<String> include);

    /**
     * 单个查找 不包含字段
     *
     * @param id      id
     * @param exclude 不包含的字段
     * @return 结果
     */
    DATA findOneExclude(ID id, Set<String> exclude);

    /**
     * 根据id单个删除
     *
     * @param id id
     * @return data
     */
    DATA delete(ID id);

    /**
     * 查找所有数据
     *
     * @return data
     */
    List<DATA> findAll();

    /**
     * 分页查找
     *
     * @param pageable
     * @return
     */
    Page<DATA> findAll(Pageable pageable);

    /**
     * 排序查找
     *
     * @param sort
     * @return
     */
    List<DATA> findAll(Sort sort);

    /**
     * 保存一条数据
     *
     * @param data 数据
     * @return 插入成功的数据
     */
    DATA save(DATA data);

    /**
     * 插入一条数据
     *
     * @param data 数据
     * @return 插入完成的数据
     */
    DATA insert(DATA data);

    /**
     * 插入一组数据
     *
     * @param data 数据
     * @return 插入完成的数据
     */
    List<DATA> insert(Iterable<DATA> data);

    /**
     * 保存一组数据
     *
     * @param data 数据
     * @return 数据
     */
    List<DATA> save(Iterable<DATA> data);

    /**
     * 查询所有数据的个数
     *
     * @return 个数
     */
    long count();

    /**
     * 根据query 查找数据
     *
     * @param query
     * @return
     */
    DATA findByQuery(CriteriaDefinition query);

    /**
     * 根据query 查找数据
     *
     * @param query
     * @return
     */
    List<DATA> findAllByQuery(CriteriaDefinition query);

    /**
     * 根据id 查找一组数据
     *
     * @param strings
     * @return
     */
    List<DATA> findByIds(Collection<String> strings);

    boolean exists(Query query);
}
