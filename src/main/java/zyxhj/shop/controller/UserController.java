package zyxhj.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.shop.service.UserService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;

public class UserController extends Controller {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	private DruidDataSource ds;
	private UserService userService;

	public UserController(String node) {
		super(node);
		try {
			ds = DataSource.getDruidDataSource("rdsDefault.prop");
			userService = Singleton.ins(UserService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@POSTAPI(//
			path = "createAddress", //
			des = "添加用户收货地址", //
			ret = ""//
	)
	public APIResponse createAddress(//
			@P(t = "模块编号") Long moduleId, //
			@P(t = "用户编号") Long userId, //
			@P(t = "收货人") String userName, //
			@P(t = "收货人电话") String userPhone, //
			@P(t = "省") String province, //
			@P(t = "市") String city, //
			@P(t = "详细地址") String detailed, //
			@P(t = "是否默认地址",r = false) Boolean isDefault//
	) throws Exception {
		try (DruidPooledConnection conn = ds.getConnection()) {
			userService.createAddress(conn, moduleId, userId, userName, userPhone, province, city, detailed, isDefault);
			return APIResponse.getNewSuccessResp();
		}
	}

	@POSTAPI(//
			path = "eidtAddress", //
			des = "修改用户收货地址", //
			ret = "受影响行数"//
	)
	public APIResponse eidtAddress(//
			@P(t = "收货地址编号") Long addressId, //
			@P(t = "收货人") String userName, //
			@P(t = "收货人电话") String userPhone, //
			@P(t = "省") String province, //
			@P(t = "市") String city, //
			@P(t = "详细地址") String detailed, //
			@P(t = "是否默认地址",r = false) Boolean isDefault//
	) throws Exception {
		try (DruidPooledConnection conn = ds.getConnection()) {
			return APIResponse.getNewSuccessResp(
					userService.editAddress(conn, addressId, userName, userPhone, province, city, detailed, isDefault));
		}
	}

	@POSTAPI(//
			path = "setDefaultAddress", //
			des = "设置默认收货地址", //
			ret = "受影响行数"//
	)
	public APIResponse setDefaultAddress(//
			@P(t = "模块编号") Long moduleId, //
			@P(t = "用户编号") Long userId, //
			@P(t = "收货地址编号") Long addressId //
	) throws Exception {
		try (DruidPooledConnection conn = ds.getConnection()) {
			userService.setDefaultAddress(conn, moduleId, userId, addressId);
			return APIResponse.getNewSuccessResp();
		}
	}

	@POSTAPI(//
			path = "getUserAddressList", //
			des = "获取用户收货地址列表", //
			ret = "List<UserAddress>"//
	)
	public APIResponse getUserAddressList(//
			@P(t = "模块编号") Long moduleId, //
			@P(t = "用户编号") Long userId, //
			Integer count, Integer offset//
	) throws Exception {
		try (DruidPooledConnection conn = ds.getConnection()) {
			return APIResponse.getNewSuccessResp(userService.getUserAddressList(conn, moduleId, userId, count, offset));
		}
	}
	
	@POSTAPI(//
			path = "delUserAddress", //
			des = "删除用户收货地址列表", //
			ret = "受影响行数"//
	)
	public APIResponse delUserAddress(//
			@P(t = "模块编号") Long moduleId, //
			@P(t = "用户编号") Long userId, //
			@P(t = "收货地址编号") Long addressId //
	) throws Exception {
		try (DruidPooledConnection conn = ds.getConnection()) {
			return APIResponse.getNewSuccessResp(userService.deleteUserAddress(conn, moduleId, userId, addressId));
		}
	}

}
