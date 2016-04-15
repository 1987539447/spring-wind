/**
 * Copyright (c) 2011-2014, L.cm (596392912@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.framework.common;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * <p>
 * ����CGlib����չBeanUtils�����ڸ������͵�CGlib��������
 * </p>
 * @author L.cm
 * @date 2016-03-24
 */
public final class BeanUtils extends org.springframework.beans.BeanUtils {
	private BeanUtils(){}

	/**
	 * ʵ��������
	 * @param clazz ��
	 * @return ����
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz) {
		try {
			return (T) clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ʵ��������
	 * @param clazzStr ����
	 * @return {T}
	 */
	public static <T> T newInstance(String clazzStr) {
		try {
			Class<?> clazz = Class.forName(clazzStr);
			return newInstance(clazz);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * copy �������Ե���һ������Ĭ�ϲ�ʹ��Convert
	 * @param src
	 * @param clazz ����
	 * @return {T}
	 */
	public static <T> T copy(Object src, Class<T> clazz) {
		BeanCopier copier = BeanCopier.create(src.getClass(), clazz, false);

		T to = newInstance(clazz);
		copier.copy(src, to, null);
		return to;
	}

	/**
	 * ��������
	 * @param src Դ����
	 * @param dist ��Ҫ��ֵ�Ķ���
	 */
	public static void copy(Object src, Object dist) {
		BeanCopier copier = BeanCopier
				.create(src.getClass(), dist.getClass(), false);

		copier.copy(src, dist, null);
	}

	/**
	 * ������װ��map��ʽ
	 * ע�⣺���ɵ���unmodifiableMap
	 * @param src Դ����
	 * @return {Map<K, V>}
	 */
	@SuppressWarnings("rawtypes")
	public static <K, V> Map<K, V> toMap(Object src) {
		return BeanMap.create(src);
	}
}
