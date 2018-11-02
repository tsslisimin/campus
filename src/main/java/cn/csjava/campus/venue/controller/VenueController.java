package cn.csjava.campus.venue.controller;

import cn.csjava.campus.common.ObjectId;
import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.venue.controller.vo.VuenueRequest;
import cn.csjava.campus.venue.entity.VenuesEntity;
import cn.csjava.campus.venue.services.VenueService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author：hcqi .
 * describe: 场馆控制器
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
@RestController
@RequestMapping("venue")
public class VenueController extends AbstractController {
    @Autowired
    private VenueService venueService;

    @GetMapping
    public Results buildId() {
        return ok(new ObjectId().toHexString());
    }

    /**
     * 添加场馆
     */
    @PostMapping("/addVenues")
    public Results addVenues(@Valid VuenueRequest vq, BindingResult result) {
        VenuesEntity ve = new VenuesEntity();
        if (null != vq) {
            return fail(1, "参数错误");
        }
        ve.setNumber(vq.getNumber());


        //查询当前地址是否已经有有场馆，有提示请重新选择地址
        //场馆容纳人数必传
        return ok();
    }


}
