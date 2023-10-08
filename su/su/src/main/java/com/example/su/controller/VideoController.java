package com.example.su.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.Music;
import com.example.su.entity.Video;
import com.example.su.service.VideoService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Resource
    private VideoService videoService;

    @Value("${su.path}")
    private String basePath;

    @Value("${su.video}")
    private String videoPath;

    @GetMapping("/files")
    public Result findallUserFile(){
        return Result.success("查询成功",videoService.list());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Video video) throws IOException {

        //获取文件名
        String[] split = video.getPath().split("/");
        String name = split[split.length-1];

        InputStream in = new FileInputStream(basePath+name);
        OutputStream out = new FileOutputStream(videoPath+name,true);

        System.out.println(basePath+name);
        System.out.println(videoPath+name);

        IOUtils.copy(in,out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);


        video.setCreateTime(LocalDateTime.now());
        video.setDowncounts(0);
        video.setPath("/mp4/"+name);

        return Result.success("操作成功",videoService.save(video));
    }

    @GetMapping("/type/{type}")
    public List<Video> SearchForType(@PathVariable String type){
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        return videoService.list(queryWrapper);
    }

    @GetMapping("/search/{k}")
    public List<Video> search(@PathVariable String k){
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("fileName",k);
        return videoService.list(queryWrapper);
    }
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Video video = videoService.getById(id);
        if(video != null){
            return Result.success("查询成功",video);
        }else{
            return Result.error(404,"没有查询到对应的信息");
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success("删除成功",videoService.removeById(id));
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public String downloadFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        Video video = videoService.getById(id);
        String[] split = video.getPath().split("/");
        String fileName = split[split.length-1];

        video.setDowncounts(video.getDowncounts()+1);

        videoService.updateById(video);

        if (fileName != null) {
            //设置文件路径
            String realPath = videoPath;
            File file = new File(realPath,fileName);
            System.out.println(file);
            if (file.exists()) {
                response.setContentType("application/octet-stream");// 设置强制下载不打开 force-download
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);//设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("download success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
