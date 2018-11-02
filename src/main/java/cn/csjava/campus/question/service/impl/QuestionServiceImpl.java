package cn.csjava.campus.question.service.impl;

import cn.csjava.campus.common.options.list.LinkedListOption;
import cn.csjava.campus.common.options.map.ObjectHashMap;
import cn.csjava.campus.common.util.StringUtils;
import cn.csjava.campus.question.dto.AnswerExport;
import cn.csjava.campus.question.entity.*;
import cn.csjava.campus.question.mapper.*;
import cn.csjava.campus.question.service.QuestionService;
import cn.csjava.campus.question.vo.QuestionRequest;
import cn.csjava.campus.student.entity.StudentEntity;
import cn.csjava.campus.student.mapper.StudentApplyMapper;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/9
 */
@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionTemplateMapper questionTemplateMapper;
    @Autowired
    private QuestionTypeMapper questionTypeMapper;
    @Autowired
    private QuestionItemMapper questionItemMapper;
    @Autowired
    private QuestionItemOptionMapper questionItemOptionMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private StudentApplyMapper studentApplyMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(Integer sign, ObjectHashMap vo) {
        List<QuestionRequest.Item> items = (List<QuestionRequest.Item>) vo.get("vo");
        QuestionTemplateEntity entity = QuestionTemplateEntity.builder()
                .createDate(new Date())
                .startDate(new Date())
                .status(1)
                .signature(sign)
                .describes(vo.getString("templateName"))
                .endDate(new DateTime(new Date()).plusDays(3).toDate()).build();
        questionTemplateMapper.save(entity);

        long templateId = entity.getId();
        for (QuestionRequest.Item item : items) {
            int typeCell = questionTypeMapper.selectCount(QuestionTypeEntity.builder().type(item.getQuestionType()).build());
            if (typeCell == 0) {
                return 0;
            }
            saveItem(templateId, item);
        }

        return 1;
    }

    private void saveItem(long templateId, QuestionRequest.Item item) {
        QuestionItemEntity entity = new QuestionItemEntity();
        BeanUtils.copyProperties(item, entity);
        entity.setCreateDate(new Date());
        entity.setEditorType(item.getEditorType() ? 2 : 1);
        entity.setStatus(1);
        entity.setHealth(item.getHealth());
        entity.setTemplateId(templateId);

        questionItemMapper.save(entity);
        Long id = entity.getId();
        for (ObjectHashMap map : item.getOptions()) {
            QuestionItemOptionEntity optionEntity = QuestionItemOptionEntity
                    .builder().templateId(templateId)
                    .questionItemId(id)
                    .questionIndex(map.getInt("index"))
                    .content(map.getString("content")).build();

            questionItemOptionMapper.save(optionEntity);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int edit(ObjectHashMap vo) {
        long id = vo.getLong("id");
        Integer sign = vo.getInt("sign");
        questionTemplateMapper.updateByPrimaryKeyBySelective(QuestionTemplateEntity.builder()
                .id(id).describes(vo.getString("templateName")).signature(sign).build());
        int count = questionTemplateMapper.selectCount(QuestionTemplateEntity.builder().id(id).build());
        if (count == 0) {
            log.error("模版不存在 {}", id);
            return 0;
        }
        @SuppressWarnings("unchecked")
        List<QuestionRequest.Item> items = (List<QuestionRequest.Item>) vo.get("vo");
        itemsDiff(id, items);

        for (QuestionRequest.Item item : items) {
            if (item.getItemId() != null) {
                optionDiff(item);
                //进行修改
                List<ObjectHashMap> options = item.getOptions();
                for (ObjectHashMap option : options) {
                    if (option.containsKey("optionId")) {
                        //
                        QuestionItemOptionEntity entity = QuestionItemOptionEntity.builder()
                                .content(StringUtils.isEmpty(option.getString("content")) ? null : option.getString("content"))
                                .questionIndex(option.getInt("index"))
                                .questionIndex(option.getInt("index"))
                                .id(Long.valueOf(option.getInt("optionId"))).build();
                        log.debug("修改option");

                        questionItemOptionMapper.updateByPrimaryKeyBySelective(entity);
                    } else {
                        //增加
                        log.debug("增加option");

                        questionItemOptionMapper.save(QuestionItemOptionEntity.builder()
                                .content(option.getString("content"))
                                .questionIndex(option.getInt("index"))
                                .questionItemId(id)
                                .questionItemId(Long.valueOf(item.getItemId())).build());
                    }
                }
                QuestionItemEntity entity = QuestionItemEntity.builder().id(Long.valueOf(item.getItemId()))
                        .describes(item.getDescribes())
                        .inputText(item.getInputText())
                        .health(item.getHealth())
                        .editorType(item.getEditorType() ? 2 : 1).build();
                log.debug("修改item");

                questionItemMapper.updateByPrimaryKeyBySelective(entity);

            } else {
                //题目增加
                log.debug("增加item");
                saveItem(id, item);
            }
        }


        return 1;
    }

    private void itemsDiff(long id, List<QuestionRequest.Item> items) {
        Set<Long> newIds = items.stream().filter(item -> item.getItemId() != null).map(QuestionRequest.Item::getItemId)
                .map(Long::valueOf).collect(Collectors.toSet());
        Set<Long> oldIds = questionItemMapper.selectIdByTemplateId(id).stream().map(QuestionItemEntity::getId).collect(Collectors.toSet());
        Sets.SetView<Long> difference = Sets.difference(oldIds, newIds);

        List<Long> ids = new LinkedList<>();
        ids.addAll(difference);
        if (!ids.isEmpty()) {
            questionItemMapper.deleteBatchById(ids);
        }
    }

    private int optionDiff(QuestionRequest.Item item) {
        List<QuestionItemOptionEntity> oldOptions = questionItemOptionMapper.selectIdByItemId(item.getItemId());
        Set<Long> oldIds = oldOptions.stream().filter(q -> q.getId() != null).map(QuestionItemOptionEntity::getId).collect(Collectors.toSet());
        Set<Long> newIds = item.getOptions().stream().filter(q -> q.getInt("optionId") != null).map(o -> Long.valueOf(o.getInt("optionId"))).collect(Collectors.toSet());
        Sets.SetView<Long> difference = Sets.difference(oldIds, newIds);
        List<Long> ids = new LinkedList<>();
        ids.addAll(difference);
        if (!ids.isEmpty()) {
            questionItemOptionMapper.deleteBatchById(ids);
        }
        return 1;
    }

    @Override
    public Object findById(long id) {

        List<QuestionEntity> list = questionMapper.findByTemplateId(id);


        return groupby(list);
    }

    @Override
    public Object findByStudentTemplateId(long templateId, long saId) {
        List<QuestionEntity> list = questionMapper.findByStudentTemplateId(templateId, saId);
        return groupby(list);
    }

    private Object groupby(List<QuestionEntity> list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        QuestionEntity questionEntity = Iterables.getLast(list);
        ObjectHashMap objectHashMap = (ObjectHashMap) new ObjectHashMap("templateId", questionEntity.getTemplateId())
                .append("start", questionEntity.getStartDate())
                .append("end", questionEntity.getEndDate())
                .append("status", questionEntity.getStatus());
        List<ObjectHashMap> items = new LinkedList<>();
        // 根据问卷题目id 分组
        list.stream().sorted(Comparator.comparing(QuestionEntity::getItemIndex))
                .filter(q -> q.getItemId() != null)
                .collect(Collectors.groupingBy(QuestionEntity::getItemId))
                .forEach((itemId, questions) -> {
                    List<ObjectHashMap> options = new LinkedList<>();
                    // item  根据问题选项id分组
                    questions.stream().collect(Collectors.groupingBy(QuestionEntity::getOptionId))
                            .forEach((optionId, optionEnters) -> {
                                // option
                                QuestionEntity last = Iterables.getLast(optionEnters);
                                //加入 选项信息
                                options.add((ObjectHashMap) new ObjectHashMap()
                                        .append("optionId", last.getOptionId())
                                        .append("index", last.getOptionIndex())
                                        .append("content", last.getAnswer() == null ? last.getOptionContent() : last.getAnswer()));
                            });
                    // 加入item 信息
                    QuestionEntity last = Iterables.getLast(questions);
                    items.add((ObjectHashMap) new ObjectHashMap()
                            .append("itemId", last.getItemId())
                            .append("options", options)
                            .append("itemTitle", last.getItemDes())
                            .append("editorType", last.getEditorType())
                            .append("inputText", last.getInputText())
                            .append("health", last.getHealth())
                            .append("questionType", last.getQuestionType())
                            .append("itemIndex", last.getItemIndex()));
                });

        items.sort(Comparator.comparingInt(o -> o.getInt("itemIndex")));
        objectHashMap.append("items", items)
                .append("title", questionEntity.getTemplateDes())
                .append("sign", questionEntity.getSignature());
        return objectHashMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int commit(long saId, String userId, String url, List<ObjectHashMap> vo) {
//        int studentCount = questionAnswerMapper.selectCount(QuestionAnswerEntity.builder().saId(saId).build());


        Integer templateId = Iterables.getLast(vo).getInt("templateId");
        int count = questionAnswerMapper.selectCount(QuestionAnswerEntity
                .builder().userId(userId)
                .saId(saId)
                .templateId(Long.valueOf(templateId)).build());

        if (count > 0) {
            return 0;
        }

        for (ObjectHashMap objectHashMap : vo) {
            QuestionAnswerEntity entity = QuestionAnswerEntity.builder()
                    .commitTime(new Date())
                    .content(objectHashMap.getString("content"))
                    .questionId(Long.valueOf(objectHashMap.getInt("itemId")))
                    .questionOptionId(Long.valueOf(objectHashMap.getInt("optionId")))
                    .templateId(Long.valueOf(objectHashMap.getInt("templateId")))
                    .saId(saId)
                    .userId(userId).build();
            questionAnswerMapper.insert(entity);
        }
        studentApplyMapper.updateStudentTemplate(saId, Long.valueOf(Iterables.getLast(vo).getInt("templateId")), new Date(), 2, url == null ? "" : url);
        return 1;
    }

    @Override
    public List<AnswerExport> export(StudentEntity entity, HttpServletResponse response) throws IOException {
        List<AnswerExport> list = questionMapper.findAnswerByStudent(1);

        List<List<String>> data = new LinkedList<>();

        List<String> titles = Lists.newArrayList("学生姓名", "身份证号码", "性别", "报名状态", "家庭住址", "母亲姓名", "母亲联系方式", "父亲姓名", "父亲联系方式");


        Map<Long, List<AnswerExport>> itemTitles = list.stream().filter(s -> s.getStudentName() != null).collect(Collectors.groupingBy(AnswerExport::getItemId));


        Map<Long, Integer> indexInvokes = new LinkedHashMap<>();
        AtomicInteger titleFirstIndex = new AtomicInteger(titles.size() - 1);
        itemTitles.forEach((aLong, answerExports) -> {
            AnswerExport export = answerExports.get(0);
            int newIndex = titleFirstIndex.addAndGet(1);
            indexInvokes.put(aLong, newIndex);
            titles.add(export.getDescribes());
        });

        Map<String, List<AnswerExport>> students = list.stream().filter(s -> s.getCardNo() != null).collect(Collectors.groupingBy(AnswerExport::getCardNo));
        students.forEach((s, answerExports) -> {
            AnswerExport a = answerExports.get(0);
            List<String> content = new LinkedListOption(indexInvokes.size(), a.getStudentName(), a.getCardNo(), a.getSex(), a.getStatus() == 1 ? "已审核" : "报名成功", StringUtils.emptyToStr(a.getFamilyAddress())
                    , a.getMotherName(), a.getMotherPhone(), a.getFatherName(), a.getFatherPhone(), a.getContent()).build();
            for (AnswerExport answerExport : answerExports) {
                content.add(indexInvokes.get(answerExport.getItemId()), answerExport.getContent());
            }
            data.add(content);
        });
        data.add(0, titles);
        //循环转换成 excel 数据
        ExcelWriter writer = new ExcelWriter(response.getOutputStream(), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("学生档案");
        writer.write0(data, sheet);
        writer.finish();


        return null;
    }
}
