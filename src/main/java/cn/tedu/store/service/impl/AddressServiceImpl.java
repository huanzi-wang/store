package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private IDistrictService districtService;

	@Override
	public void addnew(Address address, Integer uid, String username)
			throws AddressCountLimitException, InsertException {
		// 根据参数uid查询当前用户的收货地址数量
		Integer count = countByUid(uid);
		// 判断收货地址数量是否达到上限值ADDRESS_MAX_COUNT
		if (count >= ADDRESS_MAX_COUNT) {
			// 是：抛出：AddressCountLimitException
			throw new AddressCountLimitException(
				"增加收货地址失败！当前收货地址数量已经达到上限！最多允许创建" + ADDRESS_MAX_COUNT + "条，已经创建" + count + "条！");
		}

		// 补全数据：uid
		address.setUid(uid);

		// 补全数据：province_name, city_name, area_name
		District province=districtService.getByCode(address.getProvinceCode());
		District city=districtService.getByCode(address.getCityCode());
		District area=districtService.getByCode(address.getAreaCode());
		if(province==null) {
			address.setProvinceCode(null);
		}else {
			address.setProvinceName(province.getName());
		}
		if(city==null) {
			address.setCityCode(null);
		}else {
			address.setCityName(city.getName());
		}
		if(area==null) {
			address.setAreaCode(null);
		}else {
			address.setAreaName(area.getName());
		}
		
		// 判断当前用户的收货地址数量是否为0，并决定is_default的值
		Integer isDefault = count == 0 ? 1 : 0;
		// 补全数据：is_default
		address.setIsDefault(isDefault);

		// 创建当前时间对象
		Date now = new Date();
		// 补全数据：4个日志
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);

		// 插入收货地址数据
		insert(address);
	}
	

	@Override
	public List<Address> getByUid(Integer uid) {
		return findByUid(uid);
	}
	
	
	
	
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @throws InsertException 插入数据异常
	 */
	private void insert(Address address) throws InsertException {
		Integer rows = addressMapper.insert(address);
		if (rows != 1) {
			throw new InsertException(
				"增加收货地址失败！插入数据时出现未知错误！");
		}
	}

	/**
	 * 统计某个用户的收货地址数据的数量
	 * @param uid 用户的id
	 * @return 该用户的收货地址数据的数量
	 */
	private Integer countByUid(Integer uid) {
		return addressMapper.countByUid(uid);
	}


	
	
	/**
	   * 根据uid查询收货地址详细信息
	 * @param uid 用户 id
	 * @return 该用户收货地址的信息
	 */
	private List<Address> findByUid(Integer uid){
		return addressMapper.findByUid(uid);
	}
	
	
	
	private void updateNonDefault(Integer uid){
		Integer rows=addressMapper.updateNonDefault(uid);
		if(rows==0) {
			throw new UpdateException("设置非默认失败");
		}
	}

	private void updateDefault(Integer aid, String modifiedUser, Date modifiedTime){
		Integer rows=addressMapper.updateDefault(aid, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("设置默认失败");
		}
	}

	private Address findByAid(Integer aid){
		return addressMapper.findByAid(aid);
	}


	@Override
	@Transactional
	public void setDefault(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, AccessDeniedException, UpdateException {
		 // 根据aid查询收货地址数据
		Address result=findByAid(aid);
	    // 判断结果是否为null
		if(result==null) {
	    // 是：抛出AddressNotFoundException
			throw new AddressNotFoundException("设置默认失败，收货地址不存在");
		}
	    // 判断结果中的uid与参数uid是否不一致
		if(result.getUid()!=uid) {	
	    // 是：抛出AccessDeniedException
			throw new AccessDeniedException("设置默认失败，拒绝访问他人的数据");
		}
	    // 将该用户所有收货地址设置为非默认
		updateNonDefault(uid);
	    // 将指定的收货地址设置为默认
		updateDefault(aid, username, new Date());
	}
	
	//根据uid查询最近修改的收货地址
	private Address findLastModified(Integer uid) {
		return addressMapper.findLastModified(uid);
	}
	
	
	//删除指定的收货地址
	private void deleteByAid(Integer aid) {
		addressMapper.deleteByAid(aid);
	}


	@Override
	@Transactional
	public void delete(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, AccessDeniedException, UpdateException, DeleteException {
		//根据aid查询收货地址数据
		Address result=findByAid(aid);
		//判断结果是否为null
		if(result==null) {
			//是：抛出异常AddressNonFoundException
			throw new AddressNotFoundException("删除收货地址失败，收货地址不存在");
		}
		// 判断结果中的uid与参数uid是否不一致
		if(result.getUid()!=uid) {	
		// 是：抛出AccessDeniedException
			throw new AccessDeniedException("删除收货地址失败，拒绝访问他人的数据");
		}
		//执行删除
		deleteByAid(aid);
		
		
		//判断此前的查询结果中的isDefault是否为0
		if(result.getIsDefault()==0) {
			return;
		}
		
		
		//统计当前用户的的后货地址数量：countByUid
		Integer count=countByUid(uid);
		//判断剩余收货地址数量是否为0
		if(count==0) {
			return;	
		}
		
		
		//查询当前用户最近修改的收货地址
		Address address=findLastModified(uid);
		
		//将最近修改的收货地址设置为默认
		updateDefault(address.getAid(),username, new Date());
	}
	
	
	
	
	
	
}





