package cn.csjava.campus.common.configures;

import cn.csjava.campus.common.util.TableUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        String questionTemplateEntity = TableUtils.genCreateTableSql("cn.csjava.campus.user.entity.UserEntity");
//        System.out.println(questionTemplateEntity);
//
//        String questionItemEntity = TableUtils.genCreateTableSql("cn.csjava.campus.user.entity.UserEntity");
//        System.out.println(questionItemEntity);
//        String questionItemOptionEntity = TableUtils.genCreateTableSql("cn.csjava.campus.question.entity.QuestionItemOptionEntity");
//        System.out.println(questionItemOptionEntity);
        String questionItemOptionEntity = TableUtils.genCreateTableSql("cn.csjava.campus.question.entity.QuestionAnswerEntity");
        System.out.println(questionItemOptionEntity);
    }
}
