package cn.csjava.campus.common.base;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @author：hcqi .
 * des:仓库数据父类实现类  所有子类继承该类
 * email:hechuanqi.top@gmail.com
 * date: 2018/1/25
 */
public abstract class AbstractRepositoryImpl<DATA extends AbstractDocument, ID extends Serializable> implements BaseRepository<DATA, ID> {
    @SuppressWarnings("unchecked")
    protected Class<DATA> entityClass = (Class<DATA>) ((ParameterizedType)
            getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public DATA findOne(ID id) {
        Query q = Query.query(Criteria.where("_id").is(id));
        return findOne(q);
    }

    @Override
    public DATA findOneExclude(ID id, Set<String> exclude) {
        Query q = Query.query(Criteria.where("_id").is(id));
        exclude.forEach(s -> q.fields().exclude(s));
        return findOne(q);
    }

    @Override
    public DATA findOneInclude(ID id, Set<String> include) {
        Query q = Query.query(Criteria.where("_id").is(id));
        include.forEach(s -> q.fields().include(s));
        return findOne(q);
    }

    @Override
    public DATA delete(ID id) {

        Query q = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findAndRemove(q, entityClass);
    }

    @Override
    public List<DATA> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public Page<DATA> findAll(Pageable pageable) {
        Long count = count();
        List<DATA> list = findAll(new Query().with(pageable));
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public List<DATA> findAll(Sort sort) {
        return findAll(new Query().with(sort));
    }

    @Override
    public DATA save(DATA data) {
        mongoTemplate.save(data);
        return data;
    }

    @Override
    public DATA insert(DATA data) {
        mongoTemplate.insert(data);
        return data;
    }

    @Override
    public List<DATA> insert(Iterable<DATA> data) {
        mongoTemplate.insert(data);
        return convertIterableToList(data);
    }

    @Override
    public List<DATA> save(Iterable<DATA> data) {
        mongoTemplate.save(data);
        return convertIterableToList(data);
    }

    @Override
    public List<DATA> findByIds(Collection<String> strings) {
        Query query = Query.query(Criteria.where("_id").in(strings));
        return find(query);
    }

    @Override
    public long count() {
        return count(Query.query(new Criteria()));
    }

    @Override
    public DATA findByQuery(CriteriaDefinition query) {
        return findOne(Query.query(query));
    }

    @Override
    public List<DATA> findAllByQuery(CriteriaDefinition query) {
        return find(Query.query(query));
    }


    /**
     * 将iterable 转换成list
     *
     * @param entities
     * @param <T>
     * @return
     */
    protected <T> List<T> convertIterableToList(Iterable<T> entities) {
        if (entities instanceof List) {
            return (List<T>) entities;
        }

        int capacity = tryDetermineRealSizeOrReturn(entities, 10);

        if (capacity == 0 || entities == null) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<T>(capacity);
        for (T entity : entities) {
            list.add(entity);
        }

        return list;
    }

    /**
     * 获取初始化长度
     *
     * @param iterable
     * @param defaultSize
     * @return
     */
    private int tryDetermineRealSizeOrReturn(Iterable<?> iterable, int defaultSize) {
        return iterable == null ? 0 : (iterable instanceof Collection) ? ((Collection<?>) iterable).size() : defaultSize;
    }

    /**
     * 获取所有数据
     *
     * @param query
     * @return
     */
    private List<DATA> findAll(Query query) {
        if (query == null) {
            return Collections.emptyList();
        }
        return mongoTemplate.find(query, entityClass);
    }

    protected long count(Query query) {
        return mongoTemplate.count(query, entityClass);
    }

    protected List<DATA> find(Query query) {
        return mongoTemplate.find(query, entityClass);
    }

    protected DATA findOne(Query query) {
        return mongoTemplate.findOne(query, entityClass);
    }

    protected WriteResult updateFirst(Query query, Update update) {
        return mongoTemplate.updateFirst(query, update, entityClass);
    }

    protected WriteResult upsert(Query query, Update update) {
        return mongoTemplate.upsert(query, update, entityClass);
    }

    protected WriteResult updateMulti(Query query, Update update) {
        return mongoTemplate.updateMulti(query, update, entityClass);
    }

    protected DATA findAndModify(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, entityClass);
    }

    protected DATA findAndModify(Query query, Update update, FindAndModifyOptions options) {
        return mongoTemplate.findAndModify(query, update, options, entityClass);
    }

    protected WriteResult remove(Query query) {
        return mongoTemplate.remove(query, entityClass);
    }

    protected <T> AggregationResults<T> aggregation(Aggregation aggregation, Class<?> in, Class<T> out) {
        return mongoTemplate.aggregate(aggregation, in, out);
    }

    protected <T> AggregationResults<T> aggregation(Aggregation aggregation, Class<T> out) {
        return mongoTemplate.aggregate(aggregation, getCollectionName(), out);
    }

    protected DBCollection getCollection() {
        return mongoTemplate.getCollection(getCollectionName());

    }

    protected <T> GroupByResults<T> group(Criteria criteria, GroupBy operation, Class<T> entryClass) {
        return mongoTemplate.group(criteria, getCollectionName(), operation, entryClass);
    }

    protected DBCollection getCollection(String coll) {
        return mongoTemplate.getCollection(coll);
    }

    @Override
    public boolean exists(Query query) {
        return mongoTemplate.exists(query, entityClass);
    }

    protected String getCollectionName() {
        return entityClass.getAnnotation(Document.class).collection();
    }
}
