# component.js �еķ����б�

## ������Է���
- **func(name, param1, param2, param3)**���ص�����(�п�)��
- **query**����ѯ����
- **list**���б����ݡ�
- **col**��������
- **vm**����ͼģ�͡�
- **css**����ʽ�ַ�����
- **func_lang(keyword)**������ת��������

## ���ݴ�����
- **to_lang(keyword)**������ת����
- **save_obj(key, obj)**��������󵽱��ش洢��
- **load_obj(key)**���ӱ��ش洢�в�ѯ����

## �¼�������
- **events(name, param1, param2, param3)**���¼��������ڹ�������

## ���ݲ�������
- **add(param, func)**��������ݡ�
- **del(param, func)**��ɾ�����ݡ�
- **del_show(o, id)**����ʾɾ��ȷ�ϡ�
- **set(param, query, func, includeZero)**���޸����ݡ�
- **set_before(param, includeZero)**���޸�ǰ�¼���
- **batchSet()**�������޸ġ�
- **get_list(param, func)**����ѯ�������ݡ�
- **get_obj(param, func)**����ѯһ�����ݡ�
- **sort(param, func)**������
- **init(param, func)**����ʼ����
- **submit(param, func)**���ύ����
- **upload(param, func)**���ϴ��ļ���

## ���ݲ�������ʵ��
- **add_main(value, func)**���������ʵ�֡�
- **del_main(query, func)**��ɾ������ʵ�֡�
- **del_after(json, func)**��ɾ�����¼���
- **set_main(value, func)**���޸�����ʵ�֡�
- **set_after(json, func)**���޸ĳɹ���ִ�С�
- **get_main(query, func)**����ѯ����(������)��
- **get_obj_check(param)**����֤����
- **get_obj_main(query, func)**����ѯһ������(������)��
- **get_obj_after(json, func)**����ȡ��������¼���
- **get_list_main(query, func)**����ѯ��������(������)��
- **get_list_after(res, func, url)**����ȡ���б��¼���
- **search(query, func)**��������
- **first(query, func)**����ѯ�������� (�״�)��
- **next(query, func)**����ѯ��һҳ���ݡ�
- **prev(query, func)**����ѯ��һҳ���ݡ�
- **clear(query)**��������ݡ�
- **reset()**�����á�
- **submit_main(param, func)**���ύ��ʵ�֡�
- **submit_check(param)**���ύǰ��֤�¼���
- **submit_after(json, func)**���ύ���¼���
- **go(n)**�����·�ҳ��
- **goTo(page)**����תָ��ҳ��
- **toUrl(obj, url)**��ת��ѯ������
- **init_before(query)**����ʼ��ǰ������
- **init_main(query)**����ʼ����
- **init_after(func)**����ʼ��������
- **upload_main(func)**���ϴ��ļ�ʵ�֡�
- **upload_after(json, func)**���ϴ���ɺ��¼���
- **end_before(param)**������ǰ��

## ѡ��ͷ�ҳ����
- **select_all()**��ѡ����ȫ�ġ�
- **select_change(id)**��ѡ����ı䡣
- **select_has(id)**��ѡ����С�
- **selected(index, obj)**��ѡ�С�
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
- **selectionChange(val)**��ѡ����仯��
- **select_tpl_home()**��ѡ��ģ����ҳ��
- **select_tpl_admin()**��ѡ��ģ�����
- **uploadFile(file, key)**���ϴ��ļ���