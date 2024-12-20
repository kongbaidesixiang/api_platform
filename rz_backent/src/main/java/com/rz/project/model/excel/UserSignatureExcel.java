package com.rz.project.model.excel;


//import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;


@EqualsAndHashCode
@Data
@Builder
public class UserSignatureExcel {

//    @ExcelProperty(value = "accessKey",index = 0)
    private Integer accessKey;
//    @ExcelProperty(value = "secretKey",index = 1)
    private String secretKey;
}
