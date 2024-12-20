package com.rz.project.service;

import com.rz.project.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rz.project.model.entity.Post;

/**
* @author 醒目
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-12-02 18:35:19
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
