(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d00dd5ea"],{"0ff7":function(e,t,i){"use strict";var s=function(){var e=this,t=e._self._c;return t("div",{staticClass:"list_comment"},e._l(e.list,(function(i,s){return t("div",{key:s,staticClass:"item_comment_box"},[t("div",{staticClass:"comment"},[t("div",{staticClass:"left_block"},[t("img",{directives:[{name:"default-img",rawName:"v-default-img",value:"/img/default.png",expression:"'/img/default.png'"}],staticStyle:{width:"4rem",height:"4rem"},attrs:{src:e.$fullUrl(i[e.vm.avatar])}})]),t("div",{staticClass:"right_block"},[t("div",{staticClass:"top_comment"},[t("div",{staticClass:"nickname"},[e._v(e._s(i[e.vm.nickname]))]),t("div",{staticClass:"time_block"},[t("div",{staticClass:"time"},[e._v(" "+e._s(e._f("formatDate")(i[e.vm.create_time]))+" ")])])]),t("div",{staticClass:"content",domProps:{innerHTML:e._s(i[e.vm.content])}}),Object.keys(e.obj).length?t("div",{staticClass:"comment_reply"},[t("b-button",{attrs:{variant:"outline"},on:{click:function(t){return e.reply_to_sb(i)}}},[e._v("回复")])],1):e._e()])]),i.list_reply?t("div",{staticClass:"list_reply ml-5"},e._l(i.list_reply,(function(i,s){return t("div",{key:s,staticClass:"mb"},[t("div",{staticClass:"fl"},[t("span",{staticClass:"nickname"},[e._v(e._s(i[e.vm.nickname]))])]),t("div",{staticClass:"fr"},[t("span",{staticClass:"time"},[e._v(e._s(e._f("formatDate")(i[e.vm.create_time])))])]),t("div",{staticClass:"ml-5",domProps:{innerHTML:e._s(i[e.vm.content])}})])})),0):e._e()])})),0)},r=[],o=i("c6e7"),a={mixins:[o["a"]],props:{list:{type:Array,default:function(){return[]}},obj:{type:Object,default:function(){return{}}},vm:{type:Object,default:function(){return{avatar:"avatar",nickname:"nickname",content:"content",create_time:"create_time"}}}},data(){return{text:""}},methods:{reply_to_sb(e){this.obj.reply_to_id=e.comment_id}},components:{}},n=a,l=(i("5ca9"),i("2877")),c=Object(l["a"])(n,s,r,!1,null,"1e098a4e",null);t["a"]=c.exports},1512:function(e,t,i){"use strict";i.r(t);var s=function(){var e=this,t=e._self._c;return t("div",{staticClass:"diy_details page_recipe_information",attrs:{id:"recipe_information_details"}},[t("div",{staticClass:"warp"},[t("div",{staticClass:"container"},[t("div",{staticClass:"row"},[t("div",{staticClass:"col"},[t("div",{staticClass:"card_recipe_information"},[t("div_recipe_information",{attrs:{obj:e.obj}})],1)])])])]),e.$check_action("/comment/list","get")?t("div",{staticClass:"warp"},[t("div",{staticClass:"container"},[t("div",{staticClass:"row"},[t("div",{staticClass:"col"},[e.list_comment.length?t("bar_title",{staticClass:"mt",attrs:{title:"评论区"}}):e._e(),e.list_comment.length?t("div",{staticClass:"comment_box"},[t("list_comment",{staticClass:"w-100",attrs:{list:e.list_comment,obj:e.form_comment}}),t("b-pagination-nav",{attrs:{"base-url":e.$route.path,"number-of-pages":e.count_pages,"link-gen":e.link_gen,"first-number":"","last-number":""},on:{change:e.change_page}})],1):e._e(),t("div",{staticClass:"form_editor form_edit_box"},[t("div",{staticClass:"details_form_editor_title"},[e._v("发表评论")]),t("div",{directives:[{name:"show",rawName:"v-show",value:e.form_comment.reply_to_id,expression:"form_comment.reply_to_id"}],staticClass:"fl ml-3 answer_title"},[e._v(" 对 "),t("i",[e._v(e._s(e.respondent))]),e._v(" 回复: ")]),t("div",{directives:[{name:"show",rawName:"v-show",value:e.form_comment.reply_to_id,expression:"form_comment.reply_to_id"}],staticClass:"fr mr-3"},[t("b-button",{attrs:{variant:"outline-primary reset_answer_personnel",size:"sm"},on:{click:function(t){e.form_comment.reply_to_id=0}}},[e._v("重置回复人 ")])],1),t("form_editor",{staticClass:"fn",attrs:{form:e.form_comment},on:{update_comment:e.update_comment}})],1)],1)])])]):e._e()])},r=[],o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"diy_details_box diy_div_recipe_information"},[t("div",{staticClass:"warp"},[t("div",{staticClass:"container"},[t("div",{staticClass:"details_title"},[e._v("详情")]),t("div",{staticClass:"row"},[t("div",{staticClass:"row_top_wrap"},[e.imgList.length?t("div",{staticClass:"row_1"},e._l(e.imgList,(function(i,s){return t("div",{key:i+s,staticClass:"diy_img"},[t("img",{attrs:{src:e.$fullUrl(e.obj[i.name])}})])})),0):e._e(),t("div",{staticClass:"row_2",class:{flex_row2:!e.imgList.length}},e._l(e.itemList,(function(i,s){return t("div",{directives:[{name:"show",rawName:"v-show",value:e.$check_field("get",i.name),expression:"$check_field('get',item.name)"}],key:i+s,class:{flex_row2_item:!e.imgList.length}},[t("div",{staticClass:"view"},[t("div",{staticClass:"diy_title"},[t("span",[e._v(e._s(i.title)+":")])]),"UID"==i.type?t("div",{staticClass:"diy_field diy_uid",class:{flex_row2_item_text:e.imgList.length}},[t("span",[e._v(" "+e._s(e.get_user_info(i.name,e.obj[i.name]))+" ")])]):"视频"==i.type?t("div",{staticClass:"diy_field diy_video",class:{flex_row2_item_text:e.imgList.length}},[e.obj[i.name]?t("router-link",{attrs:{to:"/media/video?filename="+e.$fullUrl(e.obj[i.name])}},[t("span",[e._v(" 查看视频 ")])]):e._e()],1):"音频"==i.type?t("div",{staticClass:"diy_field diy_music",class:{flex_row2_item_text:e.imgList.length}},[e.obj[i.name]?t("audio",{staticStyle:{"text-align":"left"},attrs:{src:e.$fullUrl(e.obj[i.name]),controls:""}}):e._e()]):"文件"==i.type?t("div",{staticClass:"diy_field diy_music",class:{flex_row2_item_text:e.imgList.length}},[t("a",{staticStyle:{color:"rgb(64, 158, 255)"},attrs:{href:e.$fullUrl(e.obj[i.name]),target:"_blank"}},[e._v("点击下载")])]):"日期"==i.type?t("div",{staticClass:"diy_field diy_date",class:{flex_row2_item_text:e.imgList.length}},[t("span",[e._v(" "+e._s(e.$toTime(e.obj[i.name],"yyyy-MM-dd"))+" ")])]):"时间"==i.type?t("div",{staticClass:"diy_field diy_time",class:{flex_row2_item_text:e.imgList.length}},[t("span",[e._v(" "+e._s(e.$toTime(e.obj[i.name],"hh:mm:ss"))+" ")])]):"日长"==i.type?t("div",{staticClass:"diy_field diy_datetime",class:{flex_row2_item_text:e.imgList.length}},[t("span",[e._v(" "+e._s(e.$toTime(e.obj[i.name],"yyyy-MM-dd hh:mm:ss"))+" ")])]):"电话"==i.type||"手机"==i.type?t("div",{staticClass:"diy_field diy_phone",class:{flex_row2_item_text:e.imgList.length}},[t("span",[e._v(" "+e._s(e.obj[i.name])+" ")])]):"数字"==i.type?t("div",{staticClass:"diy_field diy_number",class:{flex_row2_item_text:e.imgList.length}},[t("span",[e._v(" "+e._s(e.obj[i.name])+" ")])]):"网址"==i.type?t("div",{staticClass:"diy_field diy_url",class:{flex_row2_item_text:e.imgList.length}},[t("div",[t("a",{staticStyle:{color:"rgb(64, 158, 255)"},attrs:{href:e.obj[i.name],target:"_blank"}},[e._v(e._s(e.obj[i.name]))])])]):"多文本"==i.type?t("div",{staticClass:"diy_field diy_desc",class:{flex_row2_item_text:e.imgList.length}},[t("div",[e._v(" "+e._s(e.obj[i.name])+" ")])]):t("div",{staticClass:"diy_field diy_text",class:{flex_row2_item_text:e.imgList.length}},[t("span",[e._v(" "+e._s(e.obj[i.name])+" ")])])])])})),0)]),t("div",{staticClass:"view praise_wrap"},[t("span",{staticClass:"diy_title"},[e._v("点赞数：")]),e._v(" "),t("span",{staticClass:"diy_field diy_text"},[e._v(" "+e._s(e.praiseLen)+" ")])]),t("div",{staticClass:"details_btn_wrap"},[e.$check_action("/recipe_information/details","set")?t("button",{staticClass:"btn btn-primary details_btn",on:{click:function(t){return e.change_praise()}}},[e._v("点赞")]):e._e(),e.$check_action("/recipe_information/details","set")?t("button",{staticClass:"btn btn-primary details_btn",on:{click:function(t){return e.change_collect()}}},[e._v("收藏")]):e._e()]),e._l(e.richList,(function(i,s){return t("div",{directives:[{name:"show",rawName:"v-show",value:e.$check_field("get",i.name),expression:"$check_field('get',item.name)"}],key:i+s,staticClass:"rich_text"},[t("div",{staticClass:"view"},[t("div",{staticClass:"diy_title"},[t("span",{domProps:{textContent:e._s(i.title)}})]),t("div",{staticClass:"diy_html",domProps:{innerHTML:e._s(e.obj[i.name])}})])])}))],2)])])])},a=[],n=i("6265"),l=i("c6e7"),c={mixins:[n["a"],l["a"]],components:{},props:{obj:{type:Object,default:function(){return{}}}},data(){return{praiseLen:this.obj["praise_len"],imgList:[{title:"封面",name:"cover",type:"图片"}],itemList:[{title:"食谱名称",name:"recipe_name",type:"文本"},{title:"食谱分类",name:"recipe_classification",type:"下寻"},{title:"食用禁忌",name:"taboo",type:"文本"},{title:"能量",name:"energy",type:"文本"},{title:"适用人群",name:"intended_for",type:"文本"},{title:"制作视频",name:"make_video",type:"视频"}],richList:[{title:"食谱介绍",name:"recipe_introduction",type:"编辑"}]}},methods:{get_user_info(e,t){var i=null,s="";return i&&(s=i.nickname+"-"+i.username),s},change_praise(e){var t=this.user.user_id,i={source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj["recipe_information_id"],user_id:t},s=this;this.state_praise?(this.state_praise=!1,this.$get("~/api/praise/del?",i,e=>{if(e.result){s.praiseLen=parseInt(s.praiseLen)-1;var t=s.praiseLen;this.$post("~/api/recipe_information/set?recipe_information_id="+s.obj["recipe_information_id"],{praise_len:t},e=>{e.result?console.log("添加点赞数状态：",e.result):e.error&&console.error(e.error)}),this.$message.success("取消点赞")}else e.error&&(this.$toast(e.error.message),console.error(e.error))})):(this.state_praise=!0,this.$post("~/api/praise/add?",i,e=>{if(e.result){s.praiseLen=parseInt(s.praiseLen)+1;var t=s.praiseLen;this.$post("~/api/recipe_information/set?recipe_information_id="+s.obj["recipe_information_id"],{praise_len:t},e=>{e.result?console.log("添加点赞数状态：",e.result):e.error&&console.error(e.error)}),this.$message.success("点赞成功")}else e.error&&(this.$toast(e.error.message),console.error(e.error))}))},get_praise(){var e=this.$store.state.user.user_id;this.$get("~/api/praise/count?",{source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj.recipe_information_id,user_id:e},e=>{e.result||0===e.result?(console.log("sadsfasf"+JSON.stringify(e)),this.state_praise=!!e.result,console.log("点赞状态：",this.state_praise)):e.error&&(this.$toast(e.error.message),console.error(e.error))})},change_collect(){var e=this.user.user_id,t={source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj["recipe_information_id"],user_id:e};this.state_collect?(this.state_collect=!1,this.$get("~/api/collect/del?",t,e=>{e.result?this.$message.success("取消收藏"):e.error&&(this.$toast(e.error.message),console.error(e.error))})):(this.state_collect=!0,t.title=this.obj.recipe_name,t.img=this.obj.cover,this.$post("~/api/collect/add?",t,e=>{e.result?this.$message.success("收藏成功"):e.error&&(this.$toast(e.error.message),console.error(e.error))}))},check_collect(){var e=this.$store.state.user.user_id,t=this.obj.recipe_information_id;this.$get("~/api/collect/count?",{user_id:e,source_table:"recipe_information",source_field:"recipe_information_id",source_id:t},e=>{this.state_collect=e.result})}},created(){setTimeout(()=>{this.get_praise(),this.check_collect()},1e3)},watch:{obj:{deep:!0,handler(){this.praiseLen=this.obj["praise_len"]}}}},_=c,m=(i("633d"),i("2877")),d=Object(m["a"])(_,o,a,!1,null,null,null),p=d.exports,u=i("0ff7"),f=i("a3ca"),h=i("56c9"),v={mixins:[l["a"]],components:{list_comment:u["a"],bar_title:f["a"],form_editor:h["a"],div_recipe_information:p},data(){return{url_get_obj:"~/api/recipe_information/get_obj?",field:"recipe_information_id",obj:{praise_len:0},query:{recipe_name:"",recipe_classification:"",cover:"",taboo:"",energy:"",intended_for:"",make_video:"",recipe_introduction:"",recipe_information_id:0},praise:0,state_praise:!1,state_collect:!1,list_comment:[],form_comment:{source_table:"recipe_information",source_field:"recipe_information_id",source_id:0,reply_to_id:0},count_pages:1}},methods:{add_hits(e){e["hits"]=e["hits"]+1;var t=e["hits"];this.$post("~/api/recipe_information/set?recipe_information_id="+e["recipe_information_id"],{hits:t},e=>{if(e.result){console.log("添加访问量状态：",e.result);var t={source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj.recipe_information_id,user_id:this.$store.state.user.user_id};this.$post("~/api/hits/add?",t,e=>{console.log(e)})}else e.error&&console.error(e.error)})},get_obj_after(e){if(e&&e.result&&e.result.obj){console.log(e.result.obj);var t=e.result.obj;this.obj=t,this.add_hits(t),this.get_praise(t),this.get_collect(t),this.get_comment(t),this.form_comment.source_id=this.obj.recipe_information_id}},get_collect(e){var t=this.user.user_id,i={source_table:"recipe_information",source_field:"recipe_information_id",source_id:e["recipe_information_id"],user_id:t};this.$get("~/api/collect/count?",i,e=>{if(console.log(e),e.result||0===e.result){var t=!!e.result;this.state_collect=t,console.log("收藏状态："+t)}else e.error&&console.error(e.error)})},toShare(e){window.open(e)},change_collect(){var e=this.user.user_id,t={source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj["recipe_information_id"],user_id:e};this.state_collect?(this.state_collect=!1,this.$get("~/api/collect/del?",t,e=>{e.result?this.$toast("取消收藏"):e.error&&(this.$toast(e.error.message),console.error(e.error))})):(this.state_collect=!0,t.title=this.obj.recipe_name,t.img=this.obj.cover,this.$post("~/api/collect/add?",t,e=>{e.result?this.$toast("收藏成功"):e.error&&(this.$toast(e.error.message),console.error(e.error))}))},get_praise(e){var t=this.user.user_id;this.$get("~/api/praise/count?",{source_table:"recipe_information",source_field:"recipe_information_id",source_id:e["recipe_information_id"]},e=>{e.result||0===e.result?(this.praise=e.result,console.log("点赞数：",e.result)):e.error&&(this.$toast(e.error.message),console.error(e.error))}),this.$get("~/api/praise/count?",{source_table:"recipe_information",source_field:"recipe_information_id",source_id:e["recipe_information_id"],user_id:t},e=>{e.result||0===e.result?(this.state_praise=!!e.result,console.log("点赞状态：",e.result)):e.error&&(this.$toast(e.error.message),console.error(e.error))})},change_praise(e){var t=this.user.user_id,i={source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj["recipe_information_id"],user_id:t},s=this;s.obj.praise_len=parseInt(s.obj.praise_len),this.state_praise?(this.state_praise=!1,this.$get("~/api/praise/del?",i,e=>{if(e.result){var t=s.obj.praise_len-1;this.$post("~/api/recipe_information/set?recipe_information_id="+s.obj["recipe_information_id"],{praise_len:t},e=>{e.result?console.log("添加点赞数状态：",e.result):e.error&&console.error(e.error)}),this.$toast("取消点赞")}else e.error&&(this.$toast(e.error.message),console.error(e.error))})):(this.state_praise=!0,this.$post("~/api/praise/add?",i,e=>{if(e.result){var t=s.obj.praise_len+1;this.$post("~/api/recipe_information/set?recipe_information_id="+s.obj["recipe_information_id"],{praise_len:t},e=>{e.result?console.log("添加点赞数状态：",e.result):e.error&&console.error(e.error)}),this.$toast("点赞成功")}else e.error&&(this.$toast(e.error.message),console.error(e.error))}))},link_gen(e){return{query:this.query}},change_page(e){this.get_comment(this.obj,e),this.form_comment.reply_to_id=0},update_comment(){this.get_comment(this.obj)},get_comment(e,t=1,i=5){this.$get("~/api/comment/get_list?",{source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj["recipe_information_id"],page:t,size:i,reply_to_id:"0",orderby:"create_time desc"},e=>{if(e.result){var t=e.result.list;t.map(e=>{e.list_reply=[]}),this.add_reply(t).then(e=>{this.list_comment=e}),this.count_pages=Math.ceil(e.result.count/i)||1}})},add_reply(e){return new Promise(t=>{for(let i=0;i<e.length;i++){const t=e[i];this.$get("~/api/comment/get_list?",{source_table:"recipe_information",source_field:"recipe_information_id",source_id:this.obj["recipe_information_id"],orderby:"create_time desc",reply_to_id:t.comment_id},e=>{e.result&&(t.list_reply=e.result.list)})}t(e)})}},computed:{respondent(){var e=this.form_comment.reply_to_id,t="";return this.list_comment.map(i=>{i.comment_id===e&&(t=i.nickname)}),t}},mounted(){}},g=v,b=(i("6422"),Object(m["a"])(g,s,r,!1,null,null,null));t["default"]=b.exports},5028:function(e,t,i){},"56a0":function(e,t,i){"use strict";i("e1be")},"56c9":function(e,t,i){"use strict";var s=function(){var e=this,t=e._self._c;return e.publish_flag?t("div",{staticClass:"form_editor"},[t("div",{staticClass:"editor"},[t("quill-editor",{staticClass:"form_editor_block",model:{value:e.form.content,callback:function(t){e.$set(e.form,"content","string"===typeof t?t.trim():t)},expression:"form.content"}})],1),t("div",{staticClass:"btn_publish"},[t("b-button",{attrs:{variant:"outline-primary",block:""},on:{click:function(t){return e.publish()}}},[e._v("发表")])],1)]):e._e()},r=[],o=(i("14d9"),i("6265")),a={mixins:[o["a"]],props:{form:{type:Object,default(){return{content:""}}},post_url:{type:String,default(){return""}},publish_flag:{type:Boolean,default(){return!0}}},data(){return{}},methods:{publish(){if(this.user.user_id){var e=this.form;if(e=Object.assign(e,{user_id:this.user.user_id,avatar:this.user.avatar,nickname:this.user.nickname}),e.content){var t=this.post_url;t||(t="~/api/comment/add?"),this.$post(t,e,e=>{e.result?(this.$emit("update_comment"),this.form.content=""):e.error&&this.$toast(e.error.message)})}else this.$toast("输入内容不能为空")}else this.$router.push("/account/login")}},components:{}},n=a,l=(i("56a0"),i("2877")),c=Object(l["a"])(n,s,r,!1,null,"acba44fa",null);t["a"]=c.exports},"5ca9":function(e,t,i){"use strict";i("9e69")},"633d":function(e,t,i){"use strict";i("d18f")},6422:function(e,t,i){"use strict";i("5028")},"9e69":function(e,t,i){},d18f:function(e,t,i){},e1be:function(e,t,i){}}]);
//# sourceMappingURL=chunk-d00dd5ea.368cbc4e.js.map