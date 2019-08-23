package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
	
	@Autowired
	IAddressService service;
	
	@Test
	public void addnew() {
		try {
			Integer uid = 2;
			String username = "系统管理员";
			Address address = new Address();
			address.setName("小孙同学");
			service.addnew(address, uid, username);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	

	@Test
	public void getByUid() {
		try {
			Integer uid = 19;
			List<Address> list=service.getByUid(uid);
			for (Address add : list) {
				System.err.println(add);
			}
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void setDefault() {
		try {
			Integer aid = 7;
			Integer uid = 19;
			String username = "悟空";
			service.setDefault(aid,uid,username);
			System.err.println("ok.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void delete() {
		try {
			Integer aid = 14;
			Integer uid = 19;
			String username = "悟空";
			service.delete(aid,uid,username);
			System.err.println("ok.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}

}







