package cn.csjava.campus.question.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.question.entity.QuestionItemOptionEntity;
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
public interface QuestionItemOptionMapper extends BaseMapper<QuestionItemOptionEntity> {

    int updateByPrimaryKeyBySelective(@Param("entity") QuestionItemOptionEntity entity);

    List<QuestionItemOptionEntity> selectByCondition(QuestionItemOptionEntity entity);

    int save(QuestionItemOptionEntity optionEntity);

    List<QuestionItemOptionEntity> selectIdByItemId(@Param("itemId") Integer itemId);

    long deleteBatchById(List<Long> ids);
}
