package cn.csjava.campus.question.service.impl;

import cn.csjava.campus.question.entity.CheckPointEntity;
import cn.csjava.campus.question.mapper.CheckPointMapper;
import cn.csjava.campus.question.service.CheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Service
public class CheckPointServiceImpl implements CheckPointService {
    @Autowired
    private CheckPointMapper checkPointMapper;


    @Override
    public int save(CheckPointEntity entity) {
        entity.setCreateTime(new Date());
        return checkPointMapper.save(entity);

    }

    @Override
    public int delete(Long id) {
        return checkPointMapper.delete(CheckPointEntity.builder().id(id).build());
    }
    
    @Override
    public CheckPointEntity findOne(Long id) {
        return checkPointMapper.selectOne(CheckPointEntity.builder().id(id).build());
    }

    @Override
    public List<CheckPointEntity> query(CheckPointEntity entity, Integer page, Integer count) {
        return checkPointMapper.selectByCondition(entity);
    }

    @Override
    public int update(CheckPointEntity entity) {
        return checkPointMapper.updateByPrimaryKeyBySelective(entity);
    }
}
