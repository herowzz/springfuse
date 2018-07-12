package com.github.herowzz.springfuse.data.domain.support;

import java.io.Serializable;

import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;

public class RevisionEntityListener implements EntityTrackingRevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&]");
		System.out.println(revisionEntity);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&]");
	}

	@Override
	public void entityChanged(@SuppressWarnings("rawtypes") Class entityClass, String entityName, Serializable entityId, RevisionType revisionType, Object revisionEntity) {
		System.out.println("*************************");
		System.out.println(entityClass);
		System.out.println(entityName);
		System.out.println(entityId);
		System.out.println(revisionType);
		System.out.println(revisionEntity);
		System.out.println("*************************");
	}
}