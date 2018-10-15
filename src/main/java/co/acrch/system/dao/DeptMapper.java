package co.acrch.system.dao;

import java.util.List;

import co.acrch.common.config.MyMapper;
import co.acrch.system.domain.Dept;

public interface DeptMapper extends MyMapper<Dept> {
	
	// 删除父节点，子节点变成顶级节点（根据实际业务调整）
	void changeToTop(List<String> deptIds);
}