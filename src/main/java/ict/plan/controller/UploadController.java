package ict.plan.controller;

import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.QiniuCloudUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/*
* @Name:UpdateController
* @Description:这个是负责处理文件上传的控制器
* @Date：2019
* */

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseRestController{

    /*
    * @Description:这个是文件上传的方法
    * @Date:2019
    * */
    @ResponseBody
    @PostMapping(value="/file")
    public RestResponse uploadImg(@RequestParam MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return fail("文件为空，请重新上传","400",null);
        }
        try {
            System.out.println("开始进行文件的上传");
            byte[] bytes = file.getBytes();
            String OriginalFilename = file.getOriginalFilename();
            String imageName = OriginalFilename+"——"+UUID.randomUUID().toString();
            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, imageName);
                //在这里将获取到的url存放到数据库中去
                return success(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            return fail("文件上传发生异常！","500",null);
        }
        return fail("文件上传发生异常！","500",null);
    }

    /*
    * @Description:这个上传图片的
    * */

    /*
    * @Description:这个是多文件上传
    * */
    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "/Users/itinypocket/workspace/temp/";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                System.out.println("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                return "上传第" + (i++) + "个文件失败";
            }
        }
        return "上传成功";
    }

}
