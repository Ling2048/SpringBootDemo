package com.test.demo.Config;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 图片绝对地址与虚拟地址映射
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //文件磁盘图片url 映射
    //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
    
    // registry.addResourceHandler("/image/**").addResourceLocations("file:E:\\java\\images");

    File path = null;
    try {
        path = new File(ResourceUtils.getURL("classpath:").getPath());
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    String gitPath = "";
    //打包后路径和调试时候不一样
    if (path.getParentFile().getParentFile().toString().indexOf(".jar!") > 0) { // 打包
        gitPath = path.getParentFile().getParentFile().getParent()+File.separator+"logistics"+File.separator+"resource"+File.separator;
    }
    else { // 调试
        gitPath = "file:\\" + path.getParentFile().getParentFile()+File.separator+"logistics"+File.separator+"resource"+File.separator;
    }

    registry.addResourceHandler("/uploads/**").addResourceLocations(gitPath);
    registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
  }
}