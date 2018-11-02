package cn.csjava.campus.venue.services;

import cn.csjava.campus.venue.mapper.VenueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueMapper venueMapper;


}
