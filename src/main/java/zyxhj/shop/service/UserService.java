package zyxhj.shop.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.core.repository.UserRepository;
import zyxhj.shop.domain.UserAddress;
import zyxhj.shop.repository.UserAddressRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.data.EXP;

public class UserService {

	private static Logger log = LoggerFactory.getLogger(UserService.class);
	private UserAddressRepository addressRepository;
	private UserRepository userRepository;

	public UserService() {
		try {
			addressRepository = Singleton.ins(UserAddressRepository.class);
			userRepository = Singleton.ins(UserRepository.class);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 创建用户收货地址
	 * 
	 * @param conn
	 * @param moduleId  模块编号
	 * @param userId    用户编号
	 * @param userName  收货人姓名
	 * @param userPhone 收货人手机号
	 * @param province  省
	 * @param city      市
	 * @param detailed  详细地址
	 * @param isDefault 是否默认地址
	 */
	public void createAddress(DruidPooledConnection conn, Long moduleId, Long userId, String userName, String userPhone,
			String province, String city, String detailed, Boolean isDefault) throws Exception {
		UserAddress address = new UserAddress();
		address.id = IDUtils.getSimpleId();
		address.moduleId = moduleId;
		address.userId = userId;
		address.userName = userName;
		address.userPhone = userPhone;
		address.province = province;
		address.city = city;
		address.detailed = detailed;
		address.status = UserAddress.STATUS_YES;
		if (null == isDefault) {
			address.isDefault = UserAddress.ISDEFAULT_NO;
		} else {
			address.isDefault = UserAddress.ISDEFAULT_YES;
		}

		address.createTime = new Date();
		addressRepository.insert(conn, address);
	}

	/**
	 * 修改用户收货地址
	 * 
	 * @param conn
	 * @param addressId 收货地址编号
	 * @param userName  收货人姓名
	 * @param userPhone 收货人手机号
	 * @param province  省
	 * @param city      市
	 * @param detailed  详细地址
	 * @param isDefault 是否默认
	 * @return
	 * @throws Exception
	 */
	public int editAddress(DruidPooledConnection conn, Long addressId, String userName, String userPhone,
			String province, String city, String detailed, Boolean isDefault) throws Exception {
		UserAddress address = new UserAddress();
		address.userName = userName;
		address.userPhone = userPhone;
		address.province = province;
		address.city = city;
		address.detailed = detailed;
		address.status = UserAddress.STATUS_YES;
		if (null == isDefault) {
			address.isDefault = UserAddress.ISDEFAULT_NO;
		} else {
			address.isDefault = UserAddress.ISDEFAULT_YES;
		}
		return addressRepository.update(conn, EXP.INS().key("id", addressId), address, true);
	}

	/**
	 * 设置用户默认收货地址
	 * 
	 * @param conn
	 * @param moduleId  模块编号
	 * @param userId    用户编号
	 * @param addressId 需要设置为默认的地址编号
	 * @return
	 * @throws Exception
	 */
	public int setDefaultAddress(DruidPooledConnection conn, Long moduleId, Long userId, Long addressId)
			throws Exception {
		// 将默认地址取消
		EXP set = EXP.INS().key("is_default", UserAddress.ISDEFAULT_NO);
		EXP where = EXP.INS().key("module_id", moduleId).andKey("user_id", userId);
		addressRepository.update(conn, set, where);

		// 将当前地址设置为默认地址
		return addressRepository.update(conn, EXP.INS().key("is_default", UserAddress.ISDEFAULT_YES),
				EXP.INS().key("id", addressId));
	}

	/**
	 * 获取用户收货地址列表
	 * 
	 * @param conn
	 * @param moduleId 模块编号
	 * @param userId   用户编号
	 * @param count    查询多少条
	 * @param offset   从第几条开始查询
	 * @return
	 * @throws Exception
	 */
	public List<UserAddress> getUserAddressList(DruidPooledConnection conn, Long moduleId, Long userId, Integer count,
			Integer offset) throws Exception {
		return addressRepository.getList(conn, EXP.INS().key("module_id", moduleId).andKey("user_id", userId), count,
				offset);
	}

	/**
	 * 删除用户收货地址
	 * 
	 * @param conn
	 * @param moduleId  模块编号
	 * @param userId    用户编号
	 * @param addressId 需要删除的地址编号
	 * @return
	 * @throws Exception
	 */
	public int deleteUserAddress(DruidPooledConnection conn, Long moduleId, Long userId, Long addressId)
			throws Exception {
		return addressRepository.delete(conn,
				EXP.INS().key("module_id", moduleId).andKey("user_id", userId).andKey("id", addressId));
	}

}
