package com.rz.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rz.project.common.ErrorCode;
import com.rz.project.exception.BusinessException;
import com.rz.project.model.entity.InterfaceInfo;
import com.rz.project.service.InterfaceInfoService;
import com.rz.project.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author rz
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-12-02 18:35:19
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    private static Integer DELETE_ID= 1;

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add  是否为创建校验
     */
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {

        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String url = interfaceInfo.getUrl();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, url)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 10) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }
}




