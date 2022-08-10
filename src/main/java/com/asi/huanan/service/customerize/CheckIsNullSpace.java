package com.asi.huanan.service.customerize;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 檢查特定type的value是否為null或該型態的預設值
 * @author chewei_hu
 *	
 */
public class CheckIsNullSpace {
	/**
	 * 檢查Byte 是否為null或預設值
	 * @param value	{@code Byte} value
	 * @return {@code true}	如果 value 是 null 或 預設值(0), 否則 {@code false}
	 */
	public static Boolean isNullOrDefault(Byte value) {
		if (null == value || 0 == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Short 是否為null或預設值
	 * @param value	{@code Short} value
	 * @return {@code true}	如果 value 是 null 或 預設值(0), 否則 {@code false}
	 */
	public static Boolean isNullOrDefault(Short value) {
		if (null == value || 0 == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Integer 是否為null或預設值
	 * @param value	{@code Integer} value
	 * @return {@code true}	如果 value 是 null 或 預設值(0), 否則 {@code false}
	 */
	public static Boolean isNullOrDefault(Integer value) {
		if (null == value || 0 == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Long 是否為null或預設值
	 * @param value	{@code Long} value
	 * @return {@code true}	如果 value 是 null 或 預設值(0L), 否則 {@code false}
	 */
	public static Boolean isNullOrDefault(Long value) {
		if (null == value || 0L == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Float 是否為null或預設值
	 * @param value	{@code Float} value
	 * @return {@code true}	如果 value 是 null 或 預設值(0.0f), 否則 {@code false}
	 */
	public static Boolean isNullOrDefault(Float value) {
		if (null == value || 0.0f == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Double 是否為null或預設值
	 * @param value	{@code Double} value
	 * @return {@code true}	如果 value 是 null 或 預設值(0.0d), 否則 {@code false}
	 */
	public static Boolean isNullOrDefault(Double value) {
		if (null == value || 0.0d == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Character 是否為null或空白
	 * @param value	{@code Character} value
	 * @return {@code true}	如果 value 是 null 或 空白, 否則 {@code false}
	 */
	public static Boolean isNullOrWhitespace(Character value) {
		if (null == value || Character.isWhitespace(value)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查String 是否為null或空白
	 * @param value	{@code String} value
	 * @return {@code true}	如果 value 是 null 或 空白, 否則 {@code false}
	 */
	public static Boolean isNullOrBlank(String value) {
		if (null == value || value.strip().isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Collection 是否為空或null<br>
	 * Collection包含List, Set
	 * @param value	{@code Collection} value
	 * @return {@code true}	如果 value 是 null 或 空, 否則 {@code false}
	 */
	public static <E> Boolean isNullOrEmpty(Collection<E>  value) {
		if (null == value || value.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查Map 是否為空或null
	 * @param value	{@code Map} value
	 * @return {@code true}	如果 value 是 null 或 空, 否則 {@code false}
	 */
	public static <K, V> Boolean isNullOrEmpty(Map<K, V>  value) {
		if (null == value || value.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查 BigDecimal 是否為null或預設值
	 * @param value	{@code BigDecimal} value
	 * @return {@code true}	如果 value 是 null , 否則 {@code false}
	 */
	public static Boolean isNull(BigDecimal value) {
		if (null == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * 檢查 Date 是否為null 
	 * @param value	{@code Date} value
	 * @return {@code true}	如果 value 是 null, 否則 {@code false}
	 */
	public static Boolean isNull(Date value) {
		if (null == value) {
			return true;
		}
		return false;
	}
	
	/**
	 * (未完成註解)針對自定義bean確認是否為空(自定義集合不適用)
	 * 目前待處理object轉陣列或集合或map的問題
	 * @param <T>
	 * @param value
	 * @return
	 * @see java.lang.reflect.Field
	 * @see java.lang.reflect.Type
	 * @deprecated
	 */
//    public static <T> Boolean checkIsNullSpace(T value){
//        Class<? extends Object> valueClass = value.getClass(); 						// 得到類物件
//        Field fields[] = valueClass.getDeclaredFields(); 	// 得到所有屬性
//        boolean flag = true; 					//定義返回結果，預設為true
//        for(Field field : fields){
//            field.setAccessible(true);
//            Object fieldValue = null;
//            String fieldTypeName = null;
//            Class<?> fieldClass = null;
//            try {
//                fieldValue = field.get(value); 									//得到屬性值
//                fieldTypeName =field.getGenericType().toString();	//得到屬性型別
//                fieldClass = field.getType();
//                System.out.println(field.getType());
//                System.out.println(field.getName());
//                System.out.println(fieldValue);
//                System.out.println(fieldTypeName);
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            //只要有一個屬性值不為null 就返回false 表示物件不為null
//            if (fieldTypeName.equals("int") || fieldTypeName.equals("class java.lang.Integer") ||
//            		fieldTypeName.equals("byte") || fieldTypeName.equals("class java.lang.Byte") ||
//            		fieldTypeName.equals("short") || fieldTypeName.equals("class java.lang.Short")) {
//            	if (checkIsNullSpace(Integer.parseInt(String.valueOf(fieldValue))) == false) {
//            		flag = false;
//            		break;
//				}
//			} else if (fieldTypeName.equals("class java.lang.String")) {
//            	if (checkIsNullSpace(String.valueOf(fieldValue)) == false) {
//            		flag = false;
//            		break;
//				}
//			} else if (fieldTypeName.equals("double") || fieldTypeName.equals("class java.lang.Double")) {
//            	if (checkIsNullSpace(Double.parseDouble(String.valueOf(fieldValue))) == false) {
//            		flag = false;
//  	                break;
//				}
//			} else if (fieldTypeName.equals("float") || fieldTypeName.equals("class java.lang.Float")) {
//            	if (checkIsNullSpace(Float.parseFloat(String.valueOf(fieldValue))) == false) {
//            		flag = false;
//            		break;
//				}
//			} else if (fieldTypeName.equals("long") || fieldTypeName.equals("class java.lang.Long")) {
//            	if (checkIsNullSpace(Float.parseFloat(String.valueOf(fieldValue))) == false) {
//            		flag = false;
//            		break;
//				}
//			} else if (fieldTypeName.equals("char") || fieldTypeName.equals("class java.lang.Character")) {
//            	if (checkIsNullSpace(String.valueOf(fieldValue).charAt(0)) == false) {
//            		flag = false;
//            		break;
//				}
//			} else if (fieldClass.isArray() || Collection.class.isAssignableFrom(fieldClass)) {
//            	if (checkIsNullSpace(Arrays.asList(fieldValue)) == false) {
//            		flag = false;
//            		break;
//				}
//			} else if (Collection.class.isAssignableFrom(fieldClass)) {
//            	if (checkIsNullSpace(Arrays.asList(fieldValue)) == false) {
//            		flag = false;
//            		break;
//				}
//			} else if (Map.class.isAssignableFrom(fieldClass)) {
//				
//			}
//        }
//        return flag;
//    }
}
