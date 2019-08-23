package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

//处理订单和商品的持久层接口
public interface OrderMapper {
	/**
	 * 插入订单详情
	 * @param order
	 * @return
	 */
	Integer insertOrder(Order order);
	
	/**
	 * 插入商品详情
	 * @param orderItem
	 * @return
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
}
