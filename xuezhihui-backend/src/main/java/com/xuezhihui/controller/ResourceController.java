package com.xuezhihui.controller;

import com.xuezhihui.entity.Resource;
import com.xuezhihui.mapper.ResourceMapper;
import com.xuezhihui.util.ResultUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceMapper resourceMapper;
    private final String uploadDir = System.getProperty("user.dir") + "/uploads";
    public ResourceController(ResourceMapper resourceMapper) { this.resourceMapper = resourceMapper; }

    @PostMapping("/upload")
    public ResultUtil<?> upload(@RequestParam("file") MultipartFile file,
                                @RequestParam("title") String title,
                                @RequestParam("userId") Integer userId,
                                @RequestParam("username") String username) throws IOException {
        new File(uploadDir).mkdirs();
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + "/" + filename;
        file.transferTo(new File(filePath));
        Resource resource = new Resource();
        resource.setTitle(title);
        resource.setFilePath(filePath);
        resource.setFileName(file.getOriginalFilename());
        resource.setUserId(userId);
        resource.setUsername(username);
        resourceMapper.insert(resource);
        return ResultUtil.success(resource);
    }

    @GetMapping
    public ResultUtil<List<Resource>> list() {
        return ResultUtil.success(resourceMapper.selectAll());
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> download(@PathVariable Integer id) throws IOException {
        Resource resource = resourceMapper.selectById(id);
        if (resource == null) return ResponseEntity.notFound().build();
        byte[] bytes = Files.readAllBytes(new File(resource.getFilePath()).toPath());
        String encodedFilename = URLEncoder.encode(resource.getFileName(), "UTF-8").replace("+", "%20");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + encodedFilename + "\"; filename*=UTF-8''" + encodedFilename)
                .body(bytes);
    }

    @DeleteMapping("/{id}")
    public ResultUtil<?> delete(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
        Resource resource = resourceMapper.selectById(id);
        if (resource == null) return ResultUtil.error("资源不存在");
        int userId = Integer.parseInt(body.get("userId").toString());
        if (!resource.getUserId().equals(userId)) return ResultUtil.error("无权删除");
        resourceMapper.deleteById(id);
        return ResultUtil.success("删除成功");
    }
}
