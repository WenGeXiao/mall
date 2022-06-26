package com.wgx.mall;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MallCodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + File.separator + "mall-service" +
                File.separator + "mall-service-ums" +
                File.separator + "src" + File.separator + "main" + File.separator + "java" );
        gc.setAuthor("wgxiao");
        // 是否文件覆盖
        gc.setFileOverride(true);
        // 去掉service前缀I
        gc.setServiceName("%sService");
        // 不打开目录
        gc.setOpen(false);
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        // gc.setSwagger2(false); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mall?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&zeroDateTimeBehavior=CONVERT_TO_NULL");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wgxiao.mall");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名
        strategy.setInclude("ums_member");
        // 表格名下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 表格字段下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok开启
        strategy.setEntityLombokModel(true);
        // 自动填充
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        // 新建和更新这个字段的值都要自动填充
        TableFill modify = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(gmtCreate);
        tableFillList.add(modify);
        strategy.setTableFillList(tableFillList);

        // 乐观锁
        strategy.setVersionFieldName("version");
        // restController
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();

    }

}
