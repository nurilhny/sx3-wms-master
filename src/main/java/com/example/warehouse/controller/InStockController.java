package com.example.warehouse.controller;

import com.example.warehouse.entity.InStock;
import com.example.warehouse.entity.InStockInfo;
import com.example.warehouse.entity.InStockItem;
import com.example.warehouse.service.InStockItemService;
import com.example.warehouse.service.InStockService;
import com.example.warehouse.service.TrolleyService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InStockController {
    @Autowired
    private InStockService inStockService;
    @Autowired
    private InStockItemService inStockItemService;
    @Autowired
    private TrolleyService trolleyService;

    //通过ID获取入库单
    @GetMapping("/instocks/{inStockId}")
    public InStock getInStockById(@PathVariable int inStockId) {
        return inStockService.selectDetailsById(inStockId);
    }

    //通过类型获取入库单
    @GetMapping("/instocks/category/{inStockCategory}")
    public List<InStock> getInStockByCategory(@PathVariable String inStockCategory) {
        return inStockService.getInStockByCategory(inStockCategory);
    }

    //通过状态获取入库单
    @GetMapping("/instocks/status/{inStockStatus}")
    public List<InStock> getInStockByStatus(@PathVariable String inStockStatus) {
        return inStockService.getInStockByStatus(inStockStatus);
    }

    //通过类型+状态获取入库单
    @GetMapping("/instocks/category/{inStockCategory}/{inStockStatus}")
    public List<InStock> getInStockByCategoryAndStatus(@PathVariable String inStockCategory,
                                                       @PathVariable String inStockStatus) {
        return inStockService.getInStockByCategoryAndStatus(inStockCategory,inStockStatus);
    }

    //获取所有入库单
    @GetMapping("/instocks/")
    public List<InStock> getAllInStocks() {
        return inStockService.selectDetails();
    }

    //导出入库单
    @GetMapping("/instocks/download")
    public void downloadAllInStocks(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建HSSFWorkbook对象,  excel的文档对象
        HSSFSheet sheet = workbook.createSheet("入库单表"); //excel的表单

        List<InStock> instockList = inStockService.selectDetails();

        String fileName = "instocksinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "入库单编号", "入库单类型", "创建者编号", "状态","创建时间"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (InStock inStock : instockList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(inStock.getInStockId());
            row1.createCell(1).setCellValue(inStock.getInStockCategory());
            row1.createCell(2).setCellValue(inStock.getUserId());
            row1.createCell(3).setCellValue(inStock.getInStockStatus());
            row1.createCell(4).setCellValue(inStock.getInStockTime());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    //添加入库单
    @PostMapping("/instocks/")
    public boolean addInStock(@RequestBody InStockInfo inStockInfo) throws InterruptedException{
        System.out.println("qweqweqweqweqweqwe");
        InStock inStock = new InStock();
        inStock.setInStockStatus(inStockInfo.getInStockStatus());
        inStock.setInStockCategory(inStockInfo.getInStockCategory());
        inStock.setInStockItems(inStockInfo.getInStockItems());
        inStock.setUserId(inStockInfo.getUserId());
        Boolean flag1;
        System.out.println(inStock);
        if(inStockInfo.getLoadType().equals("auto")){
            System.out.println("1:"+inStock);
            flag1 = inStockService.save(inStock);
        }else{
            int batch = inStockInfo.getBatch();
            trolleyService.setBusy(batch);
            System.out.println("2:"+inStock);
            flag1 = inStockService.save(inStock);
        }
        List<InStock> inStockList = inStockService.selectDetails();
        int inStockId = 0;
        for (InStock inS:inStockList
             ) {
            if(inStockId<inS.getInStockId()){
                inStockId = inS.getInStockId();
            }

        }

        // 保存inStockId
        for (InStockItem inStockItem:inStock.getInStockItems()
        ) {
            inStockItem.setInStockId(inStockId);
            Boolean flag2 = inStockItemService.save(inStockItem);
            if(!flag2){
                return flag2;
            }
        }

        // 更新库存
//        inStockService.updateStockByInStockId(inStockId);
        return flag1;
    }

    //更新入库单
    @PutMapping("/instocks/")
    public boolean updateInStock(@RequestBody InStock inStock) {
        return inStockService.updateById(inStock);
    }

    //删除入库单
    @DeleteMapping("/instocks/{inStockId}")
    public boolean deleteInStock(@PathVariable int inStockId) {
        return inStockService.removeById(inStockId);
    }

    //通过入库单更新库存
    @PostMapping("/instocks/{inStockId}/updateStock")
    public boolean updateStockByInStockId(@PathVariable int inStockId) {
        return inStockService.updateStockByInStockId(inStockId);
    }

    //通过ID获取入库单项目
    @GetMapping("/instockitems/{inStockItemId}")
    public InStockItem getInStockItemById(@PathVariable int inStockItemId) {
        return inStockItemService.getById(inStockItemId);
    }

    //通过入库单ID获取入库单项目
    @GetMapping("/instockitems/instock/{inStockId}")
    public List<InStockItem> getInStockItemByInStockId(@PathVariable int inStockId) {
        System.out.println("qeqeqweqweqweqweqweq");
        System.out.println(inStockId);
        return inStockItemService.getByInStockId(inStockId);
    }

    //获取所有入库单项目
    @GetMapping("/instockitems/")
    public List<InStockItem> getAllInStockItems() {
        return inStockItemService.list();
    }

    //添加入库单项目
    @PostMapping("/instockitems/")
    public boolean addInStockItem(@RequestBody InStockItem inStockItem) {
        return inStockItemService.save(inStockItem);
    }

    //更新入库单项目
    @PutMapping("/instockitems/")
    public boolean updateInStockItem(@RequestBody InStockItem inStockItem) {

        return inStockItemService.updateById(inStockItem);
    }

    //删除入库单项目
    @DeleteMapping("/instockitems/{inStockItemId}")
    public boolean deleteInStockItem(@PathVariable int inStockItemId) {
        return inStockItemService.removeById(inStockItemId);
    }

}
