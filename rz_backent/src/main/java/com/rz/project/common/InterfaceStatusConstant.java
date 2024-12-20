package com.rz.project.common;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口状态枚举
 */
public enum InterfaceStatusConstant {

   ONLINE("上线",1),

   OFFLINE("下线", 0);

    private final String text;

    private final int value;

    /**
     *
     * @return 获取值列表
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    InterfaceStatusConstant(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
