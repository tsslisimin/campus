package cn.csjava.campus.question.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.question.entity.CheckPointEntity;
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
public interface CheckPointMapper extends BaseMapper<CheckPointEntity> {

    int updateByPrimaryKeyBySelective(@Param("entity") CheckPointEntity entity);

    List<CheckPointEntity> selectByCondition(CheckPointEntity entity);
    int save(CheckPointEntity entity);

}
