package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	
	
	@Autowired
	ICartService service;
	
	
	@Test
	public void getByParent() {
		try {
		Cart cart=new Cart();
		cart.setGid(10L);
		cart.setNum(3);
		Integer uid=19;
		String username="购物卡";
		service.addToCart(cart, uid, username);
		System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getByUid() {
			Integer uid=19;
			List<CartVO> result=service.getByUid(uid);
			for (CartVO cartVO : result) {
				System.err.println(cartVO);
				
			}
		
	}
	@Test
	public void add() {
		try {
		Integer uid=19;
		Integer cid=12;
		String username="王欢";
		Integer result=service.add(cid, uid, username);
		System.err.println(result);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}	
		
		
	}
	@Test
	public void getByCids() {
		try {
			Integer[] cids= {12,13,15,16};
			Integer uid=19;
			List<CartVO> result=service.getByCids(cids, uid);
			System.err.println(result);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}	
		
		
	}
}
