# page.js �еķ����б�

## �¼�������
- **events(name, param1, param2, param3)**���¼��������ڹ�������

## �ص�����
- **func(name, param1, param2, param3)**���ص�����(�п�)��

## ���ݲ�������
- **add(param, func)**��������ݡ�
- **del(param, func)**��ɾ�����ݡ�
- **del_show(o, id)**����ʾɾ��ȷ�ϡ�
- **set(param, query, func, includeZero)**���޸����ݡ�
- **set_before(param, includeZero)**���޸�ǰ�¼���
- **batchSet()**�������޸ġ�
- **get_list(param, func)**����ѯ�������ݡ�
- **get_obj(param, func)**����ѯһ�����ݡ�
- **sort(param, func)**����������
- **check_auth(func)**�������Ȩ����Ȩ�޺�ص�������
- **init(param, func)**����ʼ����
- **upload(param, func)**���ϴ��¼���

## ���ݲ�������ʵ��
- **add_main(value, func)**���������ʵ�֡�
- **del_main(query, func)**��ɾ������ʵ�֡�
- **del_after(json, func)**��ɾ��֮���¼���
- **set_main(value, func)**���޸�����ʵ�֡�
- **set_after(json, func)**���޸ĳɹ�ʱִ�С�
- **get_main(query, func)**����ѯ����(������)��
- **get_obj_check(param)**����֤����
- **get_obj_main(query, func)**����ѯһ������(������)��
- **get_obj_after(json, func)**����ȡ��������¼���
- **get_list_main(query, func)**����ѯ��������(������)��
- **get_list_after(res, func, url)**����ȡ���б��¼���

## �����ͷ�ҳ����
- **search(query, func)**��������
- **first(query, func)**����ѯ�������� (�״�)��
- **next(query, func)**����ѯ��һҳ���ݡ�
- **prev(query, func)**����ѯ��һҳ���ݡ�
- **clear(query)**��������ݡ�
- **reset()**�����á�

## �ύ���ϴ�����
- **submit(param, func)**���ύ�¼���
- **submit_before(param)**���ύǰ�¼���
- **submit_check(param)**���ύǰ��֤�¼���
- **submit_main(param, func)**���ύ����
- **submit_after(json, func)**���ύ���¼���
- **upload_main(func)**���ϴ��ļ�ʵ�֡�
- **upload_after(json, func)**���ϴ���ɺ�

## ѡ��ͷ�ҳ����
- **select_all()**��ѡ����ȫ�ġ�
- **select_change(id)**��ѡ����ı䡣
- **select_has(id)**��ѡ����С�
- **selected(index)**��ѡ�С�
- **page_change(e)**��ҳ��ı�ʱ��

## ���뵼������
- **import_db(file)**���������ݡ�
- **export_db()**���������ݡ�

## չ�����
- **opens_has_sub(id, list)**���ж��Ƿ����¼���
- **opens_change(id)**���ı�չ���
- **opens_has(id)**���ж��Ƿ���ڡ�
- **opens_lv(fid, list)**���ж����Ｖ��

## ��������
- **get_name(list, arr_str, key, name, span)**����ȡ���ơ�
- **cancel()**��ȡ�������ء�
- **to_table(form, url)**����ת��
- **getter(key, url)**����ȡ���ݡ�
- **filterState(value, row)**��ɸѡ���ݡ�
- **selectionChange(val)**����ѡ���ݡ�
- **handleSizeChange(size)**�����������
- **handleCurrentChange(page)**��ҳ�������
- **uploadFile(file, key)**���ϴ��ļ���
- **delInfo()**��ɾ����Ϣ��
- **delAll(list, func)**������ɾ����