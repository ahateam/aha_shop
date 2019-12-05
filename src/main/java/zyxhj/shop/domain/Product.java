package zyxhj.shop.domain;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zyxhj.utils.data.AnnDicField;
import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 商品表
 * @author JXians
 *
 */
@RDSAnnEntity(alias = "tb_shop_product")
public class Product {

	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;

	/**
	 * 所属商店编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long storeId;

	/**
	 * 商品分类编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long classId;
	
	/**
	 * 商品编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	/**
	 * 标题
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public String title;

	/**
	 * 顶部轮播图
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public JSONArray headImgs;
	
	/**
	 * 详情图片
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public JSONArray contentImgs;
	
	/**
	 * 商品描述内容
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public String data;

	/**
	 *商品规格信息编号数组
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public JSONArray skuIds;
	
	/**
	 * 状态
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte status;
	
	/**
	 * 创建时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;
	
	@AnnDicField(alias = "正在销售")
	public static final Byte STATUS_UP = 0;
	@AnnDicField(alias = "已下架")
	public static final Byte STATUS_DOWN = 1;
}
