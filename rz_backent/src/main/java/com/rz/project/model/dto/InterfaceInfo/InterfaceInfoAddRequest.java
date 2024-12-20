package com.rz.project.model.dto.InterfaceInfo;

import lombok.Data;
import java.io.Serializable;

/**
 * 创建请求
 * @author rz
 *
 * @TableName product
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 网址
     */
    private String url;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 请求参数
     */
    private String requestParameter;

    /**
     * 请求类型
     */
    private String method;

    private static final long serialVersionUID = 1L;
}