package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	/**
	 * 获取热销商品
	 * @return
	 */
	
	List<Goods> getHotList();
	
	/**
	 * 查询商品详情
	 * @param id
	 * @return
	 */
	Goods getById(Long id);
}
