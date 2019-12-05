package zyxhj.shop.domain;

import java.util.Date;

import zyxhj.utils.data.AnnDicField;
import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 商品规格表 (SKU)
 * @author JXians
 *
 */
@RDSAnnEntity(alias = "tb_shop_product_specs")
public class ProductSpecs {
	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;

	/**
	 * 规格id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	
	/**
	 * 商品编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long productId;
	
	/**
	 * 规格
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String sku;
	
	/**
	 * 单价
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Double price;

	/**
	 * 库存
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer inventory;
	
	/**
	 * 商品规格状态
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte status;
	
	/**
	 * 规格创建时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;
	
	/**
	 * 规格修改时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date updateTime;
	
}
