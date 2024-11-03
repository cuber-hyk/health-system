# component.js 中的函数列表

## Vue 插件安装函数
- **install(Vue, options)**：安装插件，注册组件和全局方法。

## 组件注册
- **page_header**：页面头部组件。
- **page_footer**：页面底部组件。
- **page_side**：页面侧边栏组件。 
- **div_video**：视频组件。
- **div_audio**：音频组件。

## 全局方法
- **$changeTime(o)**：改变时间格式。
- **$get_rand(len)**：生成指定长度的随机数。
- **$get_stamp()**：获取当前时间戳。
- **$download(url, name)**：下载文件。
- **$get_user(func)**：获取用户信息。
- **$redirect(url)**：跳转地址。
- **$toast(text, type)**：显示提示框。
- **$toTime(time, format)**：转换时间格式。
- **$filter(arr, key, value)**：过滤数组。
- **$toUrl(obj, url)**：转换对象为 URL 参数字符串。
- **$toName(list, value, value_key, key)**：根据值转换名称。
- **$fullUrl(url)**：补全请求 URL。
- **$get(url, body, func)**：发送 GET 请求。
- **$post(url, body, func)**：发送 POST 请求。
- **$upload(url, body, func)**：上传文件。
- **$put(url, body, func)**：发送 PUT 请求。
- **$get_auth(user_group, func)**：获取用户组权限。
- **$check_action(path, action)**：检查路径对应操作权限。
- **$get_power(path)**：获取权限。
- **$check_register_field(action, field, path)**：注册时检查字段权限。
- **$check_field(action, field)**：检查字段权限。
- **$check_exam(path, action)**：检查审核权限。
- **$check_examine()**：检查是否有审核字段权限。
- **$check_pay()**：检查是否有支付字段权限。
- **$check_figure(path)**：检查是否有统计字段权限。
- **$check_comment(path)**：检查是否可以显示评论。
- **$check_option(path, op)**：检查其他选项权限。
- **$page_title(path)**：获取页面标题。
- **$check_group(user_group)**：检查用户组是否有权限。
- **$sortChange(sort)**：按目标排序。
- **$is_phone(rule, value, callback)**：验证手机号。
- **$is_tel(rule, value, callback)**：验证电话。
- **$is_email(rule, value, callback)**：验证邮箱。
- **$is_url(rule, value, callback)**：验证网址。