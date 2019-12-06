package com.example;

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
            "    <groupId>com.baomidou</groupId>\n" +
            "    <artifactId>mybatisplus-maven-plugin</artifactId>\n" +
            "    <version>1.0.0</version>\n" +
            "    <configuration>\n" +
            "        <!-- 输出目录(默认java.io.tmpdir) -->\n" +
            "        <outputDir>e:\\cache</outputDir>\n" +
            "        <!-- 是否覆盖同名文件(默认false) -->\n" +
            "        <fileOverride>true</fileOverride>\n" +
            "        <!-- mapper.xml 中添加二级缓存配置(默认true) -->\n" +
            "        <enableCache>true</enableCache>\n" +
            "        <!-- 开发者名称 -->\n" +
            "        <author>Yanghu</author>\n" +
            "        <!-- 数据源配置，( **必配** ) -->\n" +
            "        <dataSource>\n" +
            "            <driverName>com.mysql.jdbc.Driver</driverName>\n" +
            "            <url>jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&amp;useSSL=false</url>\n" +
            "            <username>root</username>\n" +
            "            <password>123456</password>\n" +
            "        </dataSource>\n" +
            "        <strategy>\n" +
            "            <!-- 字段生成策略，四种类型，从名称就能看出来含义\n" +
            "            nochange(默认),\n" +
            "            underline_to_camel,(下划线转驼峰)\n" +
            "            remove_prefix,(去除第一个下划线的前部分，后面保持不变)\n" +
            "            remove_prefix_and_camel(去除第一个下划线的前部分，后面转驼峰) \n" +
            "            -->\n" +
            "            <naming>underline_to_camel</naming>\n" +
            "            <!-- ID策略 是LONG还是STRING类型(默认stringtype)-->\n" +
            "            <serviceIdType>longtype</serviceIdType>\n" +
            "            <!--Entity中的ID生成策略（默认 id_worker）-->\n" +
            "            <idGenType>uuid</idGenType>\n" +
            "            <!--自定义超类-->\n" +
            "            <!--<superServiceClass>net.hyman.base.BaseService</superServiceClass>-->\n" +
            "            <!-- 要包含的表 与exclude 二选一配置-->\n" +
            "            <!--<include>-->\n" +
            "                <!--<property>sec_user</property>-->\n" +
            "                <!--<property>table1</property>-->\n" +
            "            <!--</include>-->\n" +
            "            <!-- 要排除的表 -->\n" +
            "            <!--<exclude>-->\n" +
            "                <!--<property>schema_version</property>-->\n" +
            "            <!--</exclude>-->\n" +
            "        </strategy>\n" +
            "        <packageInfo>\n" +
            "            <!-- 父级包名称，如果不写，下面的service等就需要写全包名(默认com.baomidou) -->\n" +
            "            <parent>net.hyman</parent>\n" +
            "            <!--service包名(默认service)-->\n" +
            "            <service>service</service>\n" +
            "            <!--serviceImpl包名(默认service.impl)-->\n" +
            "            <serviceImpl>service.impl</serviceImpl>\n" +
            "            <!--entity包名(默认entity)-->\n" +
            "            <entity>entity</entity>\n" +
            "            <!--mapper包名(默认mapper)-->\n" +
            "            <mapper>mapper</mapper>\n" +
            "            <!--xml包名(默认mapper.xml)-->\n" +
            "            <xml>mapper.xml</xml>\n" +
            "        </packageInfo>\n" +
            "    </configuration>\n" +
            "    <dependencies>\n" +
            "        <dependency>\n" +
            "            <groupId>mysql</groupId>\n" +
            "            <artifactId>mysql-connector-java</artifactId>\n" +
            "            <version>${mysql.version}</version>\n" +
            "        </dependency>\n" +
            "    </dependencies>\n" +
            "</plugin>";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("帮助文档");
        System.out.println("*****************************");
        System.out.println(info);
        System.out.println("*****************************");
    }
}
