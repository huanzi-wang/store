package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;

@Service
public class DoodsServiceImpl implements IGoodsService{
	@Autowired
	private GoodsMapper goodsMapper;
	
	/**
	 * 获取热销商品
	 * @return
	 */
	
	private List<Goods> findHotList(){
		return goodsMapper.findHotList();
	}
	
	/**
	 * 查询商品详情
	 * @param id
	 * @return
	 */
	private Goods findById(Long id) {
		return goodsMapper.findById(id);
	}
	
	@Override
	public List<Goods> getHotList() {
		return findHotList();
	}

	@Override
	public Goods getById(Long id) {
		return findById(id);
	}

}
