package zyxhj.shop.domain;

import java.util.Date;

import zyxhj.core.domain.User;
import zyxhj.utils.data.AnnDicField;
import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 订单表
 * @author JXians
 *
 */

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
	 * 商品sku信息
	 */
	@RDSAnnField(column = RDSAnnField.LONG)
	public Long sku;
	
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
	 * 购买数量
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer quantity;
	
	/**
	 * 总金额
	 */
	@RDSAnnField(column = RDSAnnField.DOUBLE)
	public Double amount;

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
	 * 创建时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;
	
	
	
	
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
