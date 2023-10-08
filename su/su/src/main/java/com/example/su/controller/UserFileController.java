package com.example.su.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.User;
import com.example.su.entity.UserFile;
import com.example.su.service.UserFileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserFileController {

    @Resource
    private UserFileService userFileService;

    @Value("${su.path}")
    private String basePath;

    //文件下载相关代码
    @GetMapping("/download/{id}")
    @ResponseBody
    public String downloadFile(@PathVariable String id,HttpServletRequest request, HttpServletResponse response) {
        //String fileName = "46c6de36-619e-4e2e-acb7-492b84352831.mp3";// 设置文件名，根据业务需要替换成要下载的文件名

        UserFile userfile = userFileService.getById(id);

        String[] split = userfile.getPath().split("/");
        String fileName = split[split.length-1];

        if (fileName != null) {
            //设置文件路径
            String realPath = basePath;
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


    /*//下载功能
    @GetMapping("{id}")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException{
        System.out.println(id);
        //获取文件信息
        UserFile userFile = userFileService.getById(id);

        System.out.println(userFile);

        String[] split = userFile.getPath().split("/");
        String name = split[split.length-1];

        //根据文件信息中的文件名字 和 文件存储路径获取文件输入流
        String realpath = basePath + name;
        //ResourceUtils.getURL("classpath:").getPath() + "/static" +
        //获取文件输入流
        System.out.println(realpath);
        File file = new File(realpath);
        //D:\su\su\src\main\resources\static\img\d8915c7a-de27-4947-b93e-13fb44141de0.png
        FileInputStream is = new FileInputStream(new File(realpath));
        //附件下载
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(userFile.getOldFileName(),"UTF-8"));
        //获取相应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);


        *//*response.setCharacterEncoding("utf-8");
        InputStream is = null;
        OutputStream os = null;
        try{
            //分片下载
            long fSize = file.length();
            response.setContentType("application/x-download");
            String fileName = URLEncoder.encode(name,"utf-8");
            response.addHeader("Content-Disposition","attachment;filename="+fileName);
            response.setHeader("Accept-Range","bytes");
            response.setHeader("fSize",String.valueOf(fSize));
            response.setHeader("fName",fileName);

            long pos = 0,last = fSize-1,sum = 0;
            if(null != response.getHeader("Range")){
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

                String numRange = request.getHeader("Range").replaceAll("bytes=","");

                String[] strRange = numRange.split("-");
                if(strRange.length == 2){
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());
                    if(last > fSize-1){
                        last = fSize-1;
                    }
                }else{
                    pos = Long.parseLong(numRange.replaceAll("-","").trim());
                }
            }
            long rangeLength = last - pos +1;
            String contentRange = new StringBuffer("bytes").append("-").append(last).append("/").append(fSize).toString();
            response.setHeader("Content-Range",contentRange);
            response.setHeader("Content-Length",String.valueOf(rangeLength));

            os = new BufferedOutputStream(response.getOutputStream());
            is = new BufferedInputStream(new FileInputStream(file));
            is.skip(pos);
            byte[] buffer = new byte[1024];
            int length = 0;
            while(sum < rangeLength){
                length = is.read(buffer,0,((rangeLength-sum) <= buffer.length?((int)(rangeLength-sum)) : buffer.length));
                sum = sum + length;
                os.write(buffer,0,length);
                System.out.println("下载完成");
            }
        }finally {
            if(is != null){
                is.close();
            }
            if(os != null){
                os.close();
            }
        }*//*
    }*/

    //上传功能
    @PostMapping("/upload")
    public Result upload(@PathParam("file") MultipartFile file, @PathParam("id") Integer id) throws IOException {

        System.out.println(id);
        //原始文件名
        String originalFilename = file.getOriginalFilename();

        //获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生产文件名，防止文件名称重复造成文件覆盖
        String newFileName = UUID.randomUUID().toString() + suffix;

        //文件大小
        Long size = file.getSize();

        //文件类型
        String type = file.getContentType();

        String isImg = type.startsWith("image")?"是":"否";

        File dir = new File(basePath);
        //判断当前目录是否存在
        if(!dir.exists()){
            dir.mkdirs();
        }

        try{
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath+newFileName));
        }catch (IOException e){
            e.printStackTrace();
        }

        //将文件信息保存到数据库中
        UserFile userFile = new UserFile();
        userFile.setUserId(id);
        userFile.setOldFileName(originalFilename);
        userFile.setNewFileName(newFileName);
        userFile.setExt(suffix);
        userFile.setPath("/img/"+newFileName);
        userFile.setSize(String.valueOf(size));
        userFile.setType(type);
        userFile.setIsImg(isImg);
        userFile.setDowncounts(0);
        System.out.println(userFile);
        userFileService.save(userFile);

        return Result.success("上传成功",newFileName);
    }

    //返回所有文件
    @GetMapping("/files")
    public Result findallUserFile(){
        return Result.success("查询成功",userFileService.list());
    }

    //删除文件
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        UserFile userFile = userFileService.getById(id);
        String realPath = basePath+userFile.getNewFileName();
        QueryWrapper<UserFile> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        File file = new File(realPath);
        if(file.exists())file.delete();
        userFileService.remove(wrapper);
        return Result.success("删除成功",id);
    }
}
