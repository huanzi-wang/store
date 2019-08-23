package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

public interface CartMapper {
	/**
	 * 添加购物车数据
	 * @param cart
	 * @return 返回受影响的行数
	 */
	Integer insert(Cart cart);

	
	
	
	/**
	 * 修改购物车商品数量
	 * @param cid 购物车数据id
	 * @param num 薪的数量
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 返回受影响的行数
	 */
	Integer updateNum(
			@Param("cid")Integer cid,
			@Param("num")Integer num,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	
	
	/**
	 * 根据用户id和商品id查询购物车商品数量
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return 匹配查询的信息 没有则返回null
	 */
	Cart findByUidAndGid(
			@Param("uid")Integer uid,
			@Param("gid")Long gid);
	
	
	
	/**
	 * 显示购物车列表
	 * @param uid 用户id
	 * @return 匹配的数据
	 */
	List<CartVO> findByUid(Integer uid);
	
	
	
	/**
	 * 根据购物车id查询购物车数据详情
	 * @param cid
	 * @return
	 */
	Cart findByCid(Integer cid);
	
	
	List<CartVO> findByCids(Integer[] cids);
}











