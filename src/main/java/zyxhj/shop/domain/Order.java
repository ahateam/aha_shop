package zyxhj.shop.domain;

import java.util.Date;

import zyxhj.core.domain.User;
import zyxhj.utils.data.AnnDicField;
import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

@RDSAnnEntity(alias = "tb_shop_order")
public class Order {

	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;

	/**
	 * 订单编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	/**
	 * 门店编号
	 */
	
	@RDSAnnField(column = RDSAnnField.ID)
	public Long storeId;

	/**
	 * 商品编号
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long productId;

	/**
	 * 买家用户编号
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long buyerId;
	
	/**
	 * 地址编号
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long addressId;

	/**
	 * 卖家推广员用户编号
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long pramoterId;

	/**
	 * 分销用户编号（只记录直接上级）
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long resellerId;

	/**
	 * 商品售价
	 */
	@RDSAnnField(column = RDSAnnField.DOUBLE)
	public Double productPrice;

	/**
	 * 最终成交价
	 */
	@RDSAnnField(column = RDSAnnField.DOUBLE)
	public Double donePrice;

	/**
	 * 标题
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public String title;

	/**
	 * 订单状态
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public Byte status;
	
	/**
	 * 渠道编号
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public String channelId;
	
	/**
	 * 创建时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;
	
	/**
	 * 渠道类型
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public String channelType;
	
	/**
	 * 商品详细信息
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public Product product;
	
	/**
	 * 商品sku信息
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String sku;
	
	
	
	@AnnDicField(alias = "订单创建")
	public static final Byte STATUS_INIT = 0;
	@AnnDicField(alias = "待支付")
	public static final Byte STATUS_WAITPAY = 1;
	@AnnDicField(alias = "支付成功")
	public static final Byte STATUS_PAY = 2;
	@AnnDicField(alias = "已发货")
	public static final Byte STATUS_SENDOUTGOODS = 3;
	@AnnDicField(alias = "已收货")
	public static final Byte STATUS_RECEIVINGGOODS = 4;
	@AnnDicField(alias = "已完成")
	public static final Byte STATUS_FINISH = 5;
}
