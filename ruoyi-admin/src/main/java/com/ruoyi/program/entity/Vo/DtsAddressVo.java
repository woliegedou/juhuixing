package com.ruoyi.program.entity.Vo;

import com.ruoyi.program.entity.DtsAddress;
import com.ruoyi.program.entity.DtsUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtsAddressVo {


    private Integer id;

    private String name;

    private Integer userId;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String address;

    private String mobile;

    private Integer isDefault;

    private List<DtsAddress> dtsUserList;

    private List<DtsAddress> dtsAddressList;
}
