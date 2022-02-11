package com.zhidao.kstb.dao;

import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX;
import com.zhidao.kstb.model.tab.KSTB_CLXX;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 跨省通办_户口迁移 持久层数据访问对象(接口)
 * 
 * @author <致道科技>YunTong
 *<p>Created on 2021-8-2<p>
 */
@Mapper
@Repository
public interface HouseholdMigrationDao {
	
	/**
	 * 批量保存申请迁出信息 
	 * 
	 * @author <致道科技>YunTong
	 * @param kstbSqqcxx 申请迁出信息
	 * @return 批量插入成功返回true，否则返回false
	 * <p>Created on 2021-8-2<p>
	 */
	boolean InsSQQCXX(KSTB_SQQCXX kstbSqqcxx);
	
	/**
	 * 添加(申请迁出)材料信息
	 * 
	 * @author <致道科技>YunTong
	 * @param kstbClxx 材料信息
	 * @return 插入成功返回true,否则返回false。
	 * <p>Created on 2021-8-2<p>
	 */
	boolean InsCLXX(KSTB_CLXX kstbClxx);
	
	/**
	 * 添加迁出反馈信息
	 * 
	 * @author <致道科技>YunTong
	 * @param kstbQcfkxx 迁出反馈信息
	 * @return 插入成功返回true,否则返回false。
	 * <p>Created on 2021-8-2<p>
	 */
	boolean InsQCFKXX(KSTB_QCFKXX kstbQcfkxx);
	
	/**
	 * 添加不予迁出反馈信息
	 * 
	 * @author <致道科技>YunTong
	 * @param kstbByqcfkxx 不予迁出反馈信息
	 * @return 插入成功返回true,否则返回false。
	 * <p>Created on 2021-8-2<p>
	 */
	boolean InsBYQCFKXX(KSTB_BYQCFKXX kstbByqcfkxx);
}
