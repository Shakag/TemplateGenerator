import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.OracleQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.springframework.boot.jdbc.DataSourceBuilder;


public class TemplateGenerator {
    public static void main(String[] args) {
        // 1.数据库配置
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig
                // 驱动连接的URL、数据库连接用户名、数据库连接密码
                .Builder("jdbc:oracle:thin:@10.0.1.10:1522:orcl", "ogg", "ogg")
                // 关键字处理 ,这里选取了mysql5.7文档中的关键字和保留字（含移除） 说明：官方文档中没有关于sqlserver，oracle数据库的配置
                //.keyWordsHandler(new MySqlKeyWordsHandler())
                // 数据库信息查询类,默认由 dbType 类型决定选择对应数据库内置实现：mysql:MySqlQuery(),sqlserver :SqlServerQuery(),Oracle:OracleQuery()
                .dbQuery(new OracleQuery())
                // 类型转换,数据库=》JAVA类型  mysql: MySqlTypeConvert() sqlserver:SqlServerTypeConvert() oracle:OracleTypeConvert()
                .typeConvert(new OracleTypeConvert());

        FastAutoGenerator.create(dataSourceConfigBuilder)
                .globalConfig(builder -> {
                    builder.author("Shakag") // 设置作者
                            //.fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 生成后不自动打开文件目录
                            .dateType(DateType.ONLY_DATE)  //设置日期类型
                            // 指定输出目录
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");
//                            .outputDir("C:\\Code\\research\\TemplateGenerator\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.shakag"); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("T_YJ_DWYJ_QXJL") // 设置需要生成的表名

                            //实体配置
                            .entityBuilder()

                            //controller 配置
                            .controllerBuilder()
                            .enableRestStyle() //开启生成@RestController 控制器

                            //service 配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp")

                            //mapper 配置
                            .mapperBuilder()
                            .enableMapperAnnotation();
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.ENTITY)
                            .entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
//                            .mapperXml("/templates/mapper.xml")
                            .controller("/templates/controller.java")
                            .build();
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();



    }
}
