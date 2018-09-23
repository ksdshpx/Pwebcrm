package cn.ksdshpx.pwebcrm.dao;
/**
  * 持久层
 * @author peng.x
 * @date 2018年9月23日 上午11:15:07
 */

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

public class CustomerDao {
	private QueryRunner qr = new TxQueryRunner();
}
