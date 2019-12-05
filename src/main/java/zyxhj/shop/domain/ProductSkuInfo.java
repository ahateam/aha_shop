package zyxhj.shop.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;


@RDSAnnEntity(alias = "tb_shop_product_skuinfo")
public class ProductSkuInfo {
	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;

	/**
	 * id编号
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
	
	
	
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String skuName;
	
	
	@RDSAnnField(column = RDSAnnField.ID)
	public double price;

}
