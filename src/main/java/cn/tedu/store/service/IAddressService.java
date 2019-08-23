package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

/**
 * 处理收货地址数据的业务层接口
 */
public interface IAddressService {

	/**
	 * 收货地址数量上限
	 */
	int ADDRESS_MAX_COUNT = 20;
	
	/**
	 * 增加新的收货地址
	 * @param address 收货地址数据
	 * @param uid 用户id
	 * @param username 用户名
	 * @throws AddressCountLimitException 收货地址数量达到上限
	 * @throws InsertException 插入数据异常
	 */
	void addnew(Address address, Integer uid, String username) 
			throws AddressCountLimitException, InsertException;
	
	
	/**
	   * 根据uid查询收货地址详细信息
	 * @param uid 用户 id
	 * @return 该用户收货地址的信息
	 */
	List<Address> getByUid(Integer uid);
	
	
	
	/**
	 *   设置默认收货地址
	 * @param aid 
	 * @param uid
	 * @param username
	 * @throws AddressNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	
	void setDefault(Integer aid,Integer uid,String username)
			throws AddressNotFoundException, AccessDeniedException, UpdateException;
	
	
	
	/**
	 * 删除收货地址
	 * @param aid
	 * @param uid
	 * @param username
	 * @throws AddressNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 * @throws DeleteException
	 */
	void delete(Integer aid,Integer uid,String username)
			throws AddressNotFoundException, AccessDeniedException, 
			UpdateException,DeleteException;
	
}







