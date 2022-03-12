package com.lxk.campustourguide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxk.campustourguide.bean.Tourist;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lxkstart
 * @date 2022/3/11 - 23:36
 */
@Mapper
public interface TouristMapper extends BaseMapper<Tourist> {
}
