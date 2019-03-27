package com.github.herowzz.springfuse.data.jpa.generator;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.github.herowzz.springfuse.core.util.CodeUtils;

/***
 * 日期Id生成方式<br>
 * 生成yyMMddHHmmssSSS的id
 * 
 * @author wangzz
 *
 */
public class DateTimeIdGenerator implements IdentifierGenerator, Configurable {

	public static final String PREFIX_PARAM = "prefix";
	public static final String FORMAT_PARAM = "format";

	private String prefix = "";
	private String format = "yyMMddHHmmssSSS";

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		String prefixParam = (String) params.get(PREFIX_PARAM);
		String formatParam = (String) params.get(FORMAT_PARAM);
		if (StringUtils.isNotBlank(formatParam)) {
			format = formatParam;
		}
		if (StringUtils.isNotBlank(prefixParam)) {
			prefix = prefixParam;
		}
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return CodeUtils.getNowDateStr(prefix, format);
	}

}
