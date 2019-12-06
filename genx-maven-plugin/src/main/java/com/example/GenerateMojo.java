package com.example;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author sunx
 * @date 2019-12-06
 * @description
 */
@Mojo(name = "code", threadSafe = true)
public class GenerateMojo extends AbstractGenerateMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        initConfig();
        System.out.println("开始执行11111111");
        System.out.println(this.config.getTest());
        System.out.println(System.getProperty("user.dir"));
        System.out.println("执行结束");
    }
}
