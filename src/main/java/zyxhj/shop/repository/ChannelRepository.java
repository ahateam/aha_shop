package zyxhj.shop.repository;

import zyxhj.shop.domain.Channel;
import zyxhj.utils.data.rds.RDSRepository;

public class ChannelRepository extends RDSRepository<Channel>{

	public  ChannelRepository() {
		super(Channel.class);
	}
}
