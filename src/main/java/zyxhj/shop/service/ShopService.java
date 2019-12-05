package zyxhj.shop.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;

import zyxhj.core.domain.User;
import zyxhj.core.service.UserService;
import zyxhj.shop.domain.Order;
import zyxhj.shop.domain.Product;
import zyxhj.shop.domain.RecommendProduct;
import zyxhj.shop.domain.Store;
import zyxhj.shop.domain.UserAddress;
import zyxhj.shop.repository.OrderRepository;
import zyxhj.shop.repository.ProductRepository;
import zyxhj.shop.repository.RecoProductRepository;
import zyxhj.shop.repository.StoreRepository;
import zyxhj.shop.repository.UserAddressRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.ServiceUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.BaseRC;
import zyxhj.utils.api.Controller;
import zyxhj.utils.api.ServerException;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.EXP;

public class ShopService extends Controller{

	private static Logger log = LoggerFactory.getLogger(ShopService.class);
	private DruidDataSource ds;
	private UserService userService;
	
	private OrderRepository orderRepository;
	private ProductRepository productRepository;
	private StoreRepository storeRepository;
	private UserAddressRepository addressRepository;
	private RecoProductRepository recoProductRepository;
	public ShopService(String node) {
		super(node);
		try {
			ds = DataSource.getDruidDataSource("rdsDefault.prop");
			userService = Singleton.ins(UserService.class);
			
			orderRepository = Singleton.ins(OrderRepository.class);
			productRepository = Singleton.ins(ProductRepository.class);
			storeRepository = Singleton.ins(StoreRepository.class);
			addressRepository = Singleton.ins(UserAddressRepository.class);
			recoProductRepository = Singleton.ins(RecoProductRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "createStore", //
			des = "创建商店", //
			ret = "" //
	)
	public void createStore(
		@P(t = "所属模块编号")Long moduleId,
		@P(t = "状态:默认0开店状态",r = false) Byte status,
		@P(t = "标题") String title
	) throws Exception {
		Store store = new Store();
		store.id = IDUtils.getSimpleId();
		store.moduleId = moduleId;
		if(status == null) {
			store.status = Store.STATUS_OPEN;
		}else {
			store.status = status;			
		}
		store.createTime = new Date();
		store.title = title;
		try(DruidPooledConnection conn = ds.getConnection()){
			storeRepository.insert(conn, store);			
		}
	}
	
	@POSTAPI(//
			path = "createProduct", //
			des = "创建商品", //
			ret = "" //
	)
	public void createProduct(
		@P(t = "所属模块编号")Long moduleId,
		@P(t = "所属商店编号")Long storeId,
		@P(t = "状态")Byte status,
		@P(t = "标题")String title,
		@P(t = "库存")Integer stock,
		@P(t = "成本价")Double costPrice,
		@P(t = "市场价")Double marketPrice,
		@P(t = "售价")Double price,
		@P(t = "会员价")Double memberPrice,
		@P(t = "商品描述内容")String data,
		@P(t = "标签")String tags,
		@P(t = "商品规格信息")String sku
		
	) throws Exception {
		Product p = new Product();
		p.id = IDUtils.getSimpleId();
		p.moduleId = moduleId;
		p.storeId = storeId;
		p.status = status;
		p.title = title;
		p.stock = stock;
		p.costPrice = costPrice;
		p.marketPrice = marketPrice;
		p.price = price;
		p.memberPrice = memberPrice;
		p.data = data;
		p.tags = tags;
		p.sku = sku;
		p.createTime = new Date();
		try(DruidPooledConnection conn = ds.getConnection()){
			productRepository.insert(conn, p);
		}
	}
	
	@POSTAPI(//
			path = "delProduct", //
			des = "删除商品", //
			ret = "" //
	)
	public int setProductPrice(
			@P(t = "所属模块编号")Long moduleId,
			@P(t = "商品id")Long id
	) throws Exception {
		try (DruidPooledConnection conn = ds.getConnection()) {	
			EXP exp = EXP.INS().key("module_id",moduleId).andKey("id", id);
			return productRepository.delete(conn, exp);
		}
	}
	

	
	@POSTAPI(//
			path = "getProducts", //
			des = "查询商品列表", //
			ret = "" //
	)
	public APIResponse getProducts(
			@P(t = "所属模块编号")Long moduleId,
			@P(t = "所属商店编号",r = false)Long storeId,
			@P(t = "状态",r = false)Byte status,
			@P(t = "标题",r = false)String title,			
			@P(t = "标签",r = false)JSONObject tags,
			int count,
			int offset
	) throws Exception {
		EXP exp = EXP.INS(false).key("module_id", moduleId).andKey("store_id", storeId).andKey("status", status);
		if(tags !=null && tags.size()>0) {
			exp.and(EXP.JSON_CONTAINS_JSONOBJECT(tags, "tags"));
		}
		else if(title !=null && title.length()>0) {
			exp.and(EXP.LIKE("title", title));
		}
		try (DruidPooledConnection conn = ds.getConnection()) {			
			List<Product> list = productRepository.getList(conn,exp, count, offset);
			return APIResponse.getNewSuccessResp(ServiceUtils.checkNull(list));			
		}
	}
	
	@POSTAPI(//
			path = "getProduct", //
			des = "查询商品详情", //
			ret = "JSONObject" //
	)
	public JSONObject getProduct(
		@P(t = "模块编号")Long moduleId,
		@P(t = "商品id")Long id
	) throws Exception {
		try(DruidPooledConnection conn = ds.getConnection()){
			Product product =  productRepository.get(conn, EXP.INS().key("module_id", moduleId).andKey("id", id));
			int count = orderRepository.getOrderCount(conn,id);
			JSONObject json = new JSONObject();
			json.put("product", product);
			json.put("count", count);
			return json;
		}
	}
	
	@POSTAPI(//
			path = "createAddress", //
			des = "添加地址", //
			ret = "" //
	)
	public UserAddress createAddress(
		@P(t = "模块编号")Long moduleId,
		@P(t = "用户id")Long userId,
		@P(t = "收货人姓名")String userName,
		@P(t = "收货人手机号码")String userPhone,
		@P(t = "省")String province,
		@P(t = "市" ,r=false)String city,
		@P(t = "详细地址")String detailed,
		@P(t = "是否默认",r = false)Byte isDefault,
		@P(t = "状态",r = false)Byte status
	) throws Exception {
		UserAddress ua = new UserAddress();
		ua.id = IDUtils.getSimpleId();
		ua.createTime = new Date();
		ua.moduleId = moduleId;
		ua.userId = userId;
		ua.userName = userName;
		ua.userPhone = userPhone;
		ua.province = province;
		ua.city = city;
		ua.detailed = detailed;
		try(DruidPooledConnection conn = ds.getConnection()){
			if(isDefault == null || isDefault == UserAddress.ISDEFAULT_NO) {
				ua.isDefault = isDefault;
			}else {//若为默认地址，把已有默认地址改为非默认，默认只能有一个
				//查询此用户是否存在地址
				List<UserAddress> list = getAddress(moduleId, userId, null,null, 300, 0);//查询地址
//				UserAddress u = new UserAddress();
//				u.isDefault = UserAddress.ISDEFAULT_NO;
//				addressRepository.update(conn, EXP.INS().IN("id", list.toArray()), u, true);		
				for(int i=0;i<list.size();i++) {
					UserAddress u = new UserAddress();
					u.id = list.get(i).id;
					u.isDefault = UserAddress.ISDEFAULT_NO;
					addressRepository.update(conn, EXP.INS().key("id", u.id), u, true);
				}
				ua.isDefault = isDefault;
			}
			if(status != null) {
				ua.status = status;			
			}else {
				ua.status = UserAddress.STATUS_YES;
			}
			addressRepository.insert(conn, ua);
			return ua;
		}
	}
	
	@POSTAPI(//
			path = "getAddress", //
			des = "查询地址", //
			ret = "" //
	)
	public List<UserAddress> getAddress(
		@P(t = "模块编号")Long moduleId,
		@P(t = "用户id")Long userId,
		@P(t = "地址id",r = false)Long id,
		@P(t = "默认状态0默认1非默认",r = false)Byte isDefault,
		int count,
		int offset
	) throws Exception {
		EXP exp = EXP.INS(false).key("module_id", moduleId).andKey("user_id", userId).andKey("id", id).andKey("is_default", isDefault);
		try(DruidPooledConnection conn = ds.getConnection()){
			return addressRepository.getList(conn, exp, count, offset);
		}
		
	}
	
	@POSTAPI(//
			path = "createOrder", //
			des = "下订单", //
			ret = "" //
	)
	public Order createOrder(
		@P(t = "模块编号")Long moduleId,
		@P(t = "商店编号")Long storeId,
		@P(t = "商品编号")Long productId,
		@P(t = "买家用户编号")Long buyerId,
		@P(t = "地址编号")Long addressId,
		@P(t = "卖家推广员用户编号")Long pramoterId,
		@P(t = "分销用户编号（只记录直接上级") Long resellerId,
		@P(t = "商品售价") Double productPrice,
		@P(t = "最终成交价") Double donePrice,
		@P(t = "标题") String title,
		@P(t = "渠道编号")  String channelId,
		@P(t = "商品sku信息")  String sku,
		@P(t = "渠道类型编号",r = false) String channelType
	) throws Exception {
		Order o = new Order();
		o.id = IDUtils.getSimpleId();
		o.moduleId = moduleId;
		o.storeId = storeId;
		o.productId = productId;
		o.buyerId = buyerId;
		o.addressId = addressId;
		o.pramoterId = pramoterId;
		o.resellerId = resellerId;
		o.productPrice = productPrice;
		o.donePrice = donePrice;
		o.title = title;
		o.status = Order.STATUS_INIT;
		o.channelId = channelId;
		o.channelType = channelType;
		o.sku = sku;
		o.createTime = new Date();
		try(DruidPooledConnection conn = ds.getConnection()){
			Product pr = productRepository.get(conn, EXP.INS().key("id", productId));
			if(pr.stock>0) {//判断商品库存
				orderRepository.insert(conn, o);
				productRepository.setStock(conn, productId);
				return o;
			}else {
				throw new ServerException(BaseRC.STOCK_BY_ERROR);
			}
		}
	}
	
	@POSTAPI(//
			path = "getOrders", //
			des = "查询订单列表", //
			ret = "" //
	)
	public APIResponse getOrders(
		@P(t = "模块编号")Long moduleId,
		@P(t = "商店编号",r = false)Long storeId,
		@P(t = "买家用户编号",r = false)Long buyerId,
		@P(t = "卖家推广员用户编号",r = false)Long pramoterId,
		@P(t = "分销用户编号（只记录直接上级",r = false) Long resellerId,
		@P(t = "标题",r = false) String title,
		@P(t = "渠道编号",r = false)String channelId,
		int count,
		int offset
	) throws Exception {
		EXP exp = EXP.INS(false).key("module_id", moduleId).andKey("store_id", storeId).andKey("buyer_id", buyerId)
				.andKey("pramoter_id", pramoterId).andKey("reseller_id", resellerId).andKey("channel_id", channelId);
		if(title !=null && title.length()>0) {
			exp.and(EXP.LIKE("title", title));
		}
		try (DruidPooledConnection conn = ds.getConnection()) {			
			List<Order> list = orderRepository.getList(conn,exp, count, offset);
			for(int i=0;i<list.size();i++) {
				list.get(i).product = productRepository.get(conn, EXP.INS().key("id", list.get(i).productId));
			}
			return APIResponse.getNewSuccessResp(ServiceUtils.checkNull(list));			
		}
	}
	
	@POSTAPI(//
			path = "getOrder", //
			des = "查询订单详情", //
			ret = "" //
	)
	public JSONObject getOrder(
		@P(t = "模块编号")Long moduleId,
		@P(t = "订单编号")Long id
	) throws Exception {
		try(DruidPooledConnection conn = ds.getConnection()){
			Order ord =  orderRepository.get(conn, EXP.INS().key("module_id", moduleId).andKey("id", id));
			User user = userService.getUserById(conn, ord.id);
			Store store = storeRepository.get(conn, EXP.INS().key("id", ord.storeId));
			UserAddress address = addressRepository.get(conn, EXP.INS().key("id", ord.addressId)); 
			JSONObject json = new JSONObject();
			json.put("order", ord);
			json.put("user", user);
			json.put("store", store);
			json.put("address", address);
			return json;
		}
	}
	@POSTAPI(//
			path = "editOrderStatus", //
			des = "修改订单状态", //
			ret = "" //
	)
	public Order editOrderStatus(
		@P(t = "模块编号")Long moduleId,
		@P(t = "订单编号")Long id,
		@P(t = "订单状态")Byte status
	) throws Exception {
		try(DruidPooledConnection conn = ds.getConnection()){
			Order or = new Order();
			or.status = status;
			orderRepository.update(conn, EXP.INS().key("module_id", moduleId).andKey("id", id), or, true);
			return or;
		}
	}
	
	
	@POSTAPI(//
			path = "createRecommendProduct", //
			des = "创建推荐商品", //
			ret = "" //
	)
	public void createRecommendProduct(
		@P(t = "所属模块编号")Long moduleId,
		@P(t = "所属商店编号")Long storeId,
		@P(t = "状态")Byte status,
		@P(t = "标题")String title,
		@P(t = "库存")Integer stock,
		@P(t = "成本价")Double costPrice,
		@P(t = "市场价")Double marketPrice,
		@P(t = "售价")Double price,
		@P(t = "会员价")Double memberPrice,
		@P(t = "商品描述内容")String data,
		@P(t = "标签")String tags,
		@P(t = "商品规格信息sku")String sku,
		@P(t = "推荐商品扩展信息",r = false)String ext		
	) throws Exception {
		RecommendProduct p = new RecommendProduct();
		p.id = IDUtils.getSimpleId();
		p.moduleId = moduleId;
		p.storeId = storeId;
		p.status = status;
		p.title = title;
		p.stock = stock;
		p.costPrice = costPrice;
		p.marketPrice = marketPrice;
		p.price = price;
		p.memberPrice = memberPrice;
		p.data = data;
		p.tags = tags;
		p.sku = sku;
		p.createTime = new Date();
		p.ext = ext;
		try(DruidPooledConnection conn = ds.getConnection()){
			recoProductRepository.insert(conn, p);
		}
	}
	
	@POSTAPI(//
			path = "getRecoProducts", //
			des = "查询推荐商品列表", //
			ret = "" //
	)
	public APIResponse getRecoProducts(
			@P(t = "所属模块编号")Long moduleId,
			@P(t = "所属商店编号",r = false)Long storeId,
			@P(t = "状态",r = false)Byte status,
			@P(t = "标题",r = false)String title,			
			@P(t = "标签",r = false)JSONObject tags,
			int count,
			int offset
	) throws Exception {
		EXP exp = EXP.INS(false).key("module_id", moduleId).andKey("store_id", storeId).andKey("status", status);
		if(tags !=null && tags.size()>0) {
			exp = exp.and(EXP.JSON_CONTAINS_JSONOBJECT(tags, "tags"));
		}
		else if(title !=null && title.length()>0) {
			exp = exp.and(EXP.LIKE("title", title));
		}
		try (DruidPooledConnection conn = ds.getConnection()) {			
			List<RecommendProduct> list = recoProductRepository.getList(conn,exp, count, offset);
			return APIResponse.getNewSuccessResp(ServiceUtils.checkNull(list));			
		}
	}
	
	@POSTAPI(//
			path = "getRecoProduct", //
			des = "查询推荐商品详情", //
			ret = "" //
	)
	public void getRecoProduct(
		@P(t = "模块编号")Long moduleId,
		@P(t = "商品id")Long id
	) throws Exception {
		try(DruidPooledConnection conn = ds.getConnection()){
			recoProductRepository.get(conn, EXP.INS().key("module_id", moduleId).andKey("id", id));		
		}
	}
	
	
//	@POSTAPI(//
//			path = "getProductTag", //
//			des = "查询商品标签", //
//			ret = "" //
//	)
//	public void getProductTag(
//		@P(t = "模块编号")String module
//		
//	) throws Exception {
//		
//	}
//	
//	@POSTAPI(//
//			path = "setProductTag", //
//			des = "设置商品标签", //
//			ret = "" //
//	)
//	public void setProductTag(
//		@P(t = "模块编号")String module
//		
//	) throws Exception {
//		
//	}
	
	
}
