package cn.csjava.campus.question.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.question.entity.QuestionTypeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Component
public interface QuestionTypeMapper extends BaseMapper<QuestionTypeEntity> {
    int updateByPrimaryKeyBySelective(@Param("entity") QuestionTypeEntity entity);

}
