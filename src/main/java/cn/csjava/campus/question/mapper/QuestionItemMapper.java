package cn.csjava.campus.question.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.question.entity.QuestionItemEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Component
public interface QuestionItemMapper extends BaseMapper<QuestionItemEntity> {

    int updateByPrimaryKeyBySelective(@Param("entity") QuestionItemEntity entity);

    List<QuestionItemEntity> selectByCondition(QuestionItemEntity entity);

    long save(QuestionItemEntity entity);

    List<QuestionItemEntity> selectIdByTemplateId(long id);

    long deleteBatchById(List<Long> ids);
}
