package co.acrch.job.service;

import java.util.List;

import co.acrch.common.service.IService;
import co.acrch.job.domain.JobLog;
import co.acrch.common.service.IService;

public interface JobLogService extends IService<JobLog> {

	List<JobLog> findAllJobLogs(JobLog jobLog);

	void saveJobLog(JobLog log);
	
	void deleteBatch(String jobLogIds);
}
