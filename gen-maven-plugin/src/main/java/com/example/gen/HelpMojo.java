package com.example.gen;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author sunx
 * @date 2019-12-06
 * @description
 */
@Mojo(name = "help", threadSafe = true)
public class HelpMojo extends AbstractMojo {

    private static final String info = "<plugin>\n" +
            "    <groupId>com.example</groupId>\n" +
            "    <artifactId>genx-maven-plugin</artifactId>\n" +
            "    <version>1.0.0-SNAPSHOT</version>\n" +
            "    <configuration>\n" +
            "        <!-- 输出目录(默认java.io.tmpdir) -->\n" +
            "        <outDir>/Users/sunguo/tmp</outDir>\n" +
            "        <!-- 包名 -->\n" +
            "        <packageName>com.biz.xx</packageName>\n" +
            "        <!-- 模块名 -->\n" +
            "        <module>user</module>\n" +
            "        <!-- 开发者名称 -->\n" +
            "        <author>sunx</author>\n" +
            "        <!-- 数据源配置，( **必配** ) -->\n" +
            "        <dataSource>\n" +
            "            <driverName>com.mysql.jdbc.Driver</driverName>\n" +
            "            <url>jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&amp;useSSL=false</url>\n" +
            "            <username>root</username>\n" +
            "            <password>root</password>\n" +
            "            <prefix>tb_</prefix>\n" +
            "            <excludeFields>" +
            "                <property>create_time</property>" +
            "                <property>modify_time</property>" +
            "            </excludeFields>\n" +
            "        </dataSource>\n" +
            "        <strategy>\n" +
            "            <!-- 需要生成的表集合-->\n" +
            "            <includeArray>\n" +
            "                <property>user</property>\n" +
            "                <property>person</property>\n" +
            "            </includeArray>\n" +
            "            <!-- 是否包含  true  包含  false  过滤 -->\n" +
            "            <includeFlag>true</includeFlag>\n" +
            "        </strategy>\n" +
            "</plugin>";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("帮助文档");
        System.out.println("*****************************");
        System.out.println(info);
        System.out.println("*****************************");
    }
}