package zyxhj.shop.domain;

import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 流量数据表（
 * @author JXians
 *
 */
public class ShopTraffic {

	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;
	
	/**
	 * 归属编号（店铺编号、商品编号）
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long attributionId;

	/**
	 *	流量编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	
	/**
	 * 类型 
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte Type;
	
	/**
	 * 浏览量
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer NumberOfViews;
	
	/**
	 * 成交量
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer volume;
	
}
