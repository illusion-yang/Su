package com.example.su.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.Admin;
import com.example.su.entity.Music;
import com.example.su.entity.User;
import com.example.su.mapper.MusicMapper;
import com.example.su.service.MusicService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Resource
    private MusicService musicService;

    @Value("${su.path}")
    private String basePath;

    @Value("${su.audio}")
    private String audioPath;

    @GetMapping("/files")
    public Result findallUserFile(){
        return Result.success("查询成功",musicService.list());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Music music) throws IOException {
        //获取文件名
        String[] split = music.getPath().split("/");
        String name = split[split.length-1];

        InputStream in = new FileInputStream(basePath+name);
        OutputStream out = new FileOutputStream(audioPath+name,true);

        System.out.println(basePath+name);
        System.out.println(audioPath+name);

        IOUtils.copy(in,out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);

        music.setCreateTime(LocalDateTime.now());
        music.setDowncounts(0);
        music.setPath("/mp3/"+name);

        return Result.success("操作成功",musicService.save(music));
    }

    @GetMapping("/type/{type}")
    public List<Music> SearchForType(@PathVariable String type){
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        return musicService.list(queryWrapper);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Music music = musicService.getById(id);

        if(music != null){
            return Result.success("查询成功",music);
        }else{
            return Result.error(404,"没有查询到对应的信息");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Music music){
        if(music.getId()==null){
            return Result.success("新增成功",musicService.save(music));
        }else{
            return Result.success("编辑成功",musicService.updateById(music));
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success("删除成功",musicService.removeById(id));
    }

    @GetMapping("/search/{k}")
    public List<Music> search(@PathVariable String k){
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("fileName",k);
        return musicService.list(queryWrapper);
    }

    //文件下载相关代码
//    @RequiresPermissions("lockerapp:company:view")
    @GetMapping("/download/{id}")
    @ResponseBody
    public String downloadFile(@PathVariable String id,HttpServletRequest request, HttpServletResponse response) {
        Music music = musicService.getById(id);
        String[] split = music.getPath().split("/");
        String fileName = split[split.length-1];

        music.setDowncounts(music.getDowncounts()+1);

        musicService.updateById(music);

        if (fileName != null) {
            //设置文件路径
            String realPath = audioPath;
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
