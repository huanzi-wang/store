package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
	
	@Autowired
	private CartMapper mapper;
	
	
	@Test
	public void insert() {
		Cart cart=new Cart();
		cart.setUid(7);
		cart.setGid(1L);
		cart.setNum(2);
		Integer rows=mapper.insert(cart);
		System.err.println(rows);
		
	}
	@Test
	public void updateNum() {
		Integer cid=1;
		Integer num=5;
		String modifiedUser="管理员";
		Date modifiedTime=new Date();
		Integer rows=mapper.updateNum(cid,cid,modifiedUser,modifiedTime);
		System.err.println(rows);
		
	}
	@Test
	public void findByUidAndGid() {
		Integer uid=19;
		Long gid=1L;
		Cart result=mapper.findByUidAndGid(uid, gid);
		System.err.println(result);
	}
	@Test
	public void findByUid() {
		Integer uid=19;
		List<CartVO> result=mapper.findByUid(uid);
		System.err.println(result);
	}
	@Test
	public void findByCid() {
		Integer cid=12;
		Cart result=mapper.findByCid(cid);
		System.err.println(result);
	}
	@Test
	public void findByCids() {
		Integer[] cids={12,13,14};
		List<CartVO> result=mapper.findByCids(cids);
		System.err.println(result);
	}
}




