package cn.csjava.campus.common.manager;

import cn.csjava.campus.openapi.manager.CampusApiManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CampusApiManagerTest {
    @Autowired
    CampusApiManager apiManager;

    @Test
    public void getUserInfo() {
        apiManager.adminList();
        apiManager.getUserInfo("MuB9lTeaxmastEdone");
    }
}