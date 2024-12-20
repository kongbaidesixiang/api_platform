package com.rz.project.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rz.project.annotation.AuthCheck;
import com.rz.project.common.*;
import com.rz.project.constant.CommonConstant;
import com.rz.project.exception.BusinessException;
import com.rz.project.model.dto.InterfaceInfo.InterfaceInfoAddRequest;
import com.rz.project.model.dto.InterfaceInfo.InterfaceInfoQueryRequest;
import com.rz.project.model.dto.InterfaceInfo.InterfaceInfoUpdateRequest;
import com.rz.project.model.dto.InterfaceInfo.InvokeInterfaceRequest;
import com.rz.project.model.entity.InterfaceInfo;
import com.rz.project.model.entity.User;
import com.rz.project.service.InterfaceInfoService;
import com.rz.project.service.UserService;
import com.rz.rzclientsdk.RzAPIClientConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 接口信息接口
 *
 * @author rz
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    @Resource
    private RzAPIClientConfig rzAPIClientConfig;

    // region 增删改查

    /**
     * 创建
     *
     * @param interfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        // 校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        boolean result = interfaceInfoService.save(interfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newInterfaceInfoId = interfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        // 判断是否存在
        User user1 = userService.getById(user.getId());
        if (user1 == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!user1.getId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = interfaceInfoService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param interfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest,
                                            HttpServletRequest request) {
        if (interfaceInfoUpdateRequest == null || interfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        // 参数校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = interfaceInfoUpdateRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        return ResultUtils.success(interfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        if (interfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        return ResultUtils.success(interfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param interfaceInfoQueryRequest
     * @param request
     * @return
     */
    @SneakyThrows
    @GetMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, HttpServletRequest request) {
        if (interfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();
        String description = interfaceInfoQuery.getDescription();
        // content 需支持模糊搜索
        interfaceInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<InterfaceInfo> page = interfaceInfoService.page(new Page<>(current, size));
        return ResultUtils.success(page);
    }

    // endregion


    /**
     * 发布接口
     *
     * @param idRequest
     * @return
     */
    @PostMapping("/publishing")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> PublishingInterface(IdRequest idRequest) {
        // 检验当前id是否存在
        if(idRequest== null || idRequest.getId()<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 判断接口是否存在
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(idRequest.getId());
        if(interfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 判断接口是否可以调用
        com.rz.rzclientsdk.model.dto.User user = new com.rz.rzclientsdk.model.dto.User();
        user.setName("MASM");
        String name = rzAPIClientConfig.httpClient().getnameByPostUserWithJSON(user);
        if(StrUtil.isBlank(name)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"接口验证失败");
        }
        // 更新数据
        InterfaceInfo newInterfaceInfo = new InterfaceInfo();
        newInterfaceInfo.setId(idRequest.getId());
        newInterfaceInfo.setStatus(String.valueOf(InterfaceStatusConstant.ONLINE.getValue()));
        boolean result = interfaceInfoService.updateById(newInterfaceInfo);

        return ResultUtils.success(result);
    }


    /**
     * 下线接口
     *
     * @param idRequest
     * @return
     */
    @PostMapping("/base")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> BaseInterface(IdRequest idRequest) {
        // 检验当前id是否存在
        if(idRequest== null || idRequest.getId()<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 判断接口是否存在
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(idRequest.getId());
        if(interfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 更新数据
        InterfaceInfo newInterfaceInfo = new InterfaceInfo();
        newInterfaceInfo.setId(idRequest.getId());
        newInterfaceInfo.setStatus(String.valueOf(InterfaceStatusConstant.OFFLINE.getValue()));
        boolean result = interfaceInfoService.updateById(newInterfaceInfo);
        return ResultUtils.success(result);
    }


    /**
     * 调用接口
     *
     * @param invokeInterfaceRequest
     * @param request
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse<Object> InvokeInterface(@RequestBody InvokeInterfaceRequest invokeInterfaceRequest,HttpServletRequest request) {
        // 检验当前id是否存在
        if (invokeInterfaceRequest == null || invokeInterfaceRequest.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
        }

        // 判断接口是否存在
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(invokeInterfaceRequest.getId());
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 判断接口状态
        if (Integer.parseInt(interfaceInfo.getStatus()) != InterfaceStatusConstant.OFFLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口未上线");
        }
//
//        User loginUser = userService.getLoginUser(request);
//        String accessKey =loginUser.getAccessKey();
//        String secretKey = loginUser.getSecretKey();

        //todo 先将请求写死
        String params = invokeInterfaceRequest.getRequestParams();
        com.rz.rzclientsdk.model.dto.User user = JSONUtil.toBean(params, com.rz.rzclientsdk.model.dto.User.class);
        String result = rzAPIClientConfig.httpClient().getnameByPostUserWithJSON(user);
        return ResultUtils.success(result);
        }
}
