package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;

/**
 * 购物车数据业务接口
 * @author soft01
 *
 */
public interface ICartService {
	
	/**
	 * 
	 * @param cart
	 * @param uid
	 * @param username
	 * @throws InsertException
	 * @throws UpdateException
	 */
	void addToCart(Cart cart,Integer uid,String username)throws InsertException, UpdateException;

	/**
	 * 获得购物车列表
	 * @param uid
	 * @return
	 */
	List<CartVO> getByUid(Integer uid);
	
	
	
	
	//根据购物车id查询购物车数据详情
	Integer add(Integer cid,Integer uid,String username)throws CartNotFoundException,AccessDeniedException;
	
	
	List<CartVO> getByCids(Integer[] cids,Integer uid);

	
}
