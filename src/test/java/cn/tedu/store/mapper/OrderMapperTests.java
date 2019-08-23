package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {

	@Autowired
	private OrderMapper orderMapper;
	
	
	
	@Test
	public void insertOrder() {
		Order order=new Order();
		order.setOid(15);
		order.setUid(20);
		Integer rows=orderMapper.insertOrder(order);
		System.err.println(rows);
	}
	@Test
	public void insertOrderItem() {
		OrderItem orderItem=new OrderItem();
		orderItem.setId(1);
		orderItem.setOid(15);
		orderItem.setGid(10000017L);
		Integer rows=orderMapper.insertOrderItem(orderItem);
		System.err.println(rows);
	}
}
