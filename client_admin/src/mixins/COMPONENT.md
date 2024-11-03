# component.js 中的方法列表

## 组件属性方法
- **func(name, param1, param2, param3)**：回调函数(中控)。
- **query**：查询对象。
- **list**：列表数据。
- **col**：列数。
- **vm**：视图模型。
- **css**：样式字符串。
- **func_lang(keyword)**：语言转换函数。

## 数据处理方法
- **to_lang(keyword)**：语言转换。
- **save_obj(key, obj)**：保存对象到本地存储。
- **load_obj(key)**：从本地存储中查询对象。

## 事件管理方法
- **events(name, param1, param2, param3)**：事件管理，用于管理函数。

## 数据操作方法
- **add(param, func)**：添加数据。
- **del(param, func)**：删除数据。
- **del_show(o, id)**：显示删除确认。
- **set(param, query, func, includeZero)**：修改数据。
- **set_before(param, includeZero)**：修改前事件。
- **batchSet()**：批量修改。
- **get_list(param, func)**：查询多条数据。
- **get_obj(param, func)**：查询一条数据。
- **sort(param, func)**：排序。
- **init(param, func)**：初始化。
- **submit(param, func)**：提交表单。
- **upload(param, func)**：上传文件。

## 数据操作方法实现
- **add_main(value, func)**：添加数据实现。
- **del_main(query, func)**：删除数据实现。
- **del_after(json, func)**：删除后事件。
- **set_main(value, func)**：修改数据实现。
- **set_after(json, func)**：修改成功后执行。
- **get_main(query, func)**：查询数据(主程序)。
- **get_obj_check(param)**：验证请求。
- **get_obj_main(query, func)**：查询一条数据(主程序)。
- **get_obj_after(json, func)**：获取到对象后事件。
- **get_list_main(query, func)**：查询多条数据(主程序)。
- **get_list_after(res, func, url)**：获取到列表事件。
- **search(query, func)**：搜索。
- **first(query, func)**：查询多条数据 (首次)。
- **next(query, func)**：查询下一页数据。
- **prev(query, func)**：查询上一页数据。
- **clear(query)**：清除数据。
- **reset()**：重置。
- **submit_main(param, func)**：提交表单实现。
- **submit_check(param)**：提交前验证事件。
- **submit_after(json, func)**：提交后事件。
- **go(n)**：上下翻页。
- **goTo(page)**：跳转指定页。
- **toUrl(obj, url)**：转查询参数。
- **init_before(query)**：初始化前函数。
- **init_main(query)**：初始化。
- **init_after(func)**：初始化后函数。
- **upload_main(func)**：上传文件实现。
- **upload_after(json, func)**：上传完成后事件。
- **end_before(param)**：结束前。

## 选择和分页方法
- **select_all()**：选择项全改。
- **select_change(id)**：选择项改变。
- **select_has(id)**：选择项含有。
- **selected(index, obj)**：选中。
- **page_change(e)**：页面改变时。

## 导入导出方法
- **import_db(file)**：导入数据。
- **export_db()**：导出数据。

## 展开项方法
- **opens_has_sub(id, list)**：判断是否有下级。
- **opens_change(id)**：改变展开项。
- **opens_has(id)**：判断是否存在。
- **opens_lv(fid, list)**：判断子孙级别。

## 其他方法
- **selectionChange(val)**：选择项变化。
- **select_tpl_home()**：选择模板首页。
- **select_tpl_admin()**：选择模板管理。
- **uploadFile(file, key)**：上传文件。