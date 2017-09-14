/**
 * @version:  2016年4月12日 下午2:20:50
 * @author:   panda
 */
package com.appc.report.common;

/**
 * 徐州市民卡常量类
 * 
 * @version 2016年4月12日 下午2:20:50
 * @author panda
 */

public interface ReportConstants {
	/**
	 * 内部异常
	 */
	final String INTERNAL_EXCEPTION = "000001";
	/**
	 * 系统异常
	 */
	final String SYSTEM_EXCEPTION = "008888";
	/**
	 * 参数异常
	 */
	final String PARAMETER_EXCEPTION = "009999";
	/**
	 * 访问拒绝
	 */
	final String ACCESS_FORBIDDEN = "000403";

	final String SESSION_KEY = "user";

	public static Integer DATA_TYPE_CHAR = 0; // 字符型
	public static Integer DATA_TYPE_INT = 1; // 数字
	public static Integer DATA_TYPE_DATE = 2; // 日期

	public static int PER_GET = 10000; // 批量导出excel时,每次取多少数据.

}
