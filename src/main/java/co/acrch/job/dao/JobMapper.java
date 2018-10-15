package co.acrch.job.dao;

import java.util.List;

import co.acrch.common.config.MyMapper;
import co.acrch.job.domain.Job;
import co.acrch.common.config.MyMapper;
import co.acrch.job.domain.Job;

public interface JobMapper extends MyMapper<Job> {
	
	List<Job> queryList();
}