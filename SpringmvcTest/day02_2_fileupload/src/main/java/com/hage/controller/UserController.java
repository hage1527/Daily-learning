package com.hage.controller;

import com.hage.domain.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * @author: Administrator
 * @date: 2019/10/9
 * Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 文件上传
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        //使用fileupload组件完成上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //解析request对象,获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = fileUpload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //进行判断，当前fileItem是否是上传文件项
            if (fileItem.isFormField()) {

            } else {
                //获取上传文件项
                //获取上传文件的名称
                String filename = fileItem.getName();
                //把文件的名称设置唯一值，uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                //完成文件上传
                fileItem.write(new File(path, filename));
                //删除临时文件
                fileItem.delete();
            }
        }
        return "forward:/WEB-INF/pages/success.jsp";
    }

    /**
     * SpringMVC文件上传
     *
     * @param request
     * @param upload
     * @return
     */
    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springmvc文件上传");
        //使用fileupload组件完成上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取上传文件项
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        //完成文件上传
        upload.transferTo(new File(path, filename));

        return "forward:/WEB-INF/pages/success.jsp";
    }

    /**
     * 跨服务器文件上传
     * @param upload
     * @return
     */
    @RequestMapping("/fileupload3")
    public String fileupload3(MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传。。。。");
        //定义上传服务器文件路径

        String path = "http://localhost:9090/fileuploadserver_war_exploded/uploads/";

        //获取上传文件项
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;

        //创建客户端对象
        Client client = Client.create();
        //和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);

        //完成文件上传
        webResource.put(upload.getBytes());

        return "forward:/WEB-INF/pages/success.jsp";
    }


}
