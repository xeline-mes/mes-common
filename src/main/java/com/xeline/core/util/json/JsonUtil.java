package com.xeline.core.util.json;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

public class JsonUtil {

  /**
   * simple type object convert to json string
   * (not contain collection,
   * this type please use [collectionToJson] method)
   *
   * @param obj
   * @return
   */
  public static String objectToJson(Object obj) {
    return Json.toJson(obj, JsonFormat.compact());
    // return JSON.toJSONString(obj);
    // return JSONObject.fromObject(obj).toString();
  }

  /**
   * collection type convert to json
   *
   * @param collection
   * @return
   */
  public static String collectionToJson(Collection collection) {

    return Json.toJson(collection);
  }

  /**
   * json convert to bean
   *
   * @param json
   * @param beanClass
   * @return
   */
  public static Object jsonToObject(String json, Class beanClass) {
    return Json.fromJson(beanClass, json);
    // return JSON.parseObject(json, beanClass);
    // JSONObject jsonObject = JSONObject.fromObject(json);
    // return JSONObject.toBean(jsonObject, beanClass);
  }

  /**
   * json string convert to Collection type object(eg. List)
   *
   * @param jsonString
   * @param beanClass
   * @return
   */
  public static List jsonToCollection(String jsonString, Class beanClass) {

    long start = System.currentTimeMillis();

    // List list = Json.fromJsonAsList(beanClass, jsonString);
    //
    //// List list = JSONArray.parseArray(jsonString, beanClass);
    //// Collection coll = JSONArray.toCollection(arr, beanClass);
    // long end = System.currentTimeMillis();
    //
    // long processTime = end-start;
    // logger.debug("Chint Debug[JSONUtil.jsonToCollection] --> process time(ms): " + processTime);
    // return list;
    return null;
  }

  public static void toJson(String result) throws Exception {

    // 设置客户端浏览器输出
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json; charset=utf-8");

    // 取消浏览器缓存
    response.setHeader("Cache-Control", "no-cache");

    PrintWriter out = response.getWriter();
    out.write(result);
    out.flush();
    out.close();
  }

  public static void main(String[] args) {
    Map map = new HashMap();
    map.put("result", "1");
    String json = JsonUtil.objectToJson(map);

    System.out.println(json);
    Object obj = JsonUtil.jsonToObject(json, Map.class);
    Map m = (Map) obj;
    System.out.println(m.get("result"));
  }

}
