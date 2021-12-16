import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;


public class TemplateGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC", "root", "3333")
                .globalConfig(builder -> {
                    builder.author("Shakag") // 设置作者
                            //.fileOverride() // 覆盖已生成文件
                            .dateType(DateType.ONLY_DATE)  //设置日期类型
                            .outputDir("C:\\Code\\TemplateGenerator\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.shakag"); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名

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
                            .mapperXml("/templates/mapper.xml")
                            .controller("/templates/controller.java")
                            .build();
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();



    }
}
