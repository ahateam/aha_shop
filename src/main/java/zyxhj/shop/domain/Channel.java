package zyxhj.shop.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 
 * 渠道
 */
@RDSAnnEntity(alias = "tb_shop_channel")
public class Channel {

	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;

	/**
	 * 渠道编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	// 渠道名称
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String name;

}
