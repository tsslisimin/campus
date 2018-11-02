package cn.csjava.campus.question.service;

import cn.csjava.campus.common.options.map.ObjectHashMap;
import cn.csjava.campus.question.dto.AnswerExport;
import cn.csjava.campus.student.entity.StudentEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/9
 */
public interface QuestionService {


    int save(Integer sign, ObjectHashMap vo);

    Object findById(long id);

    Object findByStudentTemplateId(long templateId, long saId);


    int commit(long saId, String userId, String url, List<ObjectHashMap> vo);

    int edit(ObjectHashMap map);


    List<AnswerExport>  export(StudentEntity entity, HttpServletResponse response) throws IOException;
}
