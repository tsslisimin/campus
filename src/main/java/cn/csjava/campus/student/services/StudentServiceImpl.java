package cn.csjava.campus.student.services;

import cn.csjava.campus.common.ObjectId;
import cn.csjava.campus.common.options.map.ObjectHashMap;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.common.util.PoiUtils;
import cn.csjava.campus.common.util.StringUtils;
import cn.csjava.campus.question.entity.QuestionAnswerEntity;
import cn.csjava.campus.question.mapper.QuestionAnswerMapper;
import cn.csjava.campus.school.entity.SchoolYearVenueEntity;
import cn.csjava.campus.school.mapper.SchoolYearVenueMapper;
import cn.csjava.campus.student.controller.vo.StudentApplyRequest;
import cn.csjava.campus.student.controller.vo.StudentApplyResponse;
import cn.csjava.campus.student.controller.vo.StudentRequest;
import cn.csjava.campus.student.controller.vo.StudentResponse;
import cn.csjava.campus.student.entity.StudentApplyEntity;
import cn.csjava.campus.student.entity.StudentEntity;
import cn.csjava.campus.student.entity.StudentExcel;
import cn.csjava.campus.student.mapper.StudentApplyMapper;
import cn.csjava.campus.student.mapper.StudentMapper;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;
import cn.csjava.campus.user.entity.UserEntity;
import cn.csjava.campus.user.mapper.UserMapper;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.Id;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentApplyMapper studentApplyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private SchoolYearVenueMapper schoolYearVenueMapper;

    @Override
    @Transactional
    public Long addStudentApply(StudentApplyRequest studentApplyRequest) {
        StudentApplyEntity studentApplyEntity = new StudentApplyEntity();
        BeanUtils.copyProperties(studentApplyRequest, studentApplyEntity);
        StudentEntity studentEntity = studentMapper.selectOne(StudentEntity.builder().cardNo(studentApplyEntity.getCardNo()).build());
        if (studentEntity == null) {
            return 0L;
        }
        //已经审核
        studentApplyEntity.setStatus(1);

        StudentApplyEntity studentApply = studentApplyMapper.selectOne(StudentApplyEntity.builder().cardNo(studentApplyEntity.getCardNo()).build());
        if (null == studentApply) {
            UserEntity userEntity = userMapper.selectOne(UserEntity.builder().id(Long.parseLong(studentApplyRequest.getUserId())).build());
            if (null != userEntity) {
                studentApplyEntity.setNumberId(new ObjectId().toString());
                studentApplyEntity.setCommitDate(new Date());
                studentApplyEntity.setUserId(userEntity.getId());
                studentApplyEntity.setImage("");
                studentApplyMapper.insert(studentApplyEntity);
                Long saId = studentApplyEntity.getSaId();
                return saId;
            }
        } else {
            return -1L;
        }
        return null;
    }

    @Override
    public StudentApplyResponse getStudentDetails(String saId) {
        StudentApplyResponse sap = new StudentApplyResponse();
        StudentApplyEntity studentApplyEntity = studentApplyMapper.selectOne(StudentApplyEntity.builder().saId(Long.parseLong(saId)).build());
        if (null != studentApplyEntity) {
            BeanUtils.copyProperties(studentApplyEntity, sap);
            return sap;
        }
        return null;
    }

    @Override
    public int updateStudentTemplate(Long saId, Long templateId) {
        StudentApplyEntity studentApplyEntity = studentApplyMapper.selectOne(StudentApplyEntity.builder().saId(saId).build());
        if (null != studentApplyEntity) {
            int row = studentApplyMapper.updateStudentTemplate(saId, templateId, new Date(), 2, "");
            return row;
        }
        return 0;
    }

    @Override
    public List<UserStudentResponse> findStudentTemplate(String userId) {
        List<UserStudentResponse> list = studentApplyMapper.findStudentTemplate(userId);
        if (null != list) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<UserStudentResponse> findStudentDetails(StudentRequest entity) {
        List<SchoolYearVenueEntity> maps = schoolYearVenueMapper.findYearByStatus(SchoolYearVenueEntity.builder().status(1).build());

        List<UserStudentResponse> list = studentApplyMapper.findStudentDetails(entity)
                .stream().peek(u -> {
                    SchoolYearVenueEntity map;
                    if (!ObjectUtils.isEmpty(maps) && (map = Iterators.getLast(maps.iterator())) != null) {
                        u.setVenueName(map.getVenueName());
                        u.setFormatYear(map.getYearName());
                    }


                }).collect(Collectors.toList());
        if (null != list) {
            return list;
        }
        return new ArrayList<>();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int importStudent(String name, InputStream inputStream) throws Exception {
        List<StudentExcel> students = PoiUtils.read(PoiUtils.getWorkbook(inputStream, name), 0, 0, StudentExcel.class);
        StudentEntity entity;
        for (StudentExcel student : students) {
            if (StringUtils.isEmpty(student.getCardNo())) {
                //  有身份证号码为空的
                return -1;
            }
            entity = new StudentEntity();
            BeanUtils.copyProperties(student, entity);
            // 验证是否有该学生
            StudentEntity studentEntity = studentMapper.selectOne(StudentEntity.builder().cardNo(student.getCardNo()).build());
            if (null == studentEntity) {
                studentMapper.insert(entity);
            } else {
                studentMapper.updateByCardNo(entity);
            }
        }
        return 1;
    }

    @Override
    public void exportStudent(StudentEntity entity, HttpServletResponse response) throws Exception {
        // 根据条件查询数据
        List<StudentEntity> entities = studentMapper.queryByCondition(entity);

        List<List<String>> data = new LinkedList<>();

        buildExcelData(entities, data);
        //循环转换成 excel 数据
        ExcelWriter writer = new ExcelWriter(response.getOutputStream(), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 1);
        sheet.setSheetName("学生档案");
        writer.write0(data, sheet);
        writer.finish();
    }

    private void buildExcelData(List<StudentEntity> entities, List<List<String>> data) throws IllegalAccessException {
        data.add(Lists.newArrayList("档案号", "学生编号", "XXDM"
                , "毕业学校", "姓名", "性别", "出生日期", "曾用名", "英文名", "身份证号", "籍贯", "民族", "政治面貌", "家庭地址", "户口", "特长", "联系电话", "邮编",
                "父亲", "母亲", "父亲单位", "母亲单位", "父亲电话", "母亲电话", "公办志愿", "备注", "LQXXDM",
                "LQXXMC", "录取方式", "全国学籍号"));
        DateTime dateTime;

        for (StudentEntity studentEntity : entities) {
            Field[] fields = studentEntity.getClass().getDeclaredFields();
            List<String> fieldList = new LinkedList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Id.class)) {
                    continue;
                }
                Object o = field.get(studentEntity);
                if (o == null) {
                    fieldList.add("");
                    continue;
                }
                if (o instanceof Date) {
                    Date date = (Date) o;
                    dateTime = new DateTime(date);
                    fieldList.add(dateTime.toString("yyyy-MM-dd"));
                } else {
                    fieldList.add(String.valueOf(o.toString()));
                }
            }
            data.add(fieldList);

        }
    }


    @Override
    public List<StudentResponse> query(StudentEntity entity, Integer page, Integer limit) {
        PageHelper.offsetPage(page, limit);
        return studentMapper.select(entity).stream().map(StudentResponse::new).collect(Collectors.toList());
    }

    @Override
    public StudentResponse findStudentExcelDetails(Integer id) {
        StudentResponse studentResponse = new StudentResponse();
        StudentEntity studentEntity = studentMapper.selectOne(StudentEntity.builder().id(id.longValue()).build());
        BeanUtils.copyProperties(studentEntity, studentResponse);
        return studentResponse;
    }

    @Override
    public int updateStudentExcel(StudentEntity entity) {
        return studentMapper.updateByCardNo(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteStudent(Integer id) {
        Integer temp = 0;
        StudentEntity studentEntity = studentMapper.selectOne(StudentEntity.builder().id(id.longValue()).build());
        if (null != studentEntity && null != studentEntity.getCardNo()) {
            StudentApplyEntity studentApplyEntity = studentApplyMapper.selectOne(StudentApplyEntity.builder().cardNo(studentEntity.getCardNo()).build());
            studentMapper.delete(studentEntity);
            temp++;
            if (null != studentApplyEntity && null != studentApplyEntity.getTemplateId()) {
                List<QuestionAnswerEntity> questionAnswerEntity = questionAnswerMapper.select(QuestionAnswerEntity.builder().templateId(studentApplyEntity.getTemplateId()).saId(studentApplyEntity.getSaId()).build());
                studentApplyMapper.delete(studentApplyEntity);
                temp++;
                if (!ObjectUtils.isEmpty(questionAnswerEntity)) {
                    QuestionAnswerEntity answerEntity = questionAnswerEntity.get(0);
                    questionAnswerMapper.deleteByTemplateId(answerEntity.getTemplateId(), answerEntity.getSaId());
                    temp++;
                }
            }
        }
        if (temp > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object sumStudents() {
        List<StudentApplyEntity> list = studentApplyMapper.selectAll();

        long count = list.stream().filter(s -> s.getTemplateId() != null).count();


        return new ObjectHashMap("total", list.size()).append("ok", count).append("no", list.size() - count);
    }

    @Override
    public int createStudent(StudentEntity entity) {
        StudentEntity studentEntity = studentMapper.selectOne(StudentEntity.builder().cardNo(entity.getCardNo()).build());
        if (null == studentEntity) {
            return studentMapper.insert(entity);
        }
        return 0;
    }

    @Override
    public StudentApplyEntity findImageById(long id) {
        return studentApplyMapper.findImageById(id);
    }
}
