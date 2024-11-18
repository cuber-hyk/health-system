package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.UserHeartRate;
import java.util.List;

/**
 * 用户心率信息：Mapper接口
 */
public interface UserHeartRateMapper extends BaseMapper<UserHeartRate> {

    /**
     * 通过ID查询用户心率信息
     *
     * @param userHeartRateId 用户心率信息ID
     * @return 用户心率信息对象
     */
    UserHeartRate selectUserHeartRateById(Integer userHeartRateId);

    /**
     * 查询用户心率信息列表
     *
     * @param userHeartRate 用户心率信息对象，用于传递查询条件
     * @return 用户心率信息对象列表
     */
    List<UserHeartRate> selectUserHeartRateList(UserHeartRate userHeartRate);

    /**
     * 新增用户心率信息
     *
     * @param userHeartRate 用户心率信息对象
     * @return 影响的行数
     */
    int insertUserHeartRate(UserHeartRate userHeartRate);

    /**
     * 修改用户心率信息
     *
     * @param userHeartRate 用户心率信息对象
     * @return 影响的行数
     */
    int updateUserHeartRate(UserHeartRate userHeartRate);

    /**
     * 通过ID删除用户心率信息
     *
     * @param userHeartRateId 用户心率信息ID
     * @return 影响的行数
     */
    int deleteUserHeartRateById(Integer userHeartRateId);

    /**
     * 批量删除用户心率信息
     *
     * @param userHeartRateIds 用户心率信息ID数组
     * @return 影响的行数
     */
    int deleteUserHeartRateByIds(Integer[] userHeartRateIds);
}