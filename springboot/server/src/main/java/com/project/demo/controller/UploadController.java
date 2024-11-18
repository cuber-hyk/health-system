package com.project.demo.controller;

import com.project.demo.entity.Upload;
import com.project.demo.service.UploadService;

import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

/**
 * (Upload)表控制层
 *
 */
@RestController
@RequestMapping("upload")
public class UploadController extends BaseController<Upload, UploadService> {
    /**
     * 服务对象
     */
    @Autowired
    public UploadController(UploadService service) {
        setService(service);
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            File file = new File(System.getProperty("user.dir"), "/static/upload/" + imageName);
            Resource resource = new UrlResource(file.toURI());
            if (resource.exists() && resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();

                // 根据文件类型设置响应头，指定内容类型为图片类型
                String contentType = Files.probeContentType(file.toPath());
                headers.add(HttpHeaders.CONTENT_TYPE, contentType);

                // 返回图片资源和 OK 响应状态
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}


