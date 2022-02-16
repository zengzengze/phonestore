package com.msy.phonestore.controller;


import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author zfj
 */
@RestController
@CrossOrigin
@RequestMapping("/FileUpload")
//@CrossOrigin
public class FileUpload {
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseModel upload(MultipartFile files, HttpServletRequest request) throws IOException {
        System.out.println("1234556");
//    ①获取文件名
        String fileName = files.getOriginalFilename();
        System.out.println(fileName);
//     ②获取文件后缀及重写文件名
        String prifx = fileName.substring(fileName.lastIndexOf("."));

//        fileName=new Date().getTime()+prifx;
//      使用UUID生成唯一的文件名  7e38bbbc-2aab-4287-aedb-2491f953f68e.png
        fileName = UUID.randomUUID() + prifx;

//      ③设置文件上路路径
//        String path=request.getServletContext().getRealPath("/pic");
//        System.out.println(path);

//        File file=new File("D:/pic");

//        String path = ResourceUtils.getURL("classpath:static").getPath();
        String path = System.getProperty("user.dir") + "/src/main/resources/static/pic";
        System.out.println(path);
        File file = new File(path + "/" + fileName);
        if (!file.exists()) {
            file.mkdirs();
        }

//        File file1=new File(file,fileName);
//       ④上传文件
        files.transferTo(file);

        return ResponseModel.success(ResCode.SUCCESS, "/pic/" + fileName);

    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public ResponseModel delFile(String img, HttpServletRequest request) {
//        roomImg="http://localhost:8888/hotel/pic/43f02b4d-2a48-4378-a7f2-bbb3558cc397.jpg";
        System.out.println(img);
//        String path=request.getServletContext().getRealPath("/pic");
        String path = System.getProperty("user.dir") + "/src/main/resources/static/pic";
        path = path + "\\" + img.substring(img.lastIndexOf("/") + 1);
        System.out.println(path);

        File delFile = new File(path);
        System.out.println("删除的文件路径：" + delFile);
        if (delFile.isFile() && delFile.exists()) {
            delFile.delete();
            return ResponseModel.success(ResCode.SUCCESS);
        } else {
            return ResponseModel.success(ResCode.FAIL);
        }
    }

    @RequestMapping("/deleteFileList")
    @ResponseBody
    public ResponseModel delFileList(@RequestBody Map<String, String[]> map) {


        int row = 0;

        for (int i = 0; i < map.get("imgList").length; i++) {
            String path = System.getProperty("user.dir") + "/src/main/resources/static/pic";
            path = path + "\\" + map.get("imgList")[i].substring(map.get("imgList")[i].lastIndexOf("/") + 1);

            System.out.println(path);
            File delFile = new File(path);

            System.out.println("删除的文件路径：" + delFile);
            if (delFile.isFile() && delFile.exists()) {
                boolean delete = delFile.delete();
                if(delete){
                    row++;
                }
            }
        }

        if (row == map.get("imgList").length) {
            return ResponseModel.success(ResCode.SUCCESS);
        } else {
            return ResponseModel.fail(ResCode.FAIL);
        }
    }
}
