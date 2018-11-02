package cn.csjava.campus.question.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.question.dto.AnswerExport;
import cn.csjava.campus.question.entity.QuestionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/13
 */
@Component
public interface QuestionMapper extends BaseMapper<QuestionEntity> {

    List<QuestionEntity> findByTemplateId(long templateId);

    List<QuestionEntity> findByStudentTemplateId(@Param("templateId") long templateId, @Param("saId") long saId);

    long countByTemplateId(long templateId);

    List<AnswerExport> findAnswerByStudent(@Param("studentId") long studentId);
}
