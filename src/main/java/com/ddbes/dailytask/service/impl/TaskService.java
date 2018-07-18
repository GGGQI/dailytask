package com.ddbes.dailytask.service.impl;

import com.ddbes.dailytask.dao.TaskMapper;
import com.ddbes.dailytask.entity.Task;
import com.ddbes.dailytask.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 添加任务
     * @param task
     * @return
     */
    @Override
    public ResponseEntity addTask(Task task) {
        Map map = new HashMap();
        int i = taskMapper.insert(task);
        if (i != 0) {
            map.put("msg","success");
        } else {
            map.put("msg", "添加失败");
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }

    /**
     * 修改任务
     * @param task
     * @return
     */
    @Override
    public ResponseEntity updateTask(Task task) {
        Map map = new HashMap();
        int i = taskMapper.updateByPrimaryKeySelective(task);
        if (i != 0) {
            map.put("msg","success");
        } else {
            map.put("msg", "修改失败");
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }

    /**
     * 删除任务
     * @param taskId
     * @return
     */
    @Override
    public ResponseEntity deleteTask(Long taskId) {
        Map map = new HashMap();
        int i = taskMapper.deleteByPrimaryKey(taskId);
        if (i != 0) {
            map.put("msg","success");
        } else {
            map.put("msg", "删除失败");
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }

    /**
     * 查看任务
     * @param param
     * @return
     */
    @Override
    public ResponseEntity findTask(Map param) {
        Map map = new HashMap();
        List taskList = taskMapper.findTask(param);
        map.put("taskList",taskList);
        map.put("msg","success");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    /**
     * 查询个人本周任务
     * @param userId
     * @return
     */
    @Override
    public ResponseEntity findTaskByUserId(Long userId) {
        Map map = new HashMap();
        List<Map<String, Object>> taskList = taskMapper.findTaskByUserId(userId);
        map.put("taskList",taskList);
        map.put("msg","success");
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 打印报表
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
//    @Override
//    public Object print(String userId, String startTime, String endTime) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet();
//        Row row = sheet.createRow(0);
//        Cell cell = row.createCell(0);
//        String title = "加优科技"+startTime+"至"+endTime+"各组工作计划";
//        cell.setCellValue(title);
//        //设置样式
//        CellStyle style = workbook.createCellStyle();
//        //标题
//        //设置字体
//        Font font = workbook.createFont();
//        font.setFontName("宋体");
//        font.setBold(true);
//        font.setFontHeightInPoints((short)20);
//        style.setFont(font);
//        cell.setCellStyle(style);
//        //计划编号
//        Row row1 = sheet.createRow(1);
//        row1.createCell(1).setCellValue("计划编号");
//        row1.createCell(2).setCellValue("1");
//        row1.createCell(3).setCellValue("2");
//        row1.createCell(4).setCellValue("3");
//        row1.createCell(5).setCellValue("4");
//        row1.createCell(6).setCellValue("5");
//        row1.createCell(7).setCellValue("1");
//        row1.createCell(8).setCellValue("2");
//        row1.createCell(9).setCellValue("3");
//        row1.createCell(10).setCellValue("4");
//        row1.createCell(11).setCellValue("5");
//        int num = 2;
//        //查询各组成员
//        List<Map<String,Object>> groupList = taskMapper.findGroup();
//        //各组成员计划
//        for (int i = 0;i< groupList.size();i++) {
//            Row row2 = sheet.createRow(num++);
//            Cell cell1 = row2.createCell(0);
//            cell1.setCellValue(groupList.get(i).get("groupName").toString());
//            font.setFontHeightInPoints((short)14);
//            style.setFont(font);
//            int count = Integer.parseInt(groupList.get(i).get("count").toString());
//            //TODO 设置格高
//            int height = count%2 == 0?count/2:(count/2)+1;
//            height = height == 0? 1: height;
//            cell1.setCellStyle(style);
//            List<Map<String,Object>> memberList = taskMapper.findMember(groupList.get(i).get("groupId").toString());
//            int memberSize= 0;
//                for (int k = 0;k<height;k++) {
//                    Cell cell2 = row2.createCell(1);
//                    cell2.setCellValue("员工姓名");
//                    font.setFontHeightInPoints((short)14);
//                    style.setFont(font);
//                    cell2.setCellStyle(style);
//                    Row row3 = sheet.createRow(num++);
//                    row3.createCell(1).setCellValue("工作内容");
//                    Row row4 = sheet.createRow(num++);
//                    row4.createCell(1).setCellValue("完成情况");
//                    Row row5 = sheet.createRow(num++);
//                    row5.createCell(1).setCellValue("计划外工作");
//                    if (memberList != null && memberList.size()>0) {
//                        if (k == height-1 && memberList.size()%2 != 0) {
//                            List<Map<String,Object>> taskList = (List<Map<String, Object>>) memberList.get(memberSize).get("taskList");
//                            row2.createCell(2).setCellValue(memberList.get(memberSize++).get("username")+"");
//                            for (int j = 0;j<taskList.size();j++) {
//                                //写入工作内容
//                                row3.createCell(2+j).setCellValue(taskList.get(j).get("content")+"");
//                                row4.createCell(2+j).setCellValue(taskList.get(j).get("complete")+"");
//                                row5.createCell(2+j).setCellValue(taskList.get(j).get("plan")+"");
//                            }
//                        } else {
//                            List<Map<String,Object>> taskList = (List<Map<String, Object>>) memberList.get(memberSize).get("taskList");
//                            row2.createCell(2).setCellValue(memberList.get(memberSize++).get("username")+"");
//                            for (int j = 0;j<taskList.size();j++) {
//                                //写入工作内容
//                                row3.createCell(2+j).setCellValue(taskList.get(j).get("content")+"");
//                                row4.createCell(2+j).setCellValue(taskList.get(j).get("complete")+"");
//                                row5.createCell(2+j).setCellValue(taskList.get(j).get("plan")+"");
//                            }
//                            taskList = (List<Map<String, Object>>) memberList.get(memberSize).get("taskList");
//                            row2.createCell(7).setCellValue(memberList.get(memberSize++).get("username")+"");
//                            for (int j = 0;j<taskList.size();j++) {
//                                //写入工作内容
//                                row3.createCell(7+j).setCellValue(taskList.get(j).get("content")+"");
//                                row4.createCell(7+j).setCellValue(taskList.get(j).get("complete")+"");
//                                row5.createCell(7+j).setCellValue(taskList.get(j).get("plan")+"");
//                            }
//                        }
//                    }
//                }
//        }
//        OutputStream os = new FileOutputStream("d:/teat.xlsx");
////        String userAgent = request.getHeader("User-Agent");
////        106             output = response.getOutputStream();
////        107             response.reset();
////        108             response.setHeader("Content-disposition", "attachment; filename="+ StringUtil.encodeFileName(fileName, userAgent));
////        109             response.setContentType("application/vnd.ms-excel;charset=utf-8");
////        110             response.setCharacterEncoding("utf-8");
////        111             wb.write(output);
//        workbook.write(os);
//        workbook.close();
//        return null;
//    }

}
