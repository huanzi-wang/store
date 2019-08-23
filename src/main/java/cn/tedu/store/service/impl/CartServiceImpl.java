package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;

@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	
	
	//根据购物车id查询购物车数据详情
	private Cart findByCid(Integer cid) {
		return cartMapper.findByCid(cid);
	}
	
	
	/**
	 * 添加购物车数据
	 */
	private void insert(Cart cart) {
		Integer rows=cartMapper.insert(cart);
		if(rows!=1) {
			throw new InsertException("插入失败");
		}
	}

	/**
	 * 修改购物车商品数量
	 */
	private void updateNum(Integer cid,Integer num,String modifiedUser,Date modifiedTime) {
		Integer rows=cartMapper.updateNum(cid, num, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("修改失败");
		}
	}

	/**
	 * 根据用户id和商品id查询购物车商品数量
	 */
	private Cart findByUidAndGid(Integer uid,Long gid) {
		return cartMapper.findByUidAndGid(uid, gid);
	}
	
	/**
	 * 获得购物车列表
	 */
	private List<CartVO> findByUid(Integer uid){
		return cartMapper.findByUid(uid);
	}
	
	private List<CartVO> findByCids(Integer[] cids){
		return cartMapper.findByCids(cids);
	}
	
	
	@Override
	public void addToCart(Cart cart, Integer uid, String username) throws InsertException, UpdateException {
		// 创建时间对象
		Date time=new Date();
	    // 根据参数cart中封装的uid和gid执行查询
		Cart result=findByUidAndGid(uid, cart.getGid());
	    // 检查查询结果是否为null
		if(result==null) {
	    // 是：
	    // -- 基于参数uid向参数cart中封装uid
			cart.setUid(uid);
	    // -- 基于参数username向参数cart中封装createdUser和modifiedUser
			cart.setCreatedUser(username);
			cart.setModifiedUser(username);
	    // -- 向参数cart中封装createdTime和modifiedTime
			cart.setCreatedTime(time);
			cart.setModifiedTime(time);
	    // -- 执行插入数据
			insert(cart);
		}else {
	    // 否：updateNum(cid, num, modifiedUser, modifiedTime);
	    // -- 从查询结果中获取cid
			Integer cid=result.getCid();
	    // -- 从查询结果中获取num，它是商品的原数量
			Integer oldnum=result.getNum();
	    // -- 将以上获取的原数量与参数cart中的num相加，得到新的数量
			Integer newnum=result.getNum()+cart.getNum();
	    // -- 执行修改数量
			updateNum(cid, newnum, username, time);
		}
	}

	@Override
	public List<CartVO> getByUid(Integer uid ) {
		
		return findByUid(uid);
	}


	@Override
	public Integer add(Integer cid, Integer uid, String username) throws CartNotFoundException, AccessDeniedException,UpdateException {
		//根据参数cid查询购物车数据
		Cart result=findByCid(cid);
		//判断查询结果是不是为null
		if(result==null) {
			throw new CartNotFoundException("增加失败，商品不存在");
		}
		//该用户是否是同以用户
		if(!uid.equals(result.getUid())) {
			throw new AccessDeniedException("增加失败，用户不匹配");
		}
		//执行跟新数量
		Integer newnum=result.getNum()+1;
		updateNum(cid, newnum, username, new Date());
		return newnum;
	}


	@Override
	public List<CartVO> getByCids(Integer[] cids,Integer uid) {
		//查询数据
		List<CartVO> results=findByCids(cids);
		//逐一判断结果中的每一条数据，是否都是当前用户数据
		Iterator<CartVO> it=results.iterator();
		while (it.hasNext()) {
			if(uid!=it.next().getUid()) {
				//如果不是当前的数据，移除该数据
				it.remove();
			}
		}
		//返回
		return results;
	}


	

}













