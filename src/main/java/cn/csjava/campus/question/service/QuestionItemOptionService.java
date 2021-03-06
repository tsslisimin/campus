package cn.csjava.campus.question.service;

import cn.csjava.campus.question.entity.QuestionItemOptionEntity;

import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/9
 */
public interface QuestionItemOptionService {
    /**
     * 保存一个实体
     *
     * @param entity 实体
     * @return 结果
     */
    String save(QuestionItemOptionEntity entity);

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    int delete(Long id);

    /**
     * 根据id  查询单个数据
     *
     * @param id id
     * @return 数据
     */

    QuestionItemOptionEntity findOne(Long id);

    /**
     * 根据条件查询
     *
     * @param entity 条件
     * @param page   页码
     * @param count  个数
     * @return 结果
     */
    List<QuestionItemOptionEntity> query(QuestionItemOptionEntity entity, Integer page, Integer count);

    /**
     * 根据实体修改
     *
     * @param entity 条件
     * @return 结果
     */
    int update(QuestionItemOptionEntity entity);
}
