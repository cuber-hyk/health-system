package com.project.demo.service.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.demo.constant.FindConfig;
import com.project.demo.dao.UserMapper;
import com.project.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.*;

/**
 */
@Slf4j
public class BaseService<E>{

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    Class<E> eClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private final String table = humpToLine(eClass.getSimpleName());

    public Query runEntitySql(String sql){
        return entityManager.createNativeQuery(sql, eClass);
    }

    public Query runCountSql(String sql){
        return entityManager.createNativeQuery(sql);
    }

    /**
     * 插入一条记录到数据库。
     *
     * @param body 包含要插入的列名和值的映射
     */
    public void insert(Map<String,Object> body){
        // 创建一个StringBuffer对象用于构建SQL语句
        StringBuffer sql = new StringBuffer("INSERT INTO ");
        // 将表名添加到SQL语句中
        sql.append("`").append(table).append("`").append(" (");
        // 遍历传入的映射，构建插入语句的列名部分
        for (Map.Entry<String,Object> entry : body.entrySet()){
            // 将键（列名）转换为下划线形式，并用反引号括起来，然后添加到SQL语句中，后面跟一个逗号
            sql.append("`").append(humpToLine(entry.getKey())).append("`").append(",");
        }
        // 删除列名部分最后一个多余的逗号
        sql.deleteCharAt(sql.length() - 1);
        // 添加VALUES关键字和括号开始
        sql.append(") VALUES (");
        // 再次遍历传入的映射，构建插入语句的值部分
        for (Map.Entry<String,Object> entry : body.entrySet()){
            // 获取当前键对应的值
            Object value = entry.getValue();
            // 如果值是字符串类型，则在值两侧添加单引号
            if (value instanceof String){
                sql.append("'").append(entry.getValue()).append("'").append(",");
            } else {
                // 如果值不是字符串类型，则直接添加值，后面跟一个逗号
                sql.append(entry.getValue()).append(",");
            }
        }
        // 删除值部分最后一个多余的逗号
        sql.deleteCharAt(sql.length() - 1);
        // 添加括号结束
        sql.append(")");
        // 记录日志，包括表名和构建的插入SQL语句
        log.info("[{}] - 插入操作：{}", table, sql);
        // 执行原生SQL查询，获取Query对象
        Query query = runCountSql(sql.toString());
        // 执行更新操作，将数据插入数据库
        query.executeUpdate();
    }

    /**
     * 更新数据库中的记录。
     *
     * @param query 包含要更新记录的条件的映射
     * @param config 包含查询配置的映射，例如是否使用 LIKE 操作符
     * @param body 包含要更新的列名和值的映射
     */
    @Transactional // 声明事务，确保更新操作的原子性
    public void update(Map<String,String> query, Map<String,String> config, Map<String,Object> body){
        // 创建一个StringBuffer对象用于构建SQL语句
        StringBuffer sql = new StringBuffer("UPDATE ").append("`").append(table).append("`").append(" SET ");
        // 遍历传入的映射，构建更新语句的列名和值部分
        for (Map.Entry<String,Object> entry : body.entrySet()){
            // 获取当前键对应的值
            Object value = entry.getValue();
            // 如果值是字符串类型，则在值两侧添加单引号，并构建列名和值的更新语句，后面跟一个逗号
            if (value instanceof String){
                sql.append("`").append(humpToLine(entry.getKey())).append("`").append("=").append("'").append(value).append("'").append(",");
            } else {
                // 如果值不是字符串类型，则直接添加值，并构建列名和值的更新语句，后面跟一个逗号
                sql.append("`").append(humpToLine(entry.getKey())).append("`").append("=").append(value).append(",");
            }
        }
        // 删除最后一个多余的逗号
        sql.deleteCharAt(sql.length() - 1);
        // 调用toWhereSql方法构建更新语句的条件部分
        sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)), config.get(FindConfig.SQLHWERE)));
        // 记录日志，包括表名和构建的更新SQL语句
        log.info("[{}] - 更新操作：{}", table, sql);
        // 执行原生SQL查询，获取Query对象
        Query query1 = runCountSql(sql.toString());
        // 执行更新操作，更新数据库中的记录
        query1.executeUpdate();
    }

    public Map<String,Object> selectToPage(Map<String,String> query,Map<String,String> config){
        Query select = select(query, config);
        Map<String,Object> map = new HashMap<>();
        map.put("list",select.getResultList());
        map.put("count",count(query,config).getSingleResult());
        return map;
    }

    /**
     * 将分组计数的查询结果转换为列表。
     *
     * @param query 包含查询条件的映射
     * @param config 包含查询配置的映射，如分组字段
     * @return 包含查询结果的列表，每个元素是一个包含分组和计数信息的映射
     */
    public List<Map<String,Object>> selectToList(Map<String,String> query, Map<String,String> config){
        // 调用selectGroupCount方法执行查询，获取Query对象
        Query select = selectGroupCount(query, config);
        // 创建一个列表，用于存储转换后的查询结果
        List<Map<String,Object>> list = new ArrayList<>();
        // 获取查询结果集，每个结果是一个Object数组
        List<Object[]> resultList = select.getResultList();
        // 遍历结果集，将每个结果转换为映射并添加到列表中
        for (Object[] object : resultList) {
            // 创建一个映射，用于存储当前结果的分组和计数信息
            Map<String,Object> mapSub = new HashMap<>();
            // 将分组字段的值添加到映射中
            mapSub.put(config.get(FindConfig.GROUP_BY), object[1]);
            // 将计数值添加到映射中，键为"count"
            mapSub.put("count", object[0]);
            // 将计数值添加到映射中，键为"0"（这可能是为了兼容某些特定的使用场景）
            mapSub.put("0", object[0]);
            // 将分组字段的值添加到映射中，键为"1"（同样可能是为了兼容性）
            mapSub.put("1", object[1]);
            // 将映射添加到列表中
            list.add(mapSub);
        }
        // 返回包含查询结果的列表
        return list;
    }

    public Map<String,Object> selectBarGroup(Map<String,String> query,Map<String,String> config){
        Query select = barGroup(query, config);
        Map<String,Object> map = new HashMap<>();
        map.put("list",select.getResultList());
        return map;
    }

    public Query barGroup(Map<String,String> query,Map<String,String> config){
        StringBuffer sql = new StringBuffer(" SELECT ");
        if (config.get(FindConfig.GROUP_BY) != null && !"".equals(config.get(FindConfig.GROUP_BY))){
            sql.append(config.get(FindConfig.GROUP_BY));
            if (config.get(FindConfig.FIELD) != null && !"".equals(config.get(FindConfig.FIELD))){
                String[] fieldList = config.get(FindConfig.FIELD).split(",");
                for (int i=0;i<fieldList.length;i++)
                    sql.append(" ,SUM(").append(fieldList[i]).append(")");
            }
            sql.append(" FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
            sql.append(" ").append("GROUP BY ").append(config.get(FindConfig.GROUP_BY));
        }else {
            sql.append(" SUM(").append(config.get(FindConfig.GROUP_BY)).append(") FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
        }
        log.info("[{}] - 查询操作，sql: {}",table,sql);
        return runCountSql(sql.toString());
    }

    /**
     * 构建并执行一个用于分组计数的查询SQL语句。
     *
     * @param query 包含查询条件的映射
     * @param config 包含查询配置的映射，如分组字段
     * @return Query 对象，用于执行查询
     */
    public Query selectGroupCount(Map<String,String> query, Map<String,String> config){
        // 创建一个StringBuffer对象用于构建SQL语句
        StringBuffer sql = new StringBuffer("select COUNT(*) AS count_value, ");
        // 判断是否指定了分组字段，如果指定了，则添加到SQL语句中
        sql.append(config.get(FindConfig.GROUP_BY)).append(" ");
        // 添加from子句，指定要查询的表名
        sql.append("from ").append("`").append(table).append("` ");
        // 调用toWhereSql方法构建查询语句的WHERE条件部分
        sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)), config.get(FindConfig.SQLHWERE)));
        // 如果指定了分组字段，则添加group by子句
        if (config.get(FindConfig.GROUP_BY) != null && !"".equals(config.get(FindConfig.GROUP_BY))){
            sql.append("group by ").append(config.get(FindConfig.GROUP_BY)).append(" ");
        }
        // 记录日志，包括表名和构建的查询SQL语句
        log.info("[{}] - 查询操作，sql: {}", table, sql);
        // 执行查询SQL语句，返回Query对象
        return runCountSql(sql.toString());
    }

    /**
     * 构建并执行一个查询SQL语句。
     *
     * @param query 包含查询条件的映射
     * @param config 包含查询配置的映射，如字段选择、分组、排序等
     * @return Query 对象，用于执行查询
     */
    public Query select(Map<String,String> query, Map<String,String> config){
        // 创建一个StringBuffer对象用于构建SQL语句
        StringBuffer sql = new StringBuffer("select ");
        // 判断是否指定了要查询的字段，如果没有指定，则选择所有字段（*）
        sql.append(config.get(FindConfig.FIELD) == null || "".equals(config.get(FindConfig.FIELD)) ? "*" : config.get(FindConfig.FIELD)).append(" ");
        // 添加from子句，指定要查询的表名
        sql.append("from ").append("`").append(table).append("`").append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)), config.get(FindConfig.SQLHWERE)));
        // 如果指定了分组字段，则添加group by子句
        if (config.get(FindConfig.GROUP_BY) != null && !"".equals(config.get(FindConfig.GROUP_BY))){
            sql.append("group by ").append(config.get(FindConfig.GROUP_BY)).append(" ");
        }
        // 如果指定了排序规则，则添加order by子句
        if (config.get(FindConfig.ORDER_BY) != null && !"".equals(config.get(FindConfig.ORDER_BY))){
            sql.append("order by ").append(config.get(FindConfig.ORDER_BY)).append(" ");
        }
        // 如果指定了分页参数，则添加limit子句
        if (config.get(FindConfig.PAGE) != null && !"".equals(config.get(FindConfig.PAGE))){
            // 获取当前页码，默认为第1页
            int page = config.get(FindConfig.PAGE) != null && !"".equals(config.get(FindConfig.PAGE)) ? Integer.parseInt(config.get(FindConfig.PAGE)) : 1;
            // 获取每页显示的记录数，默认为10条
            int limit = config.get(FindConfig.SIZE) != null && !"".equals(config.get(FindConfig.SIZE)) ? Integer.parseInt(config.get(FindConfig.SIZE)) : 10;
            // 计算起始记录的偏移量，并添加limit子句
            sql.append(" limit ").append((page-1)*limit).append(" , ").append(limit);
        }
        // 记录日志，包括表名和构建的查询SQL语句
        log.info("[{}] - 查询操作，sql: {}", table, sql);
        // 执行查询SQL语句，返回Query对象
        return runEntitySql(sql.toString());
    }

    @Transactional
    public void delete(Map<String,String> query,Map<String,String> config){
        StringBuffer sql = new StringBuffer("DELETE FROM ").append("`").append(table).append("`").append(" ");
        sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.GROUP_BY)),config.get(FindConfig.SQLHWERE)));
        log.info("[{}] - 删除操作：{}",table,sql);
        Query query1 = runCountSql(sql.toString());
        query1.executeUpdate();
    }

    public Query count(Map<String,String> query,Map<String,String> config){
        StringBuffer sql = new StringBuffer("SELECT ");
//        log.info("拼接统计函数前");
        if (config.get(FindConfig.GROUP_BY) != null && !"".equals(config.get(FindConfig.GROUP_BY))){
            sql.append(config.get(FindConfig.GROUP_BY)).append(" ,COUNT(").append(config.get(FindConfig.GROUP_BY)).append(") FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
//            sql.append(" ").append("GROUP BY ").append(config.get(FindConfig.GROUP_BY));
        }else {
            sql.append("COUNT(*) FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
        }
        log.info("[{}] - 统计操作，sql: {}",table,sql);
        return runCountSql(sql.toString());
    }

    public Query sum(Map<String,String> query,Map<String,String> config){
        StringBuffer sql = new StringBuffer(" SELECT ");
        if (config.get(FindConfig.GROUP_BY) != null && !"".equals(config.get(FindConfig.GROUP_BY))){
            sql.append(config.get(FindConfig.GROUP_BY)).append(" ,SUM(").append(config.get(FindConfig.FIELD)).append(") FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
            sql.append(" ").append("GROUP BY ").append(config.get(FindConfig.GROUP_BY));
        }else {
            sql.append(" SUM(").append(config.get(FindConfig.FIELD)).append(") FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
        }
        log.info("[{}] - 查询操作，sql: {}",table,sql);
        return runCountSql(sql.toString());
    }

    public Query avg(Map<String,String> query,Map<String,String> config){
        StringBuffer sql = new StringBuffer(" SELECT ");
        if (config.get(FindConfig.GROUP_BY) != null && !"".equals(config.get(FindConfig.GROUP_BY))){
            sql.append(config.get(FindConfig.GROUP_BY)).append(" ,AVG(").append(config.get(FindConfig.FIELD)).append(") FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
            sql.append(" ").append("GROUP BY ").append(config.get(FindConfig.GROUP_BY));
        }else {
            sql.append(" AVG(").append(config.get(FindConfig.FIELD)).append(") FROM ").append("`").append(table).append("`");
            sql.append(toWhereSql(query, "0".equals(config.get(FindConfig.LIKE)),config.get(FindConfig.SQLHWERE)));
        }
        log.info("[{}] - 查询操作，sql: {}",table,sql);
        return runCountSql(sql.toString());
    }

    public String toWhereSql(Map<String,String> query, Boolean like,String sqlwhere) {
        if (query.size() > 0) {
            try {
                StringBuilder sql = new StringBuilder(" WHERE ");
                for (Map.Entry<String, String> entry : query.entrySet()) {
                    if (entry.getKey().contains(FindConfig.MIN_)) {
                        String min = humpToLine(entry.getKey()).replace("_min", "");
                        sql.append("`"+min+"`").append(" >= '").append(URLDecoder.decode(entry.getValue(), "UTF-8")).append("' and ");
                        continue;
                    }
                    if (entry.getKey().contains(FindConfig.MAX_)) {
                        String max = humpToLine(entry.getKey()).replace("_max", "");
                        sql.append("`"+max+"`").append(" <= '").append(URLDecoder.decode(entry.getValue(), "UTF-8")).append("' and ");
                        continue;
                    }
                    if (like == true) {
                        sql.append("`"+humpToLine(entry.getKey())+"`").append(" LIKE '%").append(URLDecoder.decode(entry.getValue(), "UTF-8")).append("%'").append(" and ");
                    } else {
                        sql.append("`"+humpToLine(entry.getKey())+"`").append(" = '").append(URLDecoder.decode(entry.getValue(), "UTF-8")).append("'").append(" and ");
                    }
                }
                if (sqlwhere!=null && !sqlwhere.trim().equals("")) {
                    sql.append(sqlwhere).append(" and ");
                }
                sql.delete(sql.length() - 4, sql.length());
                sql.append(" ");
                return sql.toString();
            } catch (UnsupportedEncodingException e) {
                log.info("拼接sql 失败：{}", e.getMessage());
            }
        }else {
            if (sqlwhere!=null && !sqlwhere.trim().equals("")) {
                StringBuilder sql = new StringBuilder(" WHERE ");
                sql.append(sqlwhere);
                return sql.toString();
            }
        }
        return "";
    }

    public Map<String,Object> readBody(BufferedReader reader){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try{
            br = reader;
            String str;
            while ((str = br.readLine()) != null){
                sb.append(str);
            }
            br.close();
            String json = sb.toString();
            return JSONObject.parseObject(json, Map.class);
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if (null != br){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Map<String,String> readQuery(HttpServletRequest request){
        String queryString = request.getQueryString();
        if (queryString != null && !"".equals(queryString)) {
            String[] querys = queryString.split("&");
            Map<String, String> map = new HashMap<>();
            for (String query : querys) {
                String[] q = query.split("=");
                map.put(q[0], q[1]);
            }
            map.remove(FindConfig.PAGE);
            map.remove(FindConfig.SIZE);
            map.remove(FindConfig.LIKE);
            map.remove(FindConfig.ORDER_BY);
            map.remove(FindConfig.FIELD);
            map.remove(FindConfig.GROUP_BY);
            map.remove(FindConfig.MAX_);
            map.remove(FindConfig.MIN_);
            map.remove(FindConfig.SQLHWERE);
            return map;
        }else {
            return new HashMap<>();
        }
    }

    public Map<String,String> readConfig(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        map.put(FindConfig.PAGE,request.getParameter(FindConfig.PAGE));
        map.put(FindConfig.SIZE,request.getParameter(FindConfig.SIZE));
        map.put(FindConfig.LIKE,request.getParameter(FindConfig.LIKE));
        map.put(FindConfig.ORDER_BY,request.getParameter(FindConfig.ORDER_BY));
        map.put(FindConfig.FIELD,request.getParameter(FindConfig.FIELD));
        map.put(FindConfig.GROUP_BY,request.getParameter(FindConfig.GROUP_BY));
        map.put(FindConfig.MAX_,request.getParameter(FindConfig.MAX_));
        map.put(FindConfig.MIN_,request.getParameter(FindConfig.MIN_));
        map.put(FindConfig.SQLHWERE,request.getParameter(FindConfig.SQLHWERE));
        return map;
    }

    public void importDb(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return;
        }
        List<Map<String,String>> body = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        if (fileName == null){
            return;
        }
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        InputStream ins = file.getInputStream();
        Workbook wb = null;
        if(suffix.equals("xlsx")){
            wb = new XSSFWorkbook(ins);
        }else{
            wb = new HSSFWorkbook(ins);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(null != sheet){
            for(int line = 0; line <= sheet.getLastRowNum();line++){
                Row row = sheet.getRow(line);
                if(null == row){
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                StringBuffer sql = new StringBuffer("INSERT INTO ").append(table).append(" VALUES (null,");
                while (cellIterator.hasNext()){
                    sql.append(cellIterator.next().getStringCellValue()).append(",");
                }
                sql.deleteCharAt(sql.length());
                sql.append(")");
                runCountSql(sql.toString());
            }
        }
    }

    public HSSFWorkbook exportDb(Map<String,String> query,Map<String,String> config){
        Query select = select(query, config);
        List<Map<String,String>> resultList = select.getResultList();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(table);
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (int i = 0; i < resultList.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            Map<String,String> map = resultList.get(i);
            int j = 0;
            for (Map.Entry<String,String> entry:map.entrySet()){
                row.createCell(j).setCellValue(new HSSFRichTextString(entry.getValue()));
            }
        }
        return workbook;
    }

    @Transactional
    public void save(E e){
        String s = JSONObject.toJSONString(e);
        Map map = JSONObject.parseObject(s, Map.class);
        insert(map);
    }

    public E findOne(Map<String, String> map){
        try {
            Query select = select(map, new HashMap<>());
            return (E) select.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


//    public String encryption(String plainText) {
//        String re_md5 = new String();
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(plainText.getBytes());
//            byte b[] = md.digest();
//
//            int i;
//
//            StringBuffer buf = new StringBuffer("");
//            for (int offset = 0; offset < b.length; offset++) {
//                i = b[offset];
//                if (i < 0)
//                    i += 256;
//                if (i < 16)
//                    buf.append("0");
//                buf.append(Integer.toHexString(i));
//            }
//
//            re_md5 = buf.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return re_md5;
//    }

    public String encryption(String plainText) {
        // 初始化一个空字符串，用于存储最终的MD5哈希值
        String re_md5 = new String();
        try {
            // 获取MD5摘要算法的 MessageDigest 实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用待加密的字符串的字节更新摘要
            md.update(plainText.getBytes());
            // 计算摘要
            byte b[] = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            // 循环处理摘要的每个字节
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset]; // 获取当前字节的值
                // 如果当前字节的值小于0（因为byte类型在Java中是有符号的），则将其转换为无符号的
                if (i < 0)
                    i += 256;
                // 如果当前字节的值小于16（即十六进制的10），则在前面补一个0
                if (i < 16)
                    buf.append("0");
                // 将当前字节的值转换为十六进制字符串，并追加到buf中
                buf.append(Integer.toHexString(i));
            }

            // 将buf转换为字符串，即为最终的MD5哈希值
            re_md5 = buf.toString();
        } catch (Exception e) {
            // 如果发生异常，打印异常信息
            e.printStackTrace();
        }
        // 返回MD5哈希值
        return re_md5;
    }


    public static String humpToLine(String str) {
        if (str == null) {
            return null;
        }
        // 将驼峰字符串转换成数组
        char[] charArray = str.toCharArray();
        StringBuilder buffer = new StringBuilder();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        String s = buffer.toString();
        if (s.startsWith("_")){
            return s.substring(1);
        }else {
            return s;
        }
    }


    public JSONObject covertObject(JSONObject object) {
        if (object == null) {
            return null;
        }
        JSONObject newObject = new JSONObject();
        Set<String> set = object.keySet();
        for (String key : set) {
            Object value = object.get(key);
            if (value instanceof JSONArray) {
                //数组
                value = covertArray(object.getJSONArray(key));
            } else if (value instanceof JSONObject) {
                //对象
                value = covertObject(object.getJSONObject(key));
            }
            //这个方法自己写的改成下划线
            key = humpToLine(key);
            newObject.put(key, value);
        }
        return newObject;
    }

    public JSONArray covertArray(JSONArray array) {
        if (array == null) {
            return null;
        }
        JSONArray newArray = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                //数组
                value = covertArray(array.getJSONArray(i));
            } else if (value instanceof JSONObject) {
                //对象
                value = covertObject(array.getJSONObject(i));
            }
            newArray.add(value);
        }
        return newArray;
    }
}
