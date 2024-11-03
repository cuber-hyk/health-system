# jQuery 扩展对象分析

该文件中扩展了 jQuery 的 `$` 对象，添加了多个自定义函数和属性，以增强其功能。以下是文件中添加到 `$` 对象的主要功能和组件的分析：

## 基础函数
- **sleep(milliSeconds, obj, key)**：延迟执行函数，可以暂停代码的执行。
- **as(obj, query, all)**：判断两个对象是否相似。
- **speeds(func, times)**：测试函数执行速度。
- **push(objA, objB, bl)**：为对象添加属性。
- **clear(obj)**：清空对象值。
- **toJson(obj, format)**：将对象转换为 JSON 字符串。
- **toUrl(obj, url)**：将对象转换为 URL 参数字符串。
- **copy(obj, has)**：复制一个新对象。
- **keys(obj, file)**：查看对象的所有属性。
- **delete_prop(obj, includeZero)**：删除对象的空属性。

## 命名空间
- **funcs**：一个命名空间，包含数据连接池、任务池、API接口、全局变量、字典、语言包等。

## MD5 加密类
- **hex_md5(s)**：将字符串 `s` 进行 MD5 加密，并返回十六进制格式的结果。
- **b64_md5(s)**：将字符串 `s` 进行 MD5 加密，并返回 Base64 格式的结果。
- **str_md5(s)**：将字符串 `s` 进行 MD5 加密，并返回字符串格式的结果。
- **hex_hmac_md5(key, data)**：使用 HMAC-MD5 算法对 `key` 和 `data` 进行加密，并返回十六进制格式的结果。
- **b64_hmac_md5(key, data)**：使用 HMAC-MD5 算法对 `key` 和 `data` 进行加密，并返回 Base64 格式的结果。
- **str_hmac_md5(key, data)**：使用 HMAC-MD5 算法对 `key` 和 `data` 进行加密，并返回字符串格式的结果。

## 定时任务管理器
- **Timer**：一个定时任务管理器，可以添加、删除和执行定时任务。

## 网络请求
- **http.get(url, fun, headers)**：发送 GET 请求。
- **http.post(url, param, fun, headers, type)**：发送 POST 请求。
- **http.upload(url, file, fun, headers)**：上传文件。
- **cookies.set(name, value, minutes, domain)**：设置 cookie。
- **cookies.get(name)**：获取 cookie。
- **cookies.del(name)**：删除 cookie。

## 多媒体
- **file.upload(url, obj, func, headers)**：文件上传功能。

## 通讯
- **html.tag(tag, prop, value)**：根据标签名、属性和属性值获取 HTML 元素。
- **cache.set(key, value)**：设置本地临时缓存。
- **cache.get(key)**：获取本地临时缓存。
- **cache.del(key)**：删除本地临时缓存。
- **cache.clear()**：清除所有本地临时缓存。
- **db.set(key, value, longTime)**：设置本地数据库存储。
- **db.get(key)**：获取本地数据库存储。
- **db.del(key)**：删除本地数据库存储。

## 路由和浏览器检测
- **route.push(url, title)**：添加路由。
- **os**：浏览器和操作系统信息检测。

## 对象操作
- **add(obj, query, value)**：添加对象属性。
- **del(obj, query, item)**：删除对象属性。
- **set(obj, query, value)**：设置对象属性值。
- **get(obj, query)**：获取对象属性值。
- **obj_for(obj, key, value)**：遍历读写对象。

## 跨站校验
- **ifame_check()**：检查是否存在跨域嵌套 iframe 的问题。