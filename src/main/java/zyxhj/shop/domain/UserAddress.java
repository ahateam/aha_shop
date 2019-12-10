package zyxhj.shop.domain;

import java.util.Date;

import zyxhj.utils.data.AnnDicField;
import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;



@RDSAnnEntity(alias = "tb_shop_user_address")
public class UserAddress {
	
	
	/**
	 * 所属模块编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long moduleId;
	
	/**
	 * id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	
	/**
	 * 用户id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long userId;
	
	/**
	 * 收货人姓名
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String userName;
	
	/**
	 * 收货人手机号码
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String userPhone;
	
	/**
	 * 省
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String province;
	
	/**
	 * 市
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String city;
	
	/**
	 *详细地址
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String detailed;
	
	/**
	 *是否默认
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public Boolean isDefault;
	
	/**
	 *是否有效状态
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public Byte status;
	
	/**
	 *创建时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;
	
	
	@AnnDicField(alias = "默认收货地址")
	public static final Boolean ISDEFAULT_YES = true;
	@AnnDicField(alias = "非默认")
	public static final Boolean ISDEFAULT_NO = false;
	
	@AnnDicField(alias = "有效")
	public static final Byte STATUS_YES= 0;
	@AnnDicField(alias = "失效")
	public static final Byte STATUS_NO = 1;
}
