package com.example.su.controller;

import com.example.su.common.Result;
import com.example.su.entity.Game;
import com.example.su.service.GameService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/game")
public class GameController {
    @Resource
    private GameService gameService;

    @Value("${su.path}")
    private String basePath;

    @Value("${su.game}")
    private String gamePath;

    @Value("${su.all}")
    private String allPath;

    @GetMapping("/files")
    public Result findallUserFile(){
        return Result.success("查询成功",gameService.list());
    }

    @PostMapping("/save")
    public Result save(@PathParam("file") MultipartFile file,
                       @PathParam("fileName") String fileName,
                       @PathParam("size") String size,
                       @PathParam("path") String path,
                       @PathParam("type") String type) throws IOException {

        String FileName = System.currentTimeMillis()+file.getOriginalFilename();

        String imgPath = "/aimg/"+FileName;

        File dir = new File(allPath);

        //判断当前目录是否存在
        if(!dir.exists()){
            dir.mkdirs();
        }

        try{
            //将临时文件转存到指定位置
            file.transferTo(new File(allPath+FileName));

        }catch (IOException e){
            e.printStackTrace();
        }

        //获取文件名
        String[] split = path.split("/");
        String name = split[split.length-1];

        InputStream in = new FileInputStream(basePath+name);
        OutputStream out = new FileOutputStream(gamePath+name,true);

        System.out.println(basePath+name);
        System.out.println(gamePath+name);

        IOUtils.copy(in,out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);

        Game game = new Game();

        game.setPic(imgPath);
        game.setFileName(fileName);
        game.setSize(size);
        game.setType(type);
        game.setPath("/game/"+name);
        game.setCreateTime(LocalDateTime.now());
        game.setDowncounts(0);
        System.out.println(game);
        gameService.save(game);

        return Result.success("分类成功",game);
    }


}
