package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Goods;
/**
 * 处理商品数据的持久层接口
 * @author soft01
 *
 */
public interface GoodsMapper {
	/**
	 * 获取热销商品
	 * @return
	 */
	
	List<Goods> findHotList();
	
	
	/**
	 * 查询商品详情
	 * @param id
	 * @return
	 */
	Goods findById(Long id);
}
