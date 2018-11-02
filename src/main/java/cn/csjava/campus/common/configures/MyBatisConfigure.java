package cn.csjava.campus.common.configures;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author：hcqi .
 * describe: mybatis 配置
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
@EnableTransactionManagement
@tk.mybatis.spring.annotation.MapperScan("cn.csjava.campus.*.mapper")
@MapperScan("cn.csjava.campus.*.mapper")
@Configuration
public class MyBatisConfigure {
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource source = new ComboPooledDataSource();
        source.setDriverClass(driver);
        source.setUser(username);
        source.setPassword(password);
        source.setJdbcUrl(jdbcUrl);
        return source;
    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource source) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(source);
//        return bean.getObject();
//    }

}
