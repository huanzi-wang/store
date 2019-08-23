package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

/**
   * 处理收货地址数据的持久层接口
 */
public interface AddressMapper {

	/**
	    * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer insert(Address address);

	/**
	   *   统计某个用户的收货地址数据的数量
	 * @param uid 用户的id
	 * @return 该用户的收货地址数据的数量
	 */
	Integer countByUid(Integer uid);
	
	
	/**
	   * 根据uid查询收货地址详细信息
	 * @param uid 用户 id
	 * @return 该用户收货地址的信息
	 */
	List<Address> findByUid(Integer uid);
	
	
	
	/**
	 * 设置所有地址为非默认
	 * @param uid 用户id
	 * @return 受影响的条数
	 */
	Integer updateNonDefault(Integer uid);
	
	
	/**
	 * 设置指定地址为默认收货地址
	 * @param aid 用户id
	 * @return 受影响的条数
	 */
	Integer updateDefault(@Param("aid")Integer aid,
						  @Param("modifiedUser")String modifiedUser, 
						  @Param("modifiedTime")Date modifiedTime);
	
	
	/**
	 * 根据用户的id查询是否是该用户对应的收货地址
	 * @param aid 用户id
	 * @return 用户信息
	 */
	Address findByAid(Integer aid);
	
	
	/**
	 * 删除指定的收货地址
	 * @param aid  指定的aid
	 * @return 受影响的数据
	 */
	Integer deleteByAid(Integer aid);
	
	
	/**
	 * 根据uid查询最近修改的收货地址
	 * @param uid 用户id
	 * @return 查询的数据信息
	 */
	Address findLastModified(Integer uid);
	
}






