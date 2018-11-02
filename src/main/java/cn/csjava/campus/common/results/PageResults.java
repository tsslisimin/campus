package cn.csjava.campus.common.results;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * @author：hcqi .
 * describe: 分页结果回复
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResults extends Results {
    private Integer page;
    private int count;
    private Integer totalPage;
    private Integer totalCount;


    public static Results empty() {
        PageResults results = new PageResults();
        results.setCount(0);
        results.setPage(0);
        results.setTotalCount(0);
        results.setTotalPage(0);
        results.setCode(HttpStatus.OK.value());
        results.setMessage("无数据");
        return results;
    }


    public static Results ok(Integer page, Integer count, Integer totalCount) {
        PageResults results = new PageResults();
        results.setCount(count);
        results.setPage(page);
        results.setTotalCount(totalCount);
        results.setTotalPage((int) Math.ceil(totalCount / count));
        results.setCode(HttpStatus.OK.value());
        results.setMessage("无数据");
        return results;
    }
}
