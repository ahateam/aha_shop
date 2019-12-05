package zyxhj.shop.domain;

import java.util.Date;

import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 热门商品
 * @author JXians
 *
 */
public class HotProduct {
	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;
	
	/**
	 * 商品编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long productId;
	
	/**
	 * 热门编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	
	/**
	 * 热门商品状态
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte status;
	
	/**
	 * 设置热门开始时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date startTime;
	
	/**
	 * 设置热门结束时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date endTime;
	
	/**
	 * 设置热门时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;

	/**
	 * 修改热门时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date updateTime;
	
	
}
