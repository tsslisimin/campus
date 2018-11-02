package cn.csjava.campus.question.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.question.entity.QuestionTemplateEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Component
public interface QuestionTemplateMapper extends BaseMapper<QuestionTemplateEntity> {


    int updateByPrimaryKeyBySelective(@Param("entity") QuestionTemplateEntity entity);

    void save(QuestionTemplateEntity entity);

}
