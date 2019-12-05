package zyxhj.shop.repository;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.shop.domain.Order;
import zyxhj.utils.api.ServerException;
import zyxhj.utils.data.rds.RDSRepository;

public class OrderRepository  extends RDSRepository<Order>{

	public  OrderRepository() {
		super(Order.class);
	}

	public int getOrderCount(DruidPooledConnection conn, Long id) throws ServerException {
		String sql = "select count(*) from tb_shop_order where product_id = "+id.toString();
		Object[] s = this.sqlGetObjects(conn, sql, null);
		return Integer.parseInt(s[0].toString());
	}
}
