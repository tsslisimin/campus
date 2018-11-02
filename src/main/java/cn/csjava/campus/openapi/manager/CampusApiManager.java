package cn.csjava.campus.openapi.manager;

import cn.csjava.campus.common.RestHttpControl;
import cn.csjava.campus.common.options.map.ObjectHashMap;
import cn.csjava.campus.common.results.CampusResults;
import cn.csjava.campus.openapi.response.UserBasicResponse;
import cn.csjava.campus.openapi.response.UserDetailResponse;
import cn.csjava.campus.school.entity.SchoolYearDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author：hcqi .
 * describe: 腾讯智慧校园开放api 管理类
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/2
 */
@Slf4j
@Component
public class CampusApiManager {

    @Value("${campus.code}")
    private String code;
    @Value("${campus.url}")
    private String url;
    @Value("${campus.id}")
    private String id;
    @Value("${campus.key}")
    private String key;

    @Autowired
    private RestHttpControl restHttpControl;

    private ObjectHashMap commonParams;


    @PostConstruct
    public void init() {
        url = url.endsWith("/") ? url : url.concat("/");
        log.debug("url {} code {} id  {}  key {}  timestamp {}", url, code, id, key, System.currentTimeMillis());
        commonParams = new ObjectHashMap();
        commonParams.put("devCode", code);
        //开发者类型（1：上级单位，2：学校）
        commonParams.put("devType", 2);
        commonParams.put("objectid", id);
        //object类型（1：上级单位，2：学校）
        commonParams.put("objType", 2);

    }

    /**
     * 获取用户详情
     *
     * @param userId 用户id
     * @return 结果
     */
    public CampusResults<UserDetailResponse> getUserInfo(String userId) {
        ObjectHashMap params = wrapperParams(new ObjectHashMap("userid", userId));
        String url = wrapperUrl("open/getUserInfo").concat("&userid={userid}");
        ParameterizedTypeReference<CampusResults<UserDetailResponse>> typeRef = new ParameterizedTypeReference<CampusResults<UserDetailResponse>>() {
        };
        ResponseEntity<CampusResults<UserDetailResponse>> getUserInfo = restHttpControl.getRestTemplate().exchange(url, HttpMethod.GET, null, typeRef, params);

        log.debug("responseEntity  {} ", getUserInfo);

        return getUserInfo.getBody();
    }

    /**
     * 获取学年信息
     * @param page
     * @param size
     * @return
     */
    public CampusResults<SchoolYearDetailResponse> getSchoolYear(Integer page,Integer size) {
        ObjectHashMap params = wrapperParams(new ObjectHashMap().append("page",page).append("size",size));
        String url = wrapperUrl("schoolYear/getList").concat("&page={page}&size={size}");
        ParameterizedTypeReference<CampusResults<SchoolYearDetailResponse>> typeRef = new ParameterizedTypeReference<CampusResults<SchoolYearDetailResponse>>() {
        };
        ResponseEntity<CampusResults<SchoolYearDetailResponse>> getUserInfo = restHttpControl.getRestTemplate().exchange(url, HttpMethod.GET, null, typeRef, params);

        log.debug("responseEntity  {} ", getUserInfo);

        return getUserInfo.getBody();
    }


    /**
     * 获取管理员列表
     *
     * @return 结果
     */
    public CampusResults<List<UserBasicResponse>> adminList() {
        ObjectHashMap params = wrapperParams();
        ParameterizedTypeReference<CampusResults<List<UserBasicResponse>>> typeRef = new ParameterizedTypeReference<CampusResults<List<UserBasicResponse>>>() {
        };
        ResponseEntity<CampusResults<List<UserBasicResponse>>> getUserInfo = restHttpControl.getRestTemplate().exchange(wrapperUrl("open/getAdminList"),
                HttpMethod.GET, null, typeRef, params);

        log.debug("responseEntity  {} ", getUserInfo);
        return getUserInfo.getBody();
    }

    /**
     * 包装url
     *
     * @param base 接口名
     * @return 包装后的url
     */
    private String wrapperUrl(String base) {
        return url.concat(base).concat("?devCode={devCode}&devType={devType}&sign={sign}&objectid={objectid}&objType={objType}&timestamp={timestamp}");
    }

    /**
     * 包装参数
     *
     * @return 包装后的参数列表
     */
    private ObjectHashMap wrapperParams() {
        return wrapperParams(null);
    }

    /**
     * 包装参数
     *
     * @param param 自定义参数
     * @return 包装后的参数列表
     */
    private ObjectHashMap wrapperParams(Map<String, Object> param) {
        ObjectHashMap params = new ObjectHashMap(commonParams);
        params.put("timestamp", System.currentTimeMillis());
        if (param != null) {
            params.putAll(param);
        }
        params.put("sign", sign(params));
        return params;
    }

    /**
     * 签名
     *
     * @param params 需要签名的参数  key按照字段 排序
     * @return 签名
     */
    private String sign(Map<String, Object> params) {
        StringBuilder ret = new StringBuilder();
        List<Map.Entry<String, Object>> entries = new ArrayList<>(params.entrySet());
        entries.sort(Comparator.comparing(Map.Entry::getKey));
        entries.forEach(entry -> ret.append(entry.getKey())
                .append("=")
                .append(entry.getValue())
                .append("&"));
        String string = ret.toString().concat("key=").concat(key);
        log.debug("sign append {}", string);
        String md5 = DigestUtils.md5DigestAsHex(string.getBytes());
        log.debug("sign md5 {}", md5);
        return md5.toUpperCase();
    }

}
