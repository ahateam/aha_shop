package zyxhj.shop.domain;

import java.util.Date;

import zyxhj.utils.data.AnnDicField;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 商品分类
 * @author JXians 
 *
 */
public class ProductClassification {

	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;
	
	/**
	 * 商品分类编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	
	/**
	 * 父级商品分类编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long parentId;
	
	/**
	 * 商品分类名称
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String name;
	
	/**
	 * 商品分类状态
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte status;
	
	/**
	 * 创建时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;
	
	/**
	 * 修改时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date updateTime;
	
}
