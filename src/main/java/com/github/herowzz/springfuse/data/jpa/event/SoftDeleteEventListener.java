package com.github.herowzz.springfuse.data.jpa.event;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.annotations.Where;
import org.hibernate.event.internal.DefaultDeleteEventListener;
import org.hibernate.event.spi.DeleteEvent;

import com.github.herowzz.springfuse.data.domain.BaseEntity;

/**
 * 逻辑删除监听器<br>
 * 若需要删除的实体继承BaseEntity,则拦截delete删除,转换为update更新实体删除状态(isDeleted)
 * @author wangzz
 */
public class SoftDeleteEventListener extends DefaultDeleteEventListener {

	private static final long serialVersionUID = -3927250364542822753L;

	@SuppressWarnings("rawtypes")
	@Override
	public void onDelete(DeleteEvent event, Set transientEntities) throws HibernateException {
		if (event.getObject() instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) event.getObject();
			if (entity.getClass().isAnnotationPresent(Where.class)) {
				entity.onRemove();
				event.getSession().persist(entity);
				event.getSession().update(entity);
			}
		} else {
			super.onDelete(event, transientEntities);
		}
	}
}
