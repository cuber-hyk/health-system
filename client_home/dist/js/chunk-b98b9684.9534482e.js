(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b98b9684"],{b5d1:function(t,i,e){"use strict";e("bbf6")},bbf6:function(t,i,e){},e12a:function(t,i,e){"use strict";e.r(i);var s=function(){var t=this,i=t._self._c;return i("div",{staticClass:"page_notice",attrs:{id:"notice_list"}},[i("div",{staticClass:"warp"},[i("div",{staticClass:"container"},[i("div",{staticClass:"row"},[i("div",{staticClass:"col-12"},[i("div",{staticClass:"car_notice"},[i("div",{staticClass:"header-carousel"},[i("swiper",{attrs:{"slides-per-view":1,loop:!0,autoplay:{delay:1e3,disableOnInteraction:!0,stopOnLastSlide:!1}}},t._l(t.slides,(function(t,e){return i("swiper-slide",{key:e},[i("img",{attrs:{src:t.image,alt:t.title}})])})),1)],1),i("div",{staticClass:"notice_list"},t._l(t.list,(function(e,s){return i("router-link",{key:s,staticClass:"notice_block",attrs:{to:"/notice/details?notice_id="+e.notice_id}},[i("div",{staticClass:"notice_title ellipsis_2"},[t._v(" "+t._s(e.title)+" ")]),i("div",{staticClass:"notice_create_time"},[t._v(" "+t._s(t._f("formatDate")(e.create_time))+" ")])])})),1)])])]),i("div",{staticClass:"row"},[i("div",{staticClass:"col overflow-auto flex_cc"},[i("b-pagination",{attrs:{"total-rows":t.count,"per-page":t.query.size},on:{change:t.goToPage},model:{value:t.query.page,callback:function(i){t.$set(t.query,"page",i)},expression:"query.page"}})],1)])])])])},a=[],l=e("c6e7"),c={mixins:[l["a"]],data(){return{url_get_list:"~/api/notice/get_list",list:[],query:{title:"",page:1,size:10},count_pages:3,slides:[{image:"/img/gg.jpg",title:"Title 1"},{image:"/img/lianxiwomen.png",title:"Title 2"},{image:"/img/default.png",title:"Title 3"}]}},methods:{get_list_after(t){this.count_pages=Math.ceil(t.result.count/this.query.size)},goToPage(t){this.query.page=t,this.goToNew(t)}}},o=c,n=(e("b5d1"),e("2877")),r=Object(n["a"])(o,s,a,!1,null,"0a6b18f5",null);i["default"]=r.exports}}]);
//# sourceMappingURL=chunk-b98b9684.9534482e.js.map