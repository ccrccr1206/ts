package com.project.controller;

import com.github.pagehelper.PageInfo;
import com.project.common.vo.ResultVO;
import com.project.entity.PageVO;
import com.project.entity.User;
import com.project.entity.Users;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {
    @Autowired
    UserService userService;
@PostMapping("/login")
    public ResultVO login (@NotEmpty(message = "用户名不能为空")String name,
                           @NotEmpty(message = "密码不能为空")String password){
    User user = userService.getUser(name);
    if(ObjectUtils.isEmpty(user)){
        log.warn("用户名错误");
        return ResultVO.error("用户名或密码错误");
    }
    /*验证密码*/
    if(!StringUtils.pathEquals(password,user.getPassword())){
        log.warn("认证信息错误");
        return ResultVO.error("用户名或密码错误");
    }

    return ResultVO.success("登录成功");
    }
    @RequestMapping("/query")
    public List<Users> query(String empName){
    List<Users> users =   userService.query(empName);
    return users;
    }
    @RequestMapping("/querySomeStu")
    public PageVO querySomeStu(String empName, int pageNumber, int pageSize){

        PageVO<Users> page =new PageVO<Users>();
        //1.pageNumber
        page.setPageNum(pageNumber);
        //2.pageSize
        page.setPageSize(pageSize);
        List<Users> rows = userService.querySome(empName,pageNumber,pageSize);
        //rows====>Page extends ArrayList
        PageInfo<Users> info = new PageInfo<>(rows);
        //3.total
        page.setTotal(Integer.parseInt(info.getTotal()+""));
        //4.rows;
        page.setRows(info.getList());
        return page;
    }
    @RequestMapping(value = "/add")
    public ResultVO add(@RequestBody Users users) {
         userService.add(users);
        return ResultVO.success("添加成功");
    }
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable int id){
        userService.delete(id);
        return ResultVO.success("删除成功");
    }
    @PutMapping("/update")
    public ResultVO update (Users users){
        userService.update(users);
        return ResultVO.success("更新成功");
    }
    @RequestMapping("/import")
    public void exportExcel(HttpServletRequest req, HttpServletResponse resp)throws Exception, IOException {
       List<Users> data =  userService.queryData(null,1,1000);
        Workbook wb =  new  XSSFWorkbook();
        Sheet sheet = wb.createSheet("user");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("empName");
        row.createCell(2).setCellValue("sex");
        row.createCell(3).setCellValue("age");
        row.createCell(4).setCellValue("deptName");
        row.createCell(5).setCellValue("empDegreeName");
        int k =1;
    for(Users u :data){
        Row data_row = sheet.createRow(k);
        data_row.createCell(0).setCellValue(u.getId());
        data_row.createCell(1).setCellValue(u.getEmpName());
        data_row.createCell(2).setCellValue(u.getSex());
        data_row.createCell(3).setCellValue(u.getAge());
        data_row.createCell(4).setCellValue(u.getDeptName());
        data_row.createCell(5).setCellValue(u.getEmpDegreeName());
        k++;
    }
        resp.setHeader("Content-Disposition","attachment;filename=user.xlsx");
        OutputStream outputStream = resp.getOutputStream();
        wb.write(outputStream);
        wb.close();
}
}
